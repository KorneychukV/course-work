package ru.rsatu.old.POJO.admin.question;

import ru.rsatu.common.BaseResponse;
import ru.rsatu.models.Answer;

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
