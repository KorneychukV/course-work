package ru.rsatu.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity()
@Table(name = "try_answers")
public class TryAnswers extends PanacheEntityBase {

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

    public TryAnswers() {
    }

    public Long getTryAnswersId() {
        return tryAnswersId;
    }

    public void setTryAnswersId(Long tryAnswersId) {
        this.tryAnswersId = tryAnswersId;
    }

    public Integer getNpp() {
        return npp;
    }

    public void setNpp(Integer npp) {
        this.npp = npp;
    }

    public TestTry getTestTry() {
        return testTry;
    }

    public void setTestTry(TestTry testTry) {
        this.testTry = testTry;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }
}
