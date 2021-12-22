package ru.rsatu.testing.startTest.getQuestion;

import ru.rsatu.common.BaseResponse;

public class GetQuestionResponse extends BaseResponse {

    private CurrTestRespone test;
    private Boolean isComplete;

    public GetQuestionResponse() {
    }

    public CurrTestRespone getTest() {
        return test;
    }

    public void setTest(CurrTestRespone test) {
        this.test = test;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }
}
