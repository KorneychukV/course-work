package ru.rsatu.admin.adminPOJO.question.add;

public class Answer {

    public String answerText;
    public boolean rightAnswer;


    public Answer() {
    }

    public Answer(String answerText, boolean rightAnswer) {
        this.answerText = answerText;
        this.rightAnswer = rightAnswer;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public boolean isRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(boolean rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public String toString() {
        return "Answer{" +
                "answerText='" + answerText + '\'' +
                ", rightAnswer=" + rightAnswer +
                '}';
    }
}
