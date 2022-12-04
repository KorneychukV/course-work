package ru.rsatu.old.POJO.admin.question.getAll;

import ru.rsatu.common.BaseResponse;
import ru.rsatu.models.Question;

import java.util.ArrayList;
import java.util.List;

public class GetQuestionsResponse extends BaseResponse {

    private int contentSize;
    private List<Question> list = new ArrayList<>();

    public GetQuestionsResponse() {
    }

    public GetQuestionsResponse(int contentSize, List<Question> list) {
        this.contentSize = contentSize;
        this.list = list;
    }

    public int getContentSize() {
        return contentSize;
    }

    public void setContentSize(int contentSize) {
        this.contentSize = contentSize;
    }

    public List<Question> getList() {
        return list;
    }

    public void setList(List<Question> list) {
        this.list = list;
    }
}
