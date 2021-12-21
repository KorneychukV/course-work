package ru.rsatu.testing;

import org.eclipse.microprofile.jwt.JsonWebToken;
import ru.rsatu.admin.adminPOJO.question.getAll.GetQuestionsRequest;
import ru.rsatu.common.BaseResponse;
import ru.rsatu.tables.*;
import ru.rsatu.testing.startTest.startRequest.StartRequest;
import ru.rsatu.testing.startTest.startRequest.StartResponse;

import javax.inject.Inject;
import java.util.*;

public class TestingService {

    @Inject
    JsonWebToken jwt;

    public BaseResponse startTest(StartRequest request) {
        StudyProgram sp = StudyProgram.findById(request.getProgramId());
        Contract contract = Contract.find("user_id = ?1 and studyProgramId = ?2 and is_complete = ?3",
                jwt.getSubject(), sp.getStudyProgramId(), false).singleResult();

        // Сделать незавершенные - заверщенными
        List<TestTry> unfinished = TestTry.find("studyProgramId = ?1 and contractId = ?2 and is_complete = ?3",
                sp.getStudyProgramId(), contract.getContractId(), false).list();
        if (unfinished.size() > 0) {
            for (TestTry test: unfinished) {
                test.endDate = new Date();
                test.isComplete = true;
                test.persist();
            }
        }

//        Проверить, проходился ли пробный тест
        long success_test_count =
                TestTry.find("studyProgramId = ?1 and contractId = ?2 and is_test = ?3 and is_succesfull = ?4",
                        sp.getStudyProgramId(), contract.getContractId(), true, true).count();
        if ((success_test_count == 0) && request.getFinal()){
            return new BaseResponse("error",
                    "Для прохождения итогового теста необходимо успешно пройти пробное тестирование");
        }

//        Посчитать количество потраченных попыток итогового теста
        long final_count = TestTry.find("studyProgramId = ?1 and contractId = ?2 and is_final",
                        sp.getStudyProgramId(), contract.getContractId(), true).count();
        if (final_count >= sp.getTriesCount()) {
            return new BaseResponse("error","Потрачены все попытки прохождения итогового теста");
        }

        List<Question> questions = Question.find("studyProgramId = ?1", sp.getStudyProgramId()).list();
        int question_count = questions.size();
        Random random = new Random();
        Set<Integer> unique = new HashSet<>();
        for (int i = 0; i < question_count; i++) {
//            TODO проверить количество в конце
            unique.add(random.nextInt(question_count));
        }

        TestTry newTestTry = new TestTry();
        newTestTry.setStudyProgram(sp);
        newTestTry.setContract(contract);
        newTestTry.setTest(!request.getFinal());
        newTestTry.setFinal(request.getFinal());
        newTestTry.persist();

        int npp = 1;
        for (int i: unique){
            TryAnswers tryAnswer = new TryAnswers();
            tryAnswer.setQuestion(questions.get(i));
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

        return startResponse;

    }

}
