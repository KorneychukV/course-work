package ru.rsatu.admin.adminPOJO.studySection.edit;

public class EditSectionRequest {

    private String name;
    private String description;
    private Long studySectionId;

    public EditSectionRequest() {
    }

    public EditSectionRequest(String name, String description, Long studySectionId) {
        this.name = name;
        this.description = description;
        this.studySectionId = studySectionId;
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

    public Long getStudySectionId() {
        return studySectionId;
    }

    public void setStudySectionId(Long studySectionId) {
        this.studySectionId = studySectionId;
    }
}
