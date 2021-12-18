package ru.rsatu.admin.adminPOJO.question.add;

import java.util.ArrayList;
import java.util.List;

public class AddQuestionRequest {

    public String questionText;
    public String imageURL;
    public String answerInformation;
    public Long programId;
    private List<Answer> answerList = new ArrayList<>();

    public AddQuestionRequest() {
    }

    public AddQuestionRequest(String questionText, String imageURL, String answerInformation, Long programId, List<Answer> answerList) {
        this.questionText = questionText;
        this.imageURL = imageURL;
        this.answerInformation = answerInformation;
        this.programId = programId;
        this.answerList = answerList;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public List<Answer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<Answer> answerList) {
        this.answerList = answerList;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getAnswerInformation() {
        return answerInformation;
    }

    public void setAnswerInformation(String answerInformation) {
        this.answerInformation = answerInformation;
    }

    @Override
    public String toString() {
        return "AddQuestionRequest{" +
                "questionText='" + questionText + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", answerInformation='" + answerInformation + '\'' +
                ", programId=" + programId +
                ", answerList=" + answerList +
                '}';
    }
}
