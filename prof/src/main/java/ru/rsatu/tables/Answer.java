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
    public Long answerId;

    @Column(name = "answer_text")
    public String answerText;

    @Column(name = "is_right")
    public Boolean isRight;

    @ManyToOne
    @JoinColumn(name="questionId")
    @JsonIgnore
    public Question question;

    @OneToMany(mappedBy="answer",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    public Set<TryAnswers> tryAnswers = new HashSet<>();

    public Answer() {
    }

    public Answer(String answerText, Boolean isRight, Question question) {
        this.answerText = answerText;
        this.isRight = isRight;
        this.question = question;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    public Boolean getRight() {
        return isRight;
    }

    public void setRight(Boolean right) {
        isRight = right;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Set<TryAnswers> getTryAnswers() {
        return tryAnswers;
    }

    public void setTryAnswers(Set<TryAnswers> tryAnswers) {
        this.tryAnswers = tryAnswers;
    }
}
