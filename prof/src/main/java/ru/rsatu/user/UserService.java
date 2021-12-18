package ru.rsatu.user;

import io.quarkus.security.identity.SecurityIdentity;
import org.eclipse.microprofile.jwt.JsonWebToken;
import ru.rsatu.admin.adminPOJO.studySection.getAll.SectionResponse;
import ru.rsatu.common.BaseResponse;
import ru.rsatu.tables.Contract;
import ru.rsatu.tables.StudyProgram;
import ru.rsatu.tables.StudySection;
import ru.rsatu.user.userPOJO.BuyProgramRequest;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UserService {

    @Inject
    SecurityIdentity securityIdentity;

    @Inject
    JsonWebToken jwt;

    /**
     * Покупка
     * @return
     */
    public BaseResponse buyProgram(BuyProgramRequest request) {
        StudyProgram program = StudyProgram.findById(request.studyProgramId);
        Contract contract = new Contract(jwt.getSubject(), program);
        contract.persist();
        return new BaseResponse("ok", "Покупкааа");
    }
}
