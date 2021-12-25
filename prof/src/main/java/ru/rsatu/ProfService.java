package ru.rsatu;

import ru.rsatu.POJO.literature.GetLiteratureResponse;
import ru.rsatu.admin.adminPOJO.course.getAll.CourseResponse;
import ru.rsatu.admin.adminPOJO.course.getAll.GetAllCourseRequest;
import ru.rsatu.admin.adminPOJO.programs.GetProgramIdRequest;
import ru.rsatu.admin.adminPOJO.programs.getAll.GetAllProgramRequest;
import ru.rsatu.admin.adminPOJO.programs.getAll.GetAllProgramsResponse;
import ru.rsatu.admin.adminPOJO.studySection.getAll.SectionResponse;
import ru.rsatu.common.BaseResponse;
import ru.rsatu.tables.Course;
import ru.rsatu.tables.StudyProgram;
import ru.rsatu.tables.StudyProgramLiterature;
import ru.rsatu.tables.StudySection;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class ProfService {

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
     * Получение всех курсов
     * @return
     */
    public BaseResponse getCourse(GetAllCourseRequest request) {
        List<Course> allCourses = Course.find("studySectionId = ?1 and is_deprecated != true order by courseId", request.getStudySectionId())
                .list();
        return new CourseResponse(allCourses.size(), allCourses);
    }


    public BaseResponse getLiterature(GetProgramIdRequest request) {
        System.out.println(request.getFilter() + "kjnk");
        List<StudyProgramLiterature> allLit = StudyProgramLiterature.find("title like ?2 and studyProgramId = ?1 " +
                        " order by studyProgramLiteratureId",
                        request.getStudyProgramId(), "%" + request.getFilter()+"%")
                .list();
        return new GetLiteratureResponse(allLit);
    }

    /**
     * Получение всех курсов
     * @return
     */
    public BaseResponse getPrograms(GetAllProgramRequest request) {
        List<StudyProgram> allPrograms = StudyProgram.find("courseId = ?1 order by studyProgramId", request.getCourseId())
                .list();
        return new GetAllProgramsResponse(allPrograms.size(), allPrograms);
    }

}
