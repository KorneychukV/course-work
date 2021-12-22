package ru.rsatu.testing.startTest.getQuestion;

import ru.rsatu.tables.Answer;
import ru.rsatu.tables.Question;

import java.util.List;

public class CurrTestRespone {

    private Long testId;
    private Long tryAnswerId;
    private Long questionId;
    private String questionText;
    private List<Answer> answer;
    private Long amount;
    private Long curr;

    public CurrTestRespone() {
    }

    public Long getTestId() {
        return testId;
    }

    public void setTestId(Long testId) {
        this.testId = testId;
    }

    public Long getTryAnswerId() {
        return tryAnswerId;
    }

    public void setTryAnswerId(Long tryAnswerId) {
        this.tryAnswerId = tryAnswerId;
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

    public List<Answer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Answer> answer) {
        this.answer = answer;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getCurr() {
        return curr;
    }

    public void setCurr(Long curr) {
        this.curr = curr;
    }
}
