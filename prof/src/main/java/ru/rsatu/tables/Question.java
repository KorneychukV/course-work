package ru.rsatu.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    public StudyProgram studyProgram;

    @OneToMany(mappedBy="question",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Answer> answers = new HashSet<>();

    @OneToMany(mappedBy="question",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<TryAnswers> tryAnswers = new HashSet<>();
}
