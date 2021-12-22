package ru.rsatu.admin.adminPOJO.literature;

public class AddLiterRequest {

    public String title;

    public String link;

    public String youtubeLink;

    public String documentURL;

    public String description;

    public Long studyProgramId;

    public AddLiterRequest() {
    }

    public AddLiterRequest(String title, String link, String youtubeLink, String documentURL,
                           String description, Long studyProgramId) {
        this.title = title;
        this.link = link;
        this.youtubeLink = youtubeLink;
        this.documentURL = documentURL;
        this.description = description;
        this.studyProgramId = studyProgramId;
    }
}
