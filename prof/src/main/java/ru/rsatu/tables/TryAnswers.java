package ru.rsatu.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity()
@Table(name = "try_answers")
public class TryAnswers {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "try_answers_gen")
    private Long tryAnswersId;

    @Column(name = "npp")
    public Integer npp;

    @ManyToOne
    @JoinColumn(name="testTryId")
    @JsonIgnore
    public TestTry testTry;

    @ManyToOne
    @JoinColumn(name="questionId")
    @JsonIgnore
    public Question question;

    @ManyToOne
    @JoinColumn(name="answerId")
    @JsonIgnore
    public Answer answer;

}
