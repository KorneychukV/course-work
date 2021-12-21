package ru.rsatu.testing.startTest.putAnswer;

public class PutAnswerRequest {

    private Long tryAnswerId;
    private Long answerId;

    public PutAnswerRequest() {
    }

    public Long getTryAnswerId() {
        return tryAnswerId;
    }

    public void setTryAnswerId(Long tryAnswerId) {
        this.tryAnswerId = tryAnswerId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    @Override
    public String toString() {
        return "PutAnswerRequest{" +
                "tryAnswerId=" + tryAnswerId +
                ", answerId=" + answerId +
                '}';
    }
}
