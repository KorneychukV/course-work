package ru.rsatu.user;

import io.quarkus.security.identity.SecurityIdentity;
import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.resteasy.annotations.cache.NoCache;
import ru.rsatu.admin.adminPOJO.programs.getAll.GetAllProgramRequest;
import ru.rsatu.admin.adminPOJO.programs.getAll.GetAllProgramsResponse;
import ru.rsatu.admin.adminPOJO.studySection.getAll.SectionResponse;
import ru.rsatu.common.BaseResponse;
import ru.rsatu.tables.Contract;
import ru.rsatu.tables.Course;
import ru.rsatu.tables.StudyProgram;
import ru.rsatu.tables.StudySection;
import ru.rsatu.user.userPOJO.BuyProgramRequest;
import ru.rsatu.user.userPOJO.program.GetProgramsResponse;
import ru.rsatu.user.userPOJO.program.ProgramsByUserResponse;
import ru.rsatu.user.userPOJO.program.StudyProgramBuy;
import ru.rsatu.user.userPOJO.program.StudyProgramByUser;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    EntityManager em;

    @Inject
    JsonWebToken jwt;

    /**
     * Покупка
     * @return
     */
    public BaseResponse buyProgram(BuyProgramRequest request, String username) {

        StudyProgram program = StudyProgram.findById(request.studyProgramId);
        Contract contract = new Contract(jwt.getSubject(), username, program, false);
        contract.persist();
        return new BaseResponse("ok", "Покупкааа");
    }

    /**
     * Получение всех программ
     * @return
     */
    public BaseResponse getPrograms(GetAllProgramRequest request) {

        Query query = em.createQuery("select new ru.rsatu.user.userPOJO.program.StudyProgramBuy(" +
                "study.studyProgramId, study.name, study.description, study.price,\n" +
                "                 c.contractId) from StudyProgram study\n" +
                "                left join Contract c on study.studyProgramId = c.studyProgram.studyProgramId and c.userID like :userid \n" +
                "                where study.course.courseId = :paramCourseId");
        query.setParameter("paramCourseId", request.courseId);
        query.setParameter("userid", jwt.getSubject());
        List<StudyProgramBuy> list = query.getResultList();
        return new GetProgramsResponse(list);
    }

    /**
     * Получение всех программ
     * @return
     */
    public BaseResponse programsByUser() {
        Query query = em.createQuery("select new ru.rsatu.user.userPOJO.program.StudyProgramByUser(" +
                "study.studyProgramId, study.name, study.description,\n" +
                "                 c.isComplete) from StudyProgram study\n" +
                "                left join Contract c on study.studyProgramId = c.studyProgram.studyProgramId \n" +
                "                where  c.userID like :userid ");
        query.setParameter("userid", jwt.getSubject());
        List<StudyProgramByUser> list = query.getResultList();
        System.out.println(list.size());
        return new ProgramsByUserResponse(list);
    }
}
