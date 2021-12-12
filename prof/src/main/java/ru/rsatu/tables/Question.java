package ru.rsatu.tables;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity()
@Table(name = "question")
public class Question extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "question_gen")
    private Long questionId;

    @Column(name = "question_text")
    public String questionText;

    @Column(name = "image_URL")
    public String imageURL;

    @Column(name = "answer_information")
    public String answerInformation;

    @ManyToOne
    @JoinColumn(name="studyProgramId")
    public StudyProgram studyProgram;

    @OneToMany(mappedBy="questions",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Answer> answers = new HashSet<>();
}
