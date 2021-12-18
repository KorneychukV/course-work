package ru.rsatu.admin.adminPOJO.studySection;

public class DeleteSecRequest {
    private Long studySectionId;

    public DeleteSecRequest() {
    }

    public DeleteSecRequest(Long studySectionId) {
        this.studySectionId = studySectionId;
    }

    public Long getStudySectionId() {
        return studySectionId;
    }

    public void setStudySectionId(Long studySectionId) {
        this.studySectionId = studySectionId;
    }
}
