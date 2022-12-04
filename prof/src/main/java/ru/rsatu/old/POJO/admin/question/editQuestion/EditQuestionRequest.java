package ru.rsatu.old.POJO.admin.question.editQuestion;

import java.util.ArrayList;
import java.util.List;

public class EditQuestionRequest {

    public String questionText;
    public String imageURL;
    public String answerInformation;
    public Long questionId;
    public List<EditAnswer> answerList = new ArrayList<>();

    public EditQuestionRequest() {
    }

    public EditQuestionRequest(String questionText, String imageURL, String answerInformation, Long questionId, List<EditAnswer> answerList) {
        this.questionText = questionText;
        this.imageURL = imageURL;
        this.answerInformation = answerInformation;
        this.questionId = questionId;
        this.answerList = answerList;
    }
}
