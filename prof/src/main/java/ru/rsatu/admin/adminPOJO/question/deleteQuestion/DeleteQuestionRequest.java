package ru.rsatu.admin.adminPOJO.question.deleteQuestion;

public class DeleteQuestionRequest {
    private Long questionId;

    public DeleteQuestionRequest() {
    }

    public DeleteQuestionRequest(Long questionId) {
        this.questionId = questionId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
