from django.core.validators import FileExtensionValidator
from django.db import models
from django.contrib.auth.models import User
from django.utils.timezone import now


class UserInfo(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    firstname = models.CharField(max_length=255, verbose_name='Имя')
    lastname = models.CharField(max_length=255, verbose_name='Фамилия')
    thirdname = models.CharField(max_length=255, verbose_name='Отчество')
    company_name = models.CharField(max_length=255, verbose_name='Наименование работодателя')

    class Meta:
        ordering = ['id']
        verbose_name = "Информация о студенте"
        verbose_name_plural = "Информация о студентах"


class StudySection(models.Model):
    name = models.CharField(max_length=255, verbose_name='Наименование раздела обучения')
    description = models.TextField(verbose_name='Кртакое описание раздела обучения', max_length=100, blank=True)
    is_deprecated = models.BooleanField(default=False, verbose_name='Устаревший раздел обучения?')

    class Meta:
        ordering = ['id']
        verbose_name = "Раздел обучения"
        verbose_name_plural = "Разделы обучения"

    def __str__(self):
        return f"{self.name}"


class Course(models.Model):
    study_section_id = models.ForeignKey(StudySection, on_delete=models.PROTECT,
                                         verbose_name='Выберите программу обучение')
    name = models.CharField(max_length=255, verbose_name='Наименование направления обучения')
    description = models.TextField(verbose_name='Краткое описание направления обучения', max_length=100, blank=True)
    is_deprecated = models.BooleanField(default=False, verbose_name='Устаревшее направление обучения?')

    class Meta:
        ordering = ['id']
        verbose_name = "Направление обучения"
        verbose_name_plural = "Направление обучения"

    def __str__(self):
        return f"{self.name}"


class StudyProgram(models.Model):
    course_id = models.ForeignKey(Course, on_delete=models.PROTECT, verbose_name='Выберите направление обучение')
    name = models.CharField(max_length=255, verbose_name='Наименование программы обучения')
    description = models.TextField(verbose_name='Краткое описание программы обучения', max_length=100, blank=True)
    study_group_alias = models.CharField(max_length=20, verbose_name='Сокращенное название группы подготовки',
                                         blank=False)
    minimal_duration = models.IntegerField(default=0, verbose_name='Минимальное время подготовки, час.')
    complete_time = models.IntegerField(default=0, verbose_name = 'Максимальное время выполнения теста, мин.')
    question_nums = models.IntegerField(default=0, verbose_name='Количество вопросов в тесте')
    tries_count = models.IntegerField(default=5, verbose_name='Количество попыток итогового теста')
    is_deprecated = models.BooleanField(default=False, verbose_name='Устаревшая программа обучения?')

    class Meta:
        ordering = ['id']
        verbose_name = "Программа обучения"
        verbose_name_plural = "Программы обучения"

    def __str__(self):
        return f"{self.name}"

class StudyProgramLiterature(models.Model):
    program = models.ForeignKey(StudyProgram, on_delete=models.PROTECT)
    title = models.CharField(max_length=255, verbose_name='Заголовок', blank=False)
    link = models.CharField(max_length=300, verbose_name='Ссылка', blank=True)
    youtube_link = models.CharField(max_length=300, verbose_name='Ссылка на YouTube видео', blank=True)
    document_url = models.FileField(verbose_name='Документ для прочтения', upload_to='literature/',
                                    null=True, blank=True,
                                    validators=[FileExtensionValidator(allowed_extensions=['doc', 'docx', 'pdf',
                                                                                           'ppt', 'pptx'])])
    description = models.TextField(verbose_name='Дополнительная информация', blank=True)

    class Meta:
        ordering = ['id']
        verbose_name = "Литература для обучения"
        verbose_name_plural = "Литература для обучения"


class StudyGroups(models.Model):
    name = models.CharField(max_length=255, verbose_name='Название группы', unique=True)
    program = models.ForeignKey(StudyProgram, on_delete=models.PROTECT, verbose_name='Программа обучения', null=False)
    start_date = models.DateTimeField(verbose_name='Дата начала обучения')
    end_date = models.DateTimeField(verbose_name='Дата окончания обучения', null=True, blank=True)
    is_complete = models.BooleanField(default=False, verbose_name='Вся группа завершила обучение?')

    class Meta:
        ordering = ['id']
        verbose_name = "Обучающаяся группа"
        verbose_name_plural = "Обучающиеся группы"

    def __str__(self):
        return f"{self.name, self.program.name}"


class Contract(models.Model):
    user = models.ForeignKey(User, verbose_name='Студент', on_delete=models.PROTECT)
    group = models.ForeignKey(StudyGroups, verbose_name='Группа обучения', on_delete=models.PROTECT)
    enrollment_date = models.DateTimeField(verbose_name='Дата зачисления')
    is_complete = models.BooleanField(default=False, verbose_name='Завершил обучение?')

    def __str__(self):
        return f"{'{} {}. {}.'.format(self.user.userinfo.lastname, self.user.userinfo.firstname[0], self.user.userinfo.thirdname[0]), self.group.program.name}"

    class Meta:
        ordering = ['id']
        verbose_name = "Контракт на обучение"
        verbose_name_plural = "Контракты на обучение"


class Question(models.Model):
    program = models.ForeignKey(StudyProgram, on_delete=models.PROTECT, verbose_name='Программа обучения')
    question_text = models.TextField(blank=False, verbose_name='Текст вопроса')
    image = models.FileField(verbose_name='Изображение в вопросе', upload_to='questions/', null=True, blank=True,
                             validators=[FileExtensionValidator(allowed_extensions=['png', 'jpg', 'bmp'])])
    answer_information = models.TextField(blank=False, verbose_name='Информация из нормативного документа')

    class Meta:
        ordering = ['id']
        verbose_name = "Вопрос"
        verbose_name_plural = "Вопросы"

    def __str__(self):
        return f"{self.question_text}"


class Answer(models.Model):
    question = models.ForeignKey(Question, on_delete=models.PROTECT, verbose_name='Вопрос')
    answer_text = models.TextField(blank=False, verbose_name='Текст варианта ответа')
    is_right = models.BooleanField(default=False, verbose_name='Правильный вариант ответа?')

    class Meta:
        ordering = ['id']
        verbose_name = "Ответ"
        verbose_name_plural = "Ответы"

    def __str__(self):
        return f"{self.answer_text}"


class TestTry(models.Model):
    contract = models.ForeignKey(Contract, on_delete=models.PROTECT, verbose_name='Студент')
    program = models.ForeignKey(StudyProgram, on_delete=models.PROTECT, verbose_name='Программа обучения')
    start_date = models.DateTimeField(default=now, verbose_name='Дата начала теста')
    end_date = models.DateTimeField(verbose_name='Дата начала теста', null=True)
    is_test = models.BooleanField(default=False, verbose_name='Пробное тестирование?')
    is_final = models.BooleanField(default=False, verbose_name='Итоговое тестирование?')
    is_complete = models.BooleanField(default=False, verbose_name='Попытка завершена?')
    is_successful = models.BooleanField(default=False, verbose_name='Тест пройден?')

    class Meta:
        ordering = ['id']
        verbose_name = "Попытка прохождения теста"
        verbose_name_plural = "Попытки прохождения теста"

    def __str__(self):
        return f"{self.program.name, self.is_successful}"


class TryAnswers(models.Model):
    test_try = models.ForeignKey(TestTry, on_delete=models.PROTECT, verbose_name='Попытка')
    question = models.ForeignKey(Question, on_delete=models.PROTECT, verbose_name='Вопрос')
    answer = models.ForeignKey(Answer, on_delete=models.PROTECT, verbose_name='Полученный ответ', blank=True, null=True)
    npp = models.IntegerField(default=0, verbose_name = 'Номер вопроса по порядку')

    class Meta:
        ordering = ['id']
        verbose_name = "Ответ на тест"
        verbose_name_plural = "Ответы на тесты"


class EducationRequest(models.Model):
    fio = models.CharField(max_length=255, verbose_name='ФИО подавшего заявку')
    organization = models.CharField(max_length=255, verbose_name='Организация заявителя')
    phone = models.CharField(max_length=255, verbose_name='Номер телефона')
    email = models.CharField(max_length=255, verbose_name='Email-адрес')
    request_date = models.DateTimeField(verbose_name='Дата запроса', default=now)
    request_text = models.TextField(verbose_name='Текст запроса', blank=True)
    # additional_info = models.FileField(verbose_name='Приложенный документ', upload_to='education_requests/',
    #                          null=True, blank=True,
    #                          validators=[FileExtensionValidator(allowed_extensions=['docx', 'doc', 'txt'])])

    class Meta:
        ordering = ['id']
        verbose_name = "Запрос на обучение"
        verbose_name_plural = "Запросы на обучение"

class Webinar(models.Model):
    title = models.CharField(max_length=255, verbose_name='Заголовок вебинара', blank=False)
    description = models.TextField(verbose_name='Дополнительная информация', blank=True)
    youtube_link = models.CharField(max_length=255, verbose_name='Ссылка на YouTube видео', blank=False)

    class Meta:
        ordering = ['id']
        verbose_name = "Вебинар"
        verbose_name_plural = "Вебинары"

    def __str__(self):
        return f"{self.title}"


class Certificate(models.Model):
    title = models.CharField(max_length=255, verbose_name='Заголовок сертификата', blank=False)
    description = models.TextField(verbose_name='Дополнительная информация', blank=True)
    image = models.FileField(verbose_name='Отображаемое изображение сертификата', upload_to='certificates/',
                             null=False, blank=False,
                             validators=[FileExtensionValidator(allowed_extensions=['png', 'jpg', 'bmp'])])

    class Meta:
        ordering = ['id']
        verbose_name = "Сертификат"
        verbose_name_plural = "Сертификаты"

    def __str__(self):
        return f"{self.title}"
