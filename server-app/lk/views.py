import random
from datetime import datetime, timedelta
import json

from django.conf import settings
from django.contrib.auth import authenticate, login, logout
from django.http import HttpResponse, JsonResponse
from django.db import connection
from django.utils.timezone import now
from django.views.decorators.csrf import csrf_exempt
from telegram import ParseMode
from telegram.ext import Updater

from lk.models import StudyProgram, Contract, Question, TestTry, TryAnswers, Answer, StudyProgramLiterature, \
    EducationRequest, UserInfo, StudySection, Course, Webinar, Certificate
from lk.serializers import TestTrySerializer
from lk.utils import rest_login_required, to_dict, admin_required, to_list, path_to_program, get_users_id_list


def test(request):
    return HttpResponse('{"app":"lk"}')


@csrf_exempt
def get_sections(request):
    ss = StudySection.objects.filter(is_deprecated=False).values()

    return JsonResponse({
        'error_code': 0,
        'programs': list(ss)
    })


@csrf_exempt
def get_courses(request):
    if request.method == 'POST':
        body = json.loads(request.body)
        section_id = body['section_id']
        cources = Course.objects.filter(study_section_id=section_id).values()

        return JsonResponse({
            'error_code': 0,
            'programs': list(cources)
        })


@csrf_exempt
def get_public_programs(request):
    if request.method == 'POST':
        body = json.loads(request.body)
        course_id = body['course_id']
        programs = StudyProgram.objects.filter(course_id=course_id).values()

        return JsonResponse({
            'error_code': 0,
            'programs': list(programs)
        })


@rest_login_required
@csrf_exempt
def get_study_programs(request):
    sp = StudyProgram.objects.filter(studygroups__contract__user_id=request.user.id).values(
        'id', 'name', 'studygroups__contract__is_complete')

    return JsonResponse({
        'error_code': 0,
        'programs': list(sp)
    })


@rest_login_required
def login_test(request):
    return HttpResponse('{"login":"true"}')


@admin_required
@csrf_exempt
def admin_test(request):
    return JsonResponse({
        'error_code': 0,
        'username': ''
    })


@csrf_exempt
def lk_login(request):
    if request.method == 'POST':
        body = json.loads(request.body)
        username = body['username']
        password = body['pswd']
        user = authenticate(request=request, username=username, password=password)
        if user is not None:
            login(request, user)
            return JsonResponse({
                'error_code': 0,
                'user': to_dict(user),
                'session_key': request.session.session_key,
                'is_superuser': user.is_superuser
            })
        else:
            return JsonResponse({
                'error_code': 1,
                'error_message': 'Неверный пароль'
            })

    return HttpResponse(status=405)


@rest_login_required
@csrf_exempt
def lk_logout(request):
    logout(request)
    return JsonResponse({
        'error_code': 0,
        'username': ''
    })


@rest_login_required
@csrf_exempt
def get_study_programs(request):
    sp = StudyProgram.objects.filter(studygroups__contract__user_id=request.user.id).values(
        'id', 'name', 'studygroups__contract__is_complete')

    return JsonResponse({
        'error_code': 0,
        'programs': list(sp)
    })


@rest_login_required
@csrf_exempt
def start_test(request):
    if request.method == 'POST':
        body = json.loads(request.body)
        program_id = int(body['program_id'])
        is_final = True if str.lower(body['is_final']) == 'true' else False

        sp = StudyProgram.objects.get(id=program_id)
        sp_num = sp.question_nums
        contract_qs = Contract.objects.get(user_id=request.user.id, group__program_id=program_id, is_complete=False)
        contract_id = contract_qs.id

        # Сделать незавершенные тесты завершенными
        unfinished = TestTry.objects.filter(program_id=program_id, contract_id=contract_id, is_complete=False).values()
        unfinished = list(unfinished)
        if len(unfinished) > 0:
            for t in unfinished:
                temp = TestTry.objects.get(id=t['id'])
                temp.end_date = datetime.now()
                temp.is_complete = True
                temp.save()

        # Проверить, прошло ли необходимое время для подготовки
        success_time = contract_qs.group.start_date + timedelta(hours=sp.minimal_duration)
        curr = datetime.now()
        success_time,curr = success_time.replace(tzinfo=None), curr.replace(tzinfo=None)
        print(success_time)
        print(curr)
        if (success_time >= curr) and is_final:
            return JsonResponse({
                'error_code': 2,
                'message': 'Время необходимое для подготовки ещё не прошло'
            })

        # Проверить, проходился ли пробный тест
        success_test_count = TestTry.objects.filter(program_id=program_id, contract_id=contract_id,
                                                    is_test=True, is_successful=True).count()
        if (success_test_count == 0) and is_final:
            return JsonResponse({
                'error_code': 2,
                'message': 'Для прохождения итогового теста необходимо успешно пройти пробное тестирование'
            })

        # Посчитать количество потраченных попыток итогового теста
        final_count = TestTry.objects.filter(program_id=program_id, contract_id=contract_id, is_final=True).count()
        tries_count = StudyProgram.objects.get(id=program_id).tries_count
        if final_count >= tries_count:
            return JsonResponse({
                'error_code': 2,
                'message': 'Потрачены все попытки прохождения итогового теста'
            })

        # Выбираем рандомные вопросы для теста
        query = '''
            SELECT *
            FROM lk_question
            WHERE program_id = %s
            ORDER BY RANDOM() LIMIT %s
            '''
        questions = Question.objects.raw(query, [program_id, sp_num])

        tt = TestTry(program_id=program_id, contract_id=contract_id, is_test=(not is_final), is_final=is_final)
        tt.save()

        for i, question in enumerate(questions):
            ta = TryAnswers(question=question, test_try=tt, npp=i+1)
            ta.save()

        return JsonResponse({
            'error_code': 0,
            'test': {'test_id': tt.id, 'start_date': tt.start_date, 'is_test': tt.is_test, 'is_final': tt.is_final,
                     'test_time': sp.complete_time}
        })

@rest_login_required
@csrf_exempt
def get_question(request):
    if request.method == 'POST':
        body = json.loads(request.body)
        test_id = int(body['test_id'])

        # Список неотвеченных вопросов
        ta_qs = TryAnswers.objects.filter(test_try_id=test_id, answer_id__isnull=True).values()
        ta = list(ta_qs)

        # Общее количество вопросов в тесте
        amount = TryAnswers.objects.filter(test_try_id=test_id).count()

        # Если вопросы кончились, возвращаем окончание теста
        if len(ta) == 0:
            return JsonResponse({
                'error_code': 0,
                'test': None,
                'is_complete': True
            })

        # Выгружаем вопрос и ответы на него (ответы мешаем рандомно)
        ta = ta[0]
        question = Question.objects.get(id=ta['question_id'])
        answers_qs = Answer.objects.filter(question_id=ta['question_id']).values('id', 'answer_text')
        answers = list(answers_qs)
        random.shuffle(answers)

        curr = TryAnswers.objects.filter(test_try_id=test_id, answer_id__isnull=False).count() + 1

        result = {'test_id': ta['test_try_id'],
                  'try_answer_id': ta['id'],
                  'question_id': question.id,
                  'question_text': question.question_text,
                  'question_pic': question.image.url if question.image else None,
                  'answers': answers,
                  'amount': amount,
                  'curr': curr}

        return JsonResponse({
            'error_code': 0,
            'test': result,
            'is_complete': False
        })


@rest_login_required
@csrf_exempt
def put_answer(request):
    if request.method == 'POST':
        body = json.loads(request.body)
        try_answer_id = int(body['try_answer_id'])
        answer_id = int(body['answer_id'])
        # получаем идентификатор попытки ответа
        ans = TryAnswers.objects.get(id = try_answer_id)
        # Определяем не превышено ли время выполнения теста
        test_try = TestTry.objects.get(id = ans.test_try_id)
        complete_time = StudyProgram.objects.get(id = test_try.program_id).complete_time
        deadline = test_try.start_date + timedelta(minutes=complete_time)
        curr = datetime.now()
        deadline = deadline.replace(tzinfo=None)
        curr = curr.replace(tzinfo=None)
        # TODO проверка на клиенте
        if deadline < curr:
            return JsonResponse({
                'error_code': 3,
                'message': 'Превышено время выполения теста'
            })
        # Кладём ответ
        ans.answer_id = answer_id
        ans.save()

        return JsonResponse({
            'error_code': 0,
            'result': 'ok'
        })


@rest_login_required
@csrf_exempt
def get_result(request):
    if request.method == 'POST':
        body = json.loads(request.body)
        test_id = body['test_id']
        if test_id is None:
            return JsonResponse({
                'error_code': 1,
                'message': 'Empty test_id'
            })
        test_id = int(test_id)
        try:
            tt = TestTry.objects.get(id = test_id)
        except TestTry.DoesNotExist as e:
            return JsonResponse({
                'error_code': 1,
                'message': 'Wrong test_id'
            })
        # Присваиваем попытке прохождения теста статус "Завершено"
        tt.is_complete = True
        # Проверяем успешность прохождения теста
        amount = TryAnswers.objects.filter(test_try_id=test_id).count()
        right_count = TryAnswers.objects.filter(test_try_id=test_id, answer__is_right=True).count()
        # Тест пройден успешно, если допущено не более 2 ошибок
        if (amount - right_count) <= 2:
            tt.is_successful = True
            # Если это был итоговый тест, то засчитываем это как прохождение программы обучения
            if tt.is_final:
                c = tt.contract
                c.is_complete = True
                c.save()
        tt.end_date = now()
        tt.save()

        query = '''
        select lq.id, lq.question_text, ans_given.id, ans_given.answer_text,
               ans_right.id, ans_right.answer_text, lq.answer_information, ta.npp
        from lk_tryanswers ta
            inner join lk_question lq on lq.id = ta.question_id
            left join lk_answer ans_given on ta.answer_id = ans_given.id
            inner join lk_answer ans_right on lq.id = ans_right.question_id and ans_right.is_right = True
        where ta.test_try_id = %s
        order by ta.npp
        '''

        with connection.cursor() as cursor:
            cursor.execute(query, [test_id])
            result = []
            for answer in cursor.fetchall():
                result.append({'question_id': answer[0], 'question_text': answer[1],
                               'given_answer_id': answer[2], 'given_answer_text': answer[3],
                               'right_answer_id': answer[4], 'right_answer_text': answer[5],
                               'right_answer_info': answer[6], 'npp': answer[7]})

        tt = TestTrySerializer(tt)
        return JsonResponse({
            'error_code': 0,
            'result': {'test': tt.data, 'ans_amount': amount, 'right_count':right_count, 'answers': result}
        })


@rest_login_required
@csrf_exempt
def get_user_info(request):
    if request.method == 'POST':
        user_info = request.user.userinfo
        return JsonResponse({
            'error_code': 0,
            'user': to_dict(user_info)
        })


@rest_login_required
@csrf_exempt
def get_program_literature(request):
    if request.method == 'POST':
        body = json.loads(request.body)
        program_id = body['program_id']
        literature_list = StudyProgramLiterature.objects.filter(program_id = program_id).values()
        return JsonResponse({
            'error_code': 0,
            'literature': list(literature_list)
        })

@rest_login_required
@csrf_exempt
def get_webinars(request):
    if request.method == 'POST':
        webinars_list = Webinar.objects.values()
        return JsonResponse({
            'error_code': 0,
            'webinars': list(webinars_list)
        })


@rest_login_required
@csrf_exempt
def get_certificates(request):
    if request.method == 'POST':
        certs_list = Certificate.objects.values()
        return JsonResponse({
            'error_code': 0,
            'certificates': list(certs_list)
        })


@rest_login_required
@csrf_exempt
def get_users(request):
    if request.method == 'POST':
        body = json.loads(request.body)
        lastname = body['lastname']
        firstname = body['firstname']
        thirdname = body['thirdname']
        user_info = UserInfo.objects.filter(lastname__icontains=lastname, firstname__icontains=firstname,
                                thirdname__icontains=thirdname).values()

        return JsonResponse({
            'error_code': 0,
            'user': list(user_info)
        })



@rest_login_required
@csrf_exempt
def get_students_statistics(request):
    if request.method == 'POST':
        body = json.loads(request.body)
        lastname = body['lastname']
        firstname = body['firstname']
        thirdname = body['thirdname']

        ids = get_users_id_list(lastname, firstname, thirdname)
        if len(ids) == 0:
            return JsonResponse({
                'error_code': 0,
                'statistic': []
            })

        query = '''
        select lc.id, lu.lastname, lu.firstname, lu.thirdname, ls.id prg_id, ls.name prg_name,
           (select count(*) from lk_testtry lkt where lkt.program_id = ls.id
                                                  and lkt.contract_id = lc.id and lkt.is_test = True) test_amount,
           (select count(*) from lk_testtry lkt where lkt.program_id = ls.id
                                                  and lkt.contract_id = lc.id and lkt.is_test = True
                                                  and lkt.is_successful = True) test_succ_amount,
           (select count(*) from lk_testtry lkt where lkt.program_id = ls.id
                                                  and lkt.contract_id = lc.id and lkt.is_final = True ) final_amount,
           (select count(*) from lk_testtry lkt where lkt.program_id = ls.id
                                                  and lkt.contract_id = lc.id and lkt.is_final = True
                                                  and lkt.is_successful = false) final_fail_amount
        from lk_studyprogram ls
            inner join lk_studygroups l on ls.id = l.program_id
            inner join lk_contract lc on l.id = lc.group_id
            inner join auth_user au on au.id = lc.user_id
            inner join lk_userinfo lu on au.id = lu.user_id
            inner join lk_testtry lt on lc.id = lt.contract_id
        where au.id in %s
        group by au.id, lc.id, lu.lastname, lu.firstname, lu.thirdname, ls.id, ls.name
        '''
        with connection.cursor() as cursor:
            cursor.execute(query, [ids])
            result = []
            for answer in cursor.fetchall():
                result.append({'contract_id': answer[0], 'lastname': answer[1],
                               'firstname': answer[2], 'thirdname': answer[3],
                               'program_id': answer[4], 'program_name': answer[5],
                               'test_amount': answer[6], 'test_succ_amount': answer[7],
                               'final_amount': answer[8], 'final_fail_amount': answer[9]})
        return JsonResponse({
            'error_code': 0,
            'statistic': list(result)
        })


@csrf_exempt
def put_order(request):
    if request.method == 'POST':
        body = json.loads(request.POST.get('data'))
        fio = body['fio']
        organization = body['organization']
        phone = body['phone']
        email = body['email']
        request_text = body['request_text']
        additional_info_file = request.FILES.get('file')

        er = EducationRequest(fio=fio, organization=organization, phone=phone, email=email, request_text=request_text,
                         request_date=datetime.now())
        er.save()

        updater = Updater(settings.BOT_TOKEN)
        msg_text = 'Получен новая заявка на обучение №{no}:\n<b>ФИО:</b> {fio}\n<b>Организация:</b> {org}' \
                   '\n<b>Номер телефона:</b> {phone}\n<b>E-mail:</b> {email}\n<b>Текст запроса:</b> {req_text}'\
            .format(no=er.id, fio=fio, org=organization, phone=phone, email=email, req_text=request_text)
        updater.bot.sendMessage(chat_id=settings.ADMIN_CHAT_ID, text=msg_text, parse_mode=ParseMode.HTML)
        if additional_info_file is not None:
            type = str.split(additional_info_file.name, '.')[-1]
            file_name = 'Приложение к заявке {}.{}'.format(er.id, type)
            updater.bot.sendDocument(chat_id=settings.ADMIN_CHAT_ID, document=additional_info_file, filename=file_name)
        return JsonResponse({
            'error_code': 0,
            'status': 'ok'
        })


@admin_required
@csrf_exempt
def get_question_admin(request):
    if request.method == 'POST':
        body = json.loads(request.body)
        program_id = body['program_id']
        offset = int(body['offset'])

        questions = Question.objects.filter(program_id=program_id).all()
        questions_amount = len(questions)

        if offset >= questions_amount:
            return JsonResponse({
                'error_code': 4,
                'status': 'Общее количество вопросов меньше заданного номера'
            })

        curr_question = questions[offset]
        answers = Answer.objects.filter(question_id=curr_question.id).values()

        result = {'question_id': curr_question.id,
                  'question_text': curr_question.question_text,
                  'question_pic': curr_question.image.url if curr_question.image else None,
                  'answer_information': curr_question.answer_information,
                  'answers': list(answers),
                  'amount': questions_amount,
                  'curr': offset}

        return JsonResponse({
            'error_code': 0,
            'test': result
        })
