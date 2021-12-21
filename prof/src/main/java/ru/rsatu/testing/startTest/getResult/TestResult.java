package ru.rsatu.testing.startTest.getResult;

public class TestResult {
    private Long questionId;
    private String questionText;
    private Long givenAnswerId;
    private String givenAnswerText;
    private Long rightAnswerId;
    private String rightAnswerText;
    private String rightAnswerInfo;
    private Integer npp;

    public TestResult() {
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public Long getGivenAnswerId() {
        return givenAnswerId;
    }

    public void setGivenAnswerId(Long givenAnswerId) {
        this.givenAnswerId = givenAnswerId;
    }

    public String getGivenAnswerText() {
        return givenAnswerText;
    }

    public void setGivenAnswerText(String givenAnswerText) {
        this.givenAnswerText = givenAnswerText;
    }

    public Long getRightAnswerId() {
        return rightAnswerId;
    }

    public void setRightAnswerId(Long rightAnswerId) {
        this.rightAnswerId = rightAnswerId;
    }

    public String getRightAnswerText() {
        return rightAnswerText;
    }

    public void setRightAnswerText(String rightAnswerText) {
        this.rightAnswerText = rightAnswerText;
    }

    public String getRightAnswerInfo() {
        return rightAnswerInfo;
    }

    public void setRightAnswerInfo(String rightAnswerInfo) {
        this.rightAnswerInfo = rightAnswerInfo;
    }

    public Integer getNpp() {
        return npp;
    }

    public void setNpp(Integer npp) {
        this.npp = npp;
    }
}
