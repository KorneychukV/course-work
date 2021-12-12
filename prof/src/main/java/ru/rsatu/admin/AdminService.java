package ru.rsatu.admin;

import ru.rsatu.admin.adminPOJO.SectionRequest;
import ru.rsatu.admin.adminPOJO.SectionResponse;
import ru.rsatu.common.BaseResponse;
import ru.rsatu.tables.StudySection;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class AdminService {

    public BaseResponse addSection(SectionRequest request) {
        System.out.println(request.getDescription());
        StudySection newSec = new StudySection();
        newSec.name = request.getName();
        newSec.description = request.getDescription();
        newSec.persist();
        return new BaseResponse("ok", "Раздел обучения создан");
    }

    public BaseResponse getSection() {
        List<StudySection> allSec = StudySection.listAll();

        return new SectionResponse(allSec.size(), allSec);
    }
}
