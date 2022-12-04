package ru.rsatu.old.POJO.admin.literature;

public class AddLiteratureRequest {

    public String title;

    public String link;

    public String youtubeLink;

    public String documentURL;

    public String description;

    public Long studyProgramId;

    public AddLiteratureRequest() {
    }

    public AddLiteratureRequest(String title, String link, String youtubeLink, String documentURL,
                                String description, Long studyProgramId) {
        this.title = title;
        this.link = link;
        this.youtubeLink = youtubeLink;
        this.documentURL = documentURL;
        this.description = description;
        this.studyProgramId = studyProgramId;
    }
}
