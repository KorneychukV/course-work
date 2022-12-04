package ru.rsatu.old.POJO.startTest.startTest;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StartRequest {

    private Long programId;
    private Boolean isFinal;

    public StartRequest() {
    }

    public StartRequest(Long programId, Boolean isFinal) {
        this.programId = programId;
        this.isFinal = isFinal;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    @JsonProperty(value = "isFinal")
    public Boolean getFinal() {
        return isFinal;
    }

    public void setFinal(Boolean isFinal) {
        this.isFinal = isFinal;
    }
}
