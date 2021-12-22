package ru.rsatu.testing.startTest.statistic;

public class Statistic {

    private Long contractId;
    private String username;
    private Long programId;
    private String programName;
    private Long testAmount;
    private Long testSuccAmount;
    private Long final_amount;
    private Long final_fail_amount;

    public Statistic() {
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public Long getTestAmount() {
        return testAmount;
    }

    public void setTestAmount(Long testAmount) {
        this.testAmount = testAmount;
    }

    public Long getTestSuccAmount() {
        return testSuccAmount;
    }

    public void setTestSuccAmount(Long testSuccAmount) {
        this.testSuccAmount = testSuccAmount;
    }

    public Long getFinal_amount() {
        return final_amount;
    }

    public void setFinal_amount(Long final_amount) {
        this.final_amount = final_amount;
    }

    public Long getFinal_fail_amount() {
        return final_fail_amount;
    }

    public void setFinal_fail_amount(Long final_fail_amount) {
        this.final_fail_amount = final_fail_amount;
    }
}
