package ru.rsatu.admin.adminPOJO.question;

import ru.rsatu.common.BaseResponse;
import ru.rsatu.tables.Answer;
import ru.rsatu.tables.Question;

import java.util.ArrayList;
import java.util.List;

public class GetAnswerResponse extends BaseResponse {

    private List<Answer> list = new ArrayList<>();

    public GetAnswerResponse() {
    }

    public GetAnswerResponse(List<Answer> list) {
        this.list = list;
    }

    public List<Answer> getList() {
        return list;
    }

    public void setList(List<Answer> list) {
        this.list = list;
    }
}
