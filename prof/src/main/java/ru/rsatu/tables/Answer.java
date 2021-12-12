package ru.rsatu.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity()
@Table(name = "answer")
public class Answer extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "answer_gen")
    private Long answerId;

    @Column(name = "answer_text")
    public String answerText;

    @Column(name = "is_right")
    public Boolean isRight;

    @ManyToOne
    @JoinColumn(name="questionId")
    @JsonIgnore
    public Question question;

    @OneToMany(mappedBy="answer",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<TryAnswers> tryAnswers = new HashSet<>();

}
