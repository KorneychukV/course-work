package ru.rsatu.old.POJO.user.program;

public class StudyProgramByUser {

    private Long studyProgramId;

    private String name;

    private String description;

    private Boolean isComplete;

    public StudyProgramByUser() {
    }

    public StudyProgramByUser(Long studyProgramId, String name, String description, Boolean isComplete) {
        this.studyProgramId = studyProgramId;
        this.name = name;
        this.description = description;
        this.isComplete = isComplete;
    }

    public Long getStudyProgramId() {
        return studyProgramId;
    }

    public void setStudyProgramId(Long studyProgramId) {
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

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }
}
