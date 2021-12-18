package ru.rsatu.admin.adminPOJO.programs.getAll;

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
