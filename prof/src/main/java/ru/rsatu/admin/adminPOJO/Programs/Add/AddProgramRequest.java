package ru.rsatu.admin.adminPOJO.Programs.Add;

import javax.persistence.Column;

public class AddProgramRequest {

    private String name;
    private String description;
    public String studyGroupAlias;
    public Integer minimalDuration;
    public Integer completeTime;
    public Integer questionNums;
    public Integer triesCount;
    public Long courseId;

    public AddProgramRequest() {
    }

    public AddProgramRequest(String name, String description, String studyGroupAlias,
                          Integer minimalDuration, Integer completeTime, Integer questionNums,
                          Integer triesCount, Long courseId) {
        this.name = name;
        this.description = description;
        this.studyGroupAlias = studyGroupAlias;
        this.minimalDuration = minimalDuration;
        this.completeTime = completeTime;
        this.questionNums = questionNums;
        this.triesCount = triesCount;
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

    public String getStudyGroupAlias() {
        return studyGroupAlias;
    }

    public void setStudyGroupAlias(String studyGroupAlias) {
        this.studyGroupAlias = studyGroupAlias;
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
}
