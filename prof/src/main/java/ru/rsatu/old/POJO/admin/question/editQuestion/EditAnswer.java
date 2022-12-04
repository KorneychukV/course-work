package ru.rsatu.old.POJO.admin.question.editQuestion;

public class EditAnswer {

    public String answerText;
    public boolean rightAnswer;
    public boolean delete;
    public Long answerId;

    public EditAnswer() {
    }

    public EditAnswer(String answerText, boolean rightAnswer, boolean delete, Long answerId) {
        this.answerText = answerText;
        this.rightAnswer = rightAnswer;
        this.delete = delete;
        this.answerId = answerId;
    }
}
