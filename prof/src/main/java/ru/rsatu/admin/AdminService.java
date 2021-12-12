package ru.rsatu.admin;

import ru.rsatu.admin.adminPOJO.Course.Add.CourseRequest;
import ru.rsatu.admin.adminPOJO.Course.GetAll.CourseResponse;
import ru.rsatu.admin.adminPOJO.Course.GetAll.GetAllCourseRequest;
import ru.rsatu.admin.adminPOJO.Programs.Add.AddProgramRequest;
import ru.rsatu.admin.adminPOJO.Programs.GetAll.GetAllProgramRequest;
import ru.rsatu.admin.adminPOJO.Programs.GetAll.GetAllProgramsResponse;
import ru.rsatu.admin.adminPOJO.StudySection.Add.SectionRequest;
import ru.rsatu.admin.adminPOJO.StudySection.GetAll.SectionResponse;
import ru.rsatu.common.BaseResponse;
import ru.rsatu.tables.Course;
import ru.rsatu.tables.StudyProgram;
import ru.rsatu.tables.StudySection;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class AdminService {

    /**
     * Дабавление раздела обучения
     * @param request
     * @return
     */
    public BaseResponse addSection(SectionRequest request) {
        StudySection newSec = new StudySection();
        newSec.name = request.getName();
        newSec.description = request.getDescription();
        newSec.persist();
        return new BaseResponse("ok", "Раздел обучения создан");
    }

    /**
     * Получение списка разделов обучения
     * @return
     */
    public BaseResponse getSection() {
        List<StudySection> allSec = StudySection.listAll();
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
        StudySection studySection = StudySection.findById(request.getStudySectionId());
        newCourse.setStudySection(studySection);
        newCourse.persist();
        return new BaseResponse("ok", "Курс создан");
    }

    /**
     * Получение всех курсов
     * @return
     */
    public BaseResponse getCourse(GetAllCourseRequest request) {
        List<Course> allCourses = Course.find("studySectionId", request.getStudySectionId()).list();
        return new CourseResponse(allCourses.size(), allCourses);
    }

    /**
     * Добавление курса
     * @param request
     * @return
     */
    public BaseResponse addProgram(AddProgramRequest request) {
        StudyProgram newProgram = new StudyProgram(request.getName(), request.getDescription(),
                request.getStudyGroupAlias(), request.getMinimalDuration(), request.getCompleteTime(),
                request.getQuestionNums(), request.getTriesCount());
        Course course = Course.findById(request.courseId);
        System.out.println(course.getName());
        newProgram.setCourse(course);
        newProgram.persist();
        return new BaseResponse("ok", "Программа обучения создана");
    }

    /**
     * Получение всех курсов
     * @return
     */
    public BaseResponse getPrograms(GetAllProgramRequest request) {
        System.out.println(request.courseId);
        List<StudyProgram> allPrograms = StudyProgram.find("courseId", request.getCourseId()).list();
        return new GetAllProgramsResponse(allPrograms.size(), allPrograms);
    }
}
