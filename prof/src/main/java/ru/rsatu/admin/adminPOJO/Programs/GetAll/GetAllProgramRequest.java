package ru.rsatu.admin.adminPOJO.Programs.GetAll;

public class GetAllProgramRequest {

    public Long courseId;

    public GetAllProgramRequest() {
    }

    public GetAllProgramRequest(Long courseId) {
        this.courseId = courseId;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }
}
