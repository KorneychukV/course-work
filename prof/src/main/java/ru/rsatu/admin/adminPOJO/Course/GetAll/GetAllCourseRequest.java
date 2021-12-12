package ru.rsatu.admin.adminPOJO.Course.GetAll;

public class GetAllCourseRequest {

    private Long studySectionId;

    public GetAllCourseRequest() {
    }

    public GetAllCourseRequest(Long studySectionId) {
        this.studySectionId = studySectionId;
    }

    public Long getStudySectionId() {
        return studySectionId;
    }

    public void setStudySectionId(Long studySectionId) {
        this.studySectionId = studySectionId;
    }
}
