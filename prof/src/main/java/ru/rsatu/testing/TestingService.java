package ru.rsatu.testing;

import org.eclipse.microprofile.jwt.JsonWebToken;
import ru.rsatu.common.BaseResponse;
import ru.rsatu.tables.*;
import ru.rsatu.testing.startTest.getResult.GetResultRequest;
import ru.rsatu.testing.startTest.getResult.GetResultResponse;
import ru.rsatu.testing.startTest.getResult.TestResult;
import ru.rsatu.testing.startTest.putAnswer.PutAnswerRequest;
import ru.rsatu.testing.startTest.getQuestion.CurrTestRespone;
import ru.rsatu.testing.startTest.getQuestion.GetQuestionRequest;
import ru.rsatu.testing.startTest.getQuestion.GetQuestionResponse;
import ru.rsatu.testing.startTest.startTest.StartRequest;
import ru.rsatu.testing.startTest.startTest.StartResponse;
import ru.rsatu.testing.startTest.statistic.GetStatisticRequest;
import ru.rsatu.testing.startTest.statistic.Statistic;
import ru.rsatu.testing.startTest.statistic.StatisticResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.*;

import static java.util.Collections.shuffle;


@ApplicationScoped
public class TestingService {

    @Inject
    JsonWebToken jwt;
    @Inject
    EntityManager em;

    public BaseResponse getResult(GetResultRequest request) {
        TestTry testTry = TestTry.findById(request.getTestId());
        testTry.setComplete(true);
        Long amount = TryAnswers.find("testTryId = ?1", request.getTestId()).count();
        List<TryAnswers> rightTryAnswers = em.createQuery("select ta " +
                        "from TryAnswers ta " +
                        "   left join Answer a on a.answerId = ta.answer.answerId " +
                        "where ta.testTry.testTryId = :taid and a.isRight = true ", TryAnswers.class)
                .setParameter("taid", request.getTestId())
                .getResultList();
        Integer rightCount = rightTryAnswers.size();
        // Тест пройден успешно, если допущено не более 2 ошибок
        if ((amount.intValue() - rightCount) <= 2){
            testTry.setSuccessful(true);
            // Если это был итоговый тест, то засчитываем это как прохождение программы обучения
            if (testTry.getFinal()) {
                Contract contract = testTry.getContract();
                contract.setComplete(true);
                contract.persist();
            }
        } else {
            testTry.setSuccessful(false);
        }
        testTry.setEndDate(new Date());
        testTry.persist();

        List<TryAnswers> tryAnswers = new ArrayList<>(testTry.tryAnswers);
        List<TestResult> testResults = new ArrayList<>();
        for (TryAnswers tryAnswer: tryAnswers){
            TestResult tempResult = new TestResult();
            tempResult.setQuestionId(tryAnswer.question.questionId);
            tempResult.setQuestionText(tryAnswer.question.questionText);
            tempResult.setGivenAnswerId(tryAnswer.getAnswer().answerId);
            tempResult.setGivenAnswerText(tryAnswer.getAnswer().answerText);
            Answer rightAnswer = em.createQuery("select a " +
                    "from TryAnswers ta " +
                    "   inner join Answer a on ta.question.questionId = a.question.questionId " +
                    "where ta.tryAnswersId = :taid and a.isRight = true ", Answer.class)
                    .setParameter("taid", tryAnswer.getTryAnswersId())
                    .getSingleResult();
            tempResult.setRightAnswerId(rightAnswer.getAnswerId());
            tempResult.setRightAnswerText(rightAnswer.getAnswerText());
            tempResult.setRightAnswerInfo(tryAnswer.question.answerInformation);
            tempResult.setNpp(tryAnswer.getNpp());

            testResults.add(tempResult);
        }

        GetResultResponse response = new GetResultResponse();
        response.setAnswers(testResults);
        response.setAnsAmount(amount);
        response.setRightCount(rightCount.longValue());
        response.setTest(testTry);

        return response;
    }

    public BaseResponse putAnswer(PutAnswerRequest request) {
        // получаем идентификатор попытки ответа
        TryAnswers tryAnswers = TryAnswers.findById(request.getTryAnswerId());
        // Определяем не превышено ли время выполнения теста
        Integer completeTime = tryAnswers.getTestTry().getStudyProgram().getCompleteTime();
        Calendar temp = Calendar.getInstance();
        temp.setTime(tryAnswers.getTestTry().getStartDate());
        temp.add(Calendar.MINUTE, completeTime);
        Date deadline = temp.getTime();
        Date curr_time = new Date();
        if (deadline.before(curr_time)){
            return new BaseResponse("error", "Превышено время выполения теста");
        }
        // Кладём ответ
        Answer answer = Answer.findById(request.getAnswerId());
        tryAnswers.setAnswer(answer);
        tryAnswers.persist();

        return new BaseResponse("ok",
                "Ответ получен");
    }

    public BaseResponse getQuestion(GetQuestionRequest request) {

        // Список неотвеченных вопросов
        List<TryAnswers> tryAnswers = TryAnswers.find("testTryId = ?1 and answerId = null ",
                request.getTestId()).list();

        // Если вопросы кончились, возвращаем окончание теста
        if (tryAnswers.size() == 0){
            GetQuestionResponse response = new GetQuestionResponse();
            response.setComplete(true);
            return response;
        }

        // Выгружаем вопрос и ответы на него (ответы мешаем рандомно)
        Question question = tryAnswers.get(0).getQuestion();
        List<Answer> answers = new ArrayList<>(question.answers);
        shuffle(answers);

        // Общее количество вопросов в тесте
        Long amount = TryAnswers.find("testTryId = ?1", request.getTestId()).count();

        // номер вопроса
        long curr = TryAnswers.find("testTryId = ?1 and answerId != null ",
                request.getTestId()).count() + 1;

        CurrTestRespone currTestRespone = new CurrTestRespone();
        currTestRespone.setTestId(request.getTestId());
        currTestRespone.setTryAnswerId(tryAnswers.get(0).getTryAnswersId());
        currTestRespone.setQuestionId(question.questionId);
        currTestRespone.setQuestionText(question.questionText);
        currTestRespone.setAnswer(answers);
        currTestRespone.setAmount(amount);
        currTestRespone.setCurr(curr);
        GetQuestionResponse response = new GetQuestionResponse();
        response.setTest(currTestRespone);
        response.setComplete(false);

        return response;
    }

    public BaseResponse startTest(StartRequest request) {
        StudyProgram sp = StudyProgram.findById(request.getProgramId());

//        String temp_user_id = "360f4169-6812-4688-a1fd-228a874bdf04";

        Contract contract = Contract.find("user_id = ?1 and studyProgramId = ?2 and is_complete = ?3",
                jwt.getSubject(), sp.getStudyProgramId(), false).singleResult();
//                temp_user_id, sp.getStudyProgramId(), false).singleResult();

        // Сделать незавершенные - завершенными
        List<TestTry> unfinished = TestTry.find("studyProgramId = ?1 and contractId = ?2 and is_complete = false",
                sp.getStudyProgramId(), contract.getContractId()).list();
        if (unfinished.size() > 0) {
            for (TestTry test: unfinished) {
                test.endDate = new Date();
                test.isComplete = true;
                test.isSuccessful = false;
                test.persist();
            }
        }

//        Проверить, проходился ли пробный тест
        Long success_test_count =
                TestTry.find("studyProgramId = ?1 and contractId = ?2 and is_test = ?3 and is_successful = ?4",
                        sp.getStudyProgramId(), contract.getContractId(), true, true).count();
        System.out.println("Success count = " + success_test_count.toString());
        System.out.println("Get final = " + request.getFinal());
        System.out.println("Get final = " + request.getProgramId());
        if ((success_test_count == 0) && request.getFinal()){
            return new BaseResponse("error",
                    "Для прохождения итогового теста необходимо успешно пройти пробное тестирование");
        }

//        Посчитать количество потраченных попыток итогового теста
        long final_count = TestTry.find("studyProgramId = ?1 and contractId = ?2 and is_final = ?3",
                        sp.getStudyProgramId(), contract.getContractId(), true).count();
        if (final_count >= sp.getTriesCount()) {
            return new BaseResponse("error","Потрачены все попытки прохождения итогового теста");
        }

        StudyProgram studyProgram = StudyProgram.findById(request.getProgramId());
        int testQuestionCount = studyProgram.questionNums;
        List<Question> questions = Question.find("studyProgramId = ?1", sp.getStudyProgramId()).list();
        int questionCount = questions.size();
        if (questionCount < testQuestionCount) {
            return new BaseResponse("error","Не хватает вопросов.");
        }

        Collections.shuffle(questions);
        List<Question> testQuestions = new ArrayList<>();
        for (int i = 0; i < testQuestionCount; i++) {
            testQuestions.add(questions.get(i));
        }

        TestTry newTestTry = new TestTry();
        newTestTry.setStudyProgram(sp);
        newTestTry.setContract(contract);
        newTestTry.setTest(!request.getFinal());
        newTestTry.setFinal(request.getFinal());
        newTestTry.setStartDate(new Date());
        newTestTry.persist();

        int npp = 1;
        for (Question testQuestion: testQuestions){
            TryAnswers tryAnswer = new TryAnswers();
            tryAnswer.setQuestion(testQuestion);
            tryAnswer.setTestTry(newTestTry);
            tryAnswer.setNpp(npp);
            tryAnswer.persist();
        }

        StartResponse startResponse = new StartResponse();
        startResponse.setTestId(newTestTry.getTestTryId());
        startResponse.setStartDate(newTestTry.getStartDate());
        startResponse.setFinal(newTestTry.getFinal());
        startResponse.setTest(newTestTry.getTest());
        startResponse.setTestTime(sp.getCompleteTime());

        System.out.println(startResponse.toString());

        return startResponse;

    }

}
