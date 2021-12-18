package ru.rsatu.admin.adminPOJO.programs.edit;

public class EditProgramRequest {
    private String name;
    private String description;
    private Integer minimalDuration;
    private Integer completeTime;
    private Integer questionNums;
    private Integer triesCount;
    private Integer price;
    private Long studyProgramId;

    public EditProgramRequest() {
    }

    public EditProgramRequest(String name, String description, Integer minimalDuration, Integer completeTime,
                              Integer questionNums, Integer triesCount, Integer price, Long studyProgramId) {
        this.name = name;
        this.description = description;
        this.minimalDuration = minimalDuration;
        this.completeTime = completeTime;
        this.questionNums = questionNums;
        this.triesCount = triesCount;
        this.price = price;
        this.studyProgramId = studyProgramId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getMinimalDuration() {
        return minimalDuration;
    }

    public void setMinimalDuration(Integer minimalDuration) {
        this.minimalDuration = minimalDuration;
    }

    public Integer getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Integer completeTime) {
        this.completeTime = completeTime;
    }

    public Integer getQuestionNums() {
        return questionNums;
    }

    public void setQuestionNums(Integer questionNums) {
        this.questionNums = questionNums;
    }

    public Integer getTriesCount() {
        return triesCount;
    }

    public void setTriesCount(Integer triesCount) {
        this.triesCount = triesCount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getStudyProgramId() {
        return studyProgramId;
    }

    public void setStudyProgramId(Long studyProgramId) {
        this.studyProgramId = studyProgramId;
    }

    @Override
    public String toString() {
        return "EditProgramRequest{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", minimalDuration=" + minimalDuration +
                ", completeTime=" + completeTime +
                ", questionNums=" + questionNums +
                ", triesCount=" + triesCount +
                ", price=" + price +
                ", studyProgramId=" + studyProgramId +
                '}';
    }
}
