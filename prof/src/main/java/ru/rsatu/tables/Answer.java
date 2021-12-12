package ru.rsatu.tables;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity()
@Table(name = "answer")
public class Answer extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "answer_gen")
    private Long answerId;

    @Column(name = "answer_text")
    public String answerText;

    @Column(name = "isRight")
    public String isRight;

    @ManyToOne
    @JoinColumn(name="questionId")
    public Question question;


}
