package ru.rsatu.old.POJO.admin.programs.add;

public class AddProgramRequest {

    private String name;
    private String description;
    private Integer minimalDuration;
    private Integer completeTime;
    private Integer questionNums;
    private Integer triesCount;
    private Integer price;
    private Long courseId;

    public AddProgramRequest() {
    }

    public AddProgramRequest(String name, String description, Integer minimalDuration, Integer completeTime,
                             Integer questionNums, Integer triesCount, Integer price, Long courseId) {
        this.name = name;
        this.description = description;
        this.minimalDuration = minimalDuration;
        this.completeTime = completeTime;
        this.questionNums = questionNums;
        this.triesCount = triesCount;
        this.price = price;
        this.courseId = courseId;
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

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
