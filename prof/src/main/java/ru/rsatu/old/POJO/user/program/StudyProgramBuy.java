package ru.rsatu.old.POJO.user.program;

public class StudyProgramBuy {

    private Long studyProgramId;

    private String name;

    private String description;

    private Integer price;

    private Long contractId;

    public StudyProgramBuy() {
    }

    public StudyProgramBuy(Long studyProgramId, String name, String description, Integer price, Long contractId) {
        this.studyProgramId = studyProgramId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.contractId = contractId;
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

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }
}
