package ru.rsatu.testing.startTest.startRequest;

public class StartRequest {

    private Long programId;
    private Boolean isFinal;

    public StartRequest() {
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Boolean getFinal() {
        return isFinal;
    }

    public void setFinal(Boolean aFinal) {
        isFinal = aFinal;
    }
}
