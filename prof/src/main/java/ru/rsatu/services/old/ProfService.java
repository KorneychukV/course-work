package ru.rsatu.services.old;

import ru.rsatu.old.POJO.admin.studySection.getAll.SectionResponse;
import ru.rsatu.common.BaseResponse;
import ru.rsatu.models.StudySection;

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

//    /**
//     * Получение всех курсов
//     * @return
//     */
//    public BaseResponse getCourse(GetAllCourseRequest request) {
//        List<Course> allCourses = Course.find("studySectionId = ?1 and is_deprecated != true order by courseId", request.getStudySectionId())
//                .list();
//        return new CourseResponse(allCourses.size(), allCourses);
//    }


//    public BaseResponse getLiterature(GetProgramIdRequest request) {
//        List<StudyProgramLiterature> allLit = StudyProgramLiterature.find("title like ?2 and studyProgramId = ?1 " +
//                        " order by studyProgramLiteratureId",
//                        request.getStudyProgramId(), "%" + request.getFilter()+"%")
//                .list();
//        return new GetLiteratureResponse(allLit);
//    }
//
//    /**
//     * Получение всех курсов
//     * @return
//     */
//    public BaseResponse getPrograms(GetAllProgramRequest request) {
//        List<StudyProgram> allPrograms = StudyProgram.find("courseId = ?1 order by studyProgramId", request.getCourseId())
//                .list();
//        return new GetAllProgramsResponse(allPrograms.size(), allPrograms);
//    }

}
