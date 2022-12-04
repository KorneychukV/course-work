package ru.rsatu.old.POJO.user.program;

import ru.rsatu.common.BaseResponse;

import java.util.ArrayList;
import java.util.List;

public class GetProgramsResponse extends BaseResponse {

    private List<StudyProgramBuy> list = new ArrayList<>();

    public GetProgramsResponse() {
    }
    public GetProgramsResponse(List<StudyProgramBuy> list) {
        this.list = list;
    }

    public List<StudyProgramBuy> getList() {
        return list;
    }

    public void setList(List<StudyProgramBuy> list) {
        this.list = list;
    }
}
