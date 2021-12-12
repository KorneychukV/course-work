package ru.rsatu.tables;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity()
@Table(name = "study_program_literature")
public class StudyProgramLiterature extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "studyProgramLiterature_gen")
    private Long studyProgramLiteratureId;

    @Column(name = "title")
    public String title;

    @Column(name = "link")
    public String link;

    @Column(name = "youtube_link")
    public String youtubeLink;

    @Column(name = "document_url")
    public String documentURL;

    @Column(name = "description")
    public String description;

    @ManyToOne
    @JoinColumn(name="studyProgramId")
    public StudyProgram studyProgram;


}
