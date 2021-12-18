package ru.rsatu.admin.adminPOJO.question;

public class GetAnswerRequest {

    private Long questionId;

    public GetAnswerRequest() {
    }

    public GetAnswerRequest(Long questionId) {
        this.questionId = questionId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
