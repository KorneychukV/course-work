package ru.rsatu.admin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.rsatu.admin.adminPOJO.course.DeleteCoursesRequest;
import ru.rsatu.admin.adminPOJO.course.add.CourseRequest;
import ru.rsatu.admin.adminPOJO.course.edit.EditCourseRequest;
import ru.rsatu.admin.adminPOJO.course.getAll.CourseResponse;
import ru.rsatu.admin.adminPOJO.course.getAll.GetAllCourseRequest;
import ru.rsatu.admin.adminPOJO.literature.AddLiterRequest;
import ru.rsatu.admin.adminPOJO.literature.DeleteLitRequest;
import ru.rsatu.admin.adminPOJO.programs.DeleteProgramRequest;
import ru.rsatu.admin.adminPOJO.programs.add.AddProgramRequest;
import ru.rsatu.admin.adminPOJO.programs.edit.EditProgramRequest;
import ru.rsatu.admin.adminPOJO.programs.getAll.GetAllProgramRequest;
import ru.rsatu.admin.adminPOJO.programs.getAll.GetAllProgramsResponse;
import ru.rsatu.admin.adminPOJO.question.deleteQuestion.DeleteQuestionRequest;
import ru.rsatu.admin.adminPOJO.question.editQuestion.EditAnswer;
import ru.rsatu.admin.adminPOJO.question.editQuestion.EditQuestionRequest;
import ru.rsatu.admin.adminPOJO.question.GetAnswerRequest;
import ru.rsatu.admin.adminPOJO.question.GetAnswerResponse;
import ru.rsatu.admin.adminPOJO.question.add.*;
import ru.rsatu.admin.adminPOJO.question.add.Answer;
import ru.rsatu.admin.adminPOJO.question.getAll.GetQuestionsRequest;
import ru.rsatu.admin.adminPOJO.question.getAll.GetQuestionsResponse;
import ru.rsatu.admin.adminPOJO.studySection.DeleteSecRequest;
import ru.rsatu.admin.adminPOJO.studySection.add.SectionRequest;
import ru.rsatu.admin.adminPOJO.studySection.edit.EditSectionRequest;
import ru.rsatu.admin.adminPOJO.studySection.getAll.SectionResponse;
import ru.rsatu.common.BaseResponse;
import ru.rsatu.tables.*;
import ru.rsatu.testing.startTest.statistic.GetStatisticRequest;
import ru.rsatu.testing.startTest.statistic.Statistic;
import ru.rsatu.testing.startTest.statistic.StatisticResponse;

import javax.enterprise.context.ApplicationScoped;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@ApplicationScoped
public class AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);

    /**
     * Дабавление раздела обучения
     * @param request
     * @return
     */
    public BaseResponse addSection(SectionRequest request) {
        StudySection newSec = new StudySection();
        newSec.name = request.getName();
        newSec.description = request.getDescription();
        newSec.isDeprecated = false;
        newSec.persist();
        return new BaseResponse("ok", "Раздел обучения создан");
    }

    /**
     * Удаление раздела обучения
     * @param request
     * @return
     */
    public BaseResponse deleteSection(DeleteSecRequest request) {
        StudySection section = StudySection.findById(request.getStudySectionId());
        section.isDeprecated = true;
        section.persist();
        for (Course course: section.courses) {
            course.isDeprecated = true;
            course.persist();
            for(StudyProgram program: course.studyPrograms) {
                program.isDeprecated = true;
                program.persist();
            }
        }

        return new BaseResponse("ok", "Раздел обучения удален");
    }

    /**
     * Удаление раздела обучения
     * @param request
     * @return
     */
    public BaseResponse deleteCourses(DeleteCoursesRequest request) {
        Course course = Course.findById(request.getCourseId());
        course.isDeprecated = true;
        course.persist();
        for(StudyProgram program: course.studyPrograms) {
            program.isDeprecated = true;
            program.persist();
        }

        return new BaseResponse("ok", "Направление обучения удалено");
    }


    /**
     * Удаление программы
     * @param request
     * @return
     */
    public BaseResponse deleteProgram(DeleteProgramRequest request) {
        StudyProgram program = StudyProgram.findById(request.studyProgramId);
        program.isDeprecated = true;
        program.persist();
        return new BaseResponse("ok", "Программа удалена");
    }

    /**
     * Удаление программы
     * @param request
     * @return
     */
    public BaseResponse deleteLit(DeleteLitRequest request) {
        StudyProgramLiterature.delete("studyProgramLiteratureId", request.id);
        return new BaseResponse("ok", "Источник удален");
    }


    /**
     * РЕДАКТИРОВАНИЕ раздела обучения
     * @param request
     * @return
     */
    public BaseResponse editSection(EditSectionRequest request) {
        StudySection section = StudySection.findById(request.getStudySectionId());
        section.name = request.getName();
        section.description = request.getDescription();
        section.persist();
        return new BaseResponse("ok", "Изменения сохранены");
    }

    /**
     * Получение списка разделов обучения
     * @return
     */
    public BaseResponse getSection() {
        List<StudySection> allSec = StudySection.find("is_deprecated != true order by studySectionId")
                .list();
        return new SectionResponse(allSec.size(), allSec);
    }

    /**
     * Добавление курса
     * @param request
     * @return
     */
    public BaseResponse addCourse(CourseRequest request) {
        Course newCourse = new Course();
        newCourse.name = request.getName();
        newCourse.description = request.getDescription();
        newCourse.isDeprecated = false;
        StudySection studySection = StudySection.findById(request.getStudySectionId());
        newCourse.setStudySection(studySection);
        newCourse.persist();
        return new BaseResponse("ok", "Направление создано");
    }

    /**
     * редактирование курса
     * @param request
     * @return
     */
    public BaseResponse editCourse(EditCourseRequest request) {
        Course course = Course.findById(request.getCourseId());
        course.name = request.getName();
        course.description = request.getDescription();
        course.persist();
        return new BaseResponse("ok", "Изменения сохранены");
    }

    /**
     * Получение всех курсов
     * @return
     */
    public BaseResponse getCourse(GetAllCourseRequest request) {
        List<Course> allCourses = Course.find("studySectionId = ?1 and is_deprecated != true order by courseId", request.getStudySectionId())
                .list();
        return new CourseResponse(allCourses.size(), allCourses);
    }

    /**
     * Добавление курса
     * @param request
     * @return
     */
    public BaseResponse addProgram(AddProgramRequest request) {
        StudyProgram newProgram = new StudyProgram(request.getName(), request.getDescription(),
                request.getMinimalDuration(), request.getCompleteTime(),
                request.getQuestionNums(), request.getTriesCount(), request.getPrice());
        newProgram.isDeprecated = false;
        Course course = Course.findById(request.getCourseId());
        System.out.println(course.getName());
        newProgram.setCourse(course);
        newProgram.persist();
        return new BaseResponse("ok", "Программа обучения создана");
    }

    /**
     * Добавление вопроса
     * @param request
     * @return
     */
    public BaseResponse addQuestion(AddQuestionRequest request) {
        System.out.println(request.toString());
        try {
            StudyProgram studyProgram = StudyProgram.findById(request.programId);
            Question question = new Question();
            question.questionText = request.questionText;;
            question.answerInformation = request.answerInformation;
            question.studyProgram = studyProgram;
            question.persist();
            ArrayList<ru.rsatu.tables.Answer> answerList = new ArrayList<>();
            for (Answer answer: request.getAnswerList())
                {
                    ru.rsatu.tables.Answer newAnswer = new ru.rsatu.tables.Answer(answer.getAnswerText(), answer.rightAnswer, question);
                    newAnswer.persist();
                }
            return new BaseResponse("ok", "Программа обучения создана");
        } catch (Exception ex) {
            logger.error(String.valueOf(ex));
            return new BaseResponse("error", "Ошибка при добавлении");
        }
    }

    /**
     * удаление вопроса
     * @param request
     * @return
     */
    public BaseResponse deleteQuestion(DeleteQuestionRequest request) {
        try {
            Question question = Question.findById(request.getQuestionId());
            question.delete();
            return new BaseResponse("ok", "Вопрос удален");
        } catch (Exception ex) {
            logger.error(String.valueOf(ex));
            return new BaseResponse("error", "Ошибка при удалении");
        }
    }

    /**
     * Изменение вопроса
     * @param request
     * @return
     */
    public BaseResponse editQuestion(EditQuestionRequest request) {
        Question question = Question.findById(request.questionId);
        question.questionText = request.questionText;;
        question.answerInformation = request.answerInformation;
        question.persist();
        List<ru.rsatu.tables.Answer> answers = ru.rsatu.tables.Answer
                .find("questionId = ?1 order by answerId", request.questionId)
                .list();
        try {
            for(EditAnswer editAnswer: request.answerList) {
                for(ru.rsatu.tables.Answer answer: answers ) {
                    if (Objects.equals(editAnswer.answerId, answer.answerId)) {
                        answer.answerText = editAnswer.answerText;
                        answer.isRight = editAnswer.rightAnswer;
                        answer.persist();
                    }
                }
                if (editAnswer.answerId == null) {
                    ru.rsatu.tables.Answer newAnswer =
                            new ru.rsatu.tables.Answer(editAnswer.answerText, editAnswer.rightAnswer, question);
                    newAnswer.persist();
                } else if (editAnswer.delete) {
                    System.out.println();
                    ru.rsatu.tables.Answer deleteAnswer = ru.rsatu.tables.Answer.findById(editAnswer.answerId);
                    deleteAnswer.delete();
                }
            }
            return new BaseResponse("ok", "Изменения сохранены");
        } catch (Exception ex) {
            logger.error(String.valueOf(ex));
            return new BaseResponse("error", "Ошибка при изменении");
        }
    }

    /**
     * Выгрузка вопросов
     * @param request
     * @return
     */
    public BaseResponse getQuestion(GetQuestionsRequest request) {
        try {
            List<Question> allQuestion = Question.find("studyProgramId = ?1 order by questionId", request.getStudyProgramId())
                    .list();
            return new GetQuestionsResponse( allQuestion.size(), allQuestion);
        } catch (Exception ex) {
            logger.error(String.valueOf(ex));
            return new BaseResponse("error", "Ошибка при добавлении");
        }
    }

    /**
     * Получение всех программ
     * @return
     */
    public BaseResponse getPrograms(GetAllProgramRequest request) {
        List<StudyProgram> allPrograms = StudyProgram.find("courseId = ?1 and isDeprecated = false order by studyProgramId", request.getCourseId())
                .list();
        return new GetAllProgramsResponse(allPrograms.size(), allPrograms);
    }

    /**
     * редактирование программы
     * @param request
     * @return
     */
    public BaseResponse editPrograms(EditProgramRequest request) {
        StudyProgram program = StudyProgram.findById(request.getStudyProgramId());
        System.out.println(program.getStudyProgramId());
        program.name = request.getName();
        program.description = request.getDescription();
        program.completeTime = request.getCompleteTime();
        program.price = request.getPrice();
        program.minimalDuration = request.getMinimalDuration();
        program.questionNums = request.getQuestionNums();
        program.triesCount = request.getTriesCount();
        program.persist();
        return new BaseResponse("ok", "Изменения сохранены");
    }

    public BaseResponse getAnswer(GetAnswerRequest request) {
        List<ru.rsatu.tables.Answer> answers = ru.rsatu.tables.Answer
                .find("questionId = ?1 order by answerId", request.getQuestionId())
                .list();
        return new GetAnswerResponse(answers);
    }

    /**
     * Добавление литературы
     * @return
     */
    public BaseResponse addLiter(AddLiterRequest request) {
        try {
            StudyProgram studyProgram = StudyProgram.findById(request.studyProgramId);
            StudyProgramLiterature liter = new StudyProgramLiterature();
            liter.title = request.title;
            liter.description = request.description;
            liter.link = request.link;
            liter.studyProgram = studyProgram;
            liter.persist();
            return new BaseResponse("ok", "Литература добавлена");
        } catch (Exception ex) {
            logger.error(String.valueOf(ex));
            return new BaseResponse("error", "Ошибка при добавлении");
        }
    }

    public BaseResponse getStatistic(GetStatisticRequest request){

        List<Contract> contracts = Contract
                .find("username like ?1", '%'+request.getUsername()+'%')
                .page(request.getPageNumber(), request.getPageSize())
                .list();

        List<Statistic> statistics = new ArrayList<>();
        for (Contract contract: contracts){
            Statistic temp = new Statistic();
            temp.setContractId(contract.getContractId());
            temp.setUsername(contract.username);
            temp.setProgramId(contract.getStudyProgram().getStudyProgramId());
            temp.setProgramName(contract.getStudyProgram().getName());
            Long testAmount = TestTry
                    .find("studyProgramId = ?1 and contractId = ?2 and is_test = True",
                            temp.getProgramId(), temp.getContractId()).count();
            Long testSuccAmount = TestTry
                    .find("studyProgramId = ?1 and contractId = ?2 and is_test = True and is_successful = True",
                            temp.getProgramId(), temp.getContractId()).count();
            Long finalAmount = TestTry
                    .find("studyProgramId = ?1 and contractId = ?2 and is_final = True",
                            temp.getProgramId(), temp.getContractId()).count();
            Long finalFailAmount = TestTry
                    .find("studyProgramId = ?1 and contractId = ?2 and is_final = True and is_successful = True",
                            temp.getProgramId(), temp.getContractId()).count();
            temp.setTestAmount(testAmount);
            temp.setTestSuccAmount(testSuccAmount);
            temp.setFinal_amount(finalAmount);
            temp.setFinal_fail_amount(finalFailAmount);
            statistics.add(temp);
        }

        StatisticResponse statisticResponse = new StatisticResponse();
        statisticResponse.setStatistics(statistics);

        Long cnt = Contract
                .find("username like ?1", '%'+request.getUsername()+'%')
                .count();
        statisticResponse.setCountPage((long) Math.ceil(cnt / (double) request.getPageSize()));

        return statisticResponse;
    }
}
