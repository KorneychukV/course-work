package ru.rsatu.old.POJO.literature;

import ru.rsatu.common.BaseResponse;
import ru.rsatu.models.StudyProgramLiterature;

import java.util.ArrayList;
import java.util.List;

public class GetLiteratureResponse extends BaseResponse {

    private List<StudyProgramLiterature> list = new ArrayList<>();

    public GetLiteratureResponse() {
    }

    public GetLiteratureResponse(List<StudyProgramLiterature> list) {
        this.list = list;
    }

    public List<StudyProgramLiterature> getList() {
        return list;
    }

    public void setList(List<StudyProgramLiterature> list) {
        this.list = list;
    }
}
