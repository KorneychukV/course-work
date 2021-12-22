package ru.rsatu.admin.adminPOJO.programs;

public class GetProgramIdRequest {

    private Long studyProgramId;
    private String filter;

    public GetProgramIdRequest() {
    }

    public GetProgramIdRequest(Long studyProgramId, String filter) {
        this.studyProgramId = studyProgramId;
        this.filter = filter;
    }

    public Long getStudyProgramId() {
        return studyProgramId;
    }

    public void setStudyProgramId(Long studyProgramId) {
        this.studyProgramId = studyProgramId;
    }

    public String getFilter() {
        if (filter == null) {
            return "";
        }
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }
}
