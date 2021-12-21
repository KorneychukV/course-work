package ru.rsatu.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity()
@Table(name = "test_try")
public class TestTry extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "test_try_gen")
    private Long testTryId;

    @Column(name = "start_date")
    public Date startDate;

    @Column(name = "end_date")
    public Date endDate;

    @Column(name = "is_test")
    public Boolean isTest;

    @Column(name = "is_final")
    public Boolean isFinal;

    @Column(name = "is_complete")
    public Boolean isComplete;

    @Column(name = "is_successful")
    public Boolean isSuccessful;

    @ManyToOne
    @JoinColumn(name="studyProgramId")
    @JsonIgnore
    public StudyProgram studyProgram;

    @ManyToOne
    @JoinColumn(name="contractId")
    @JsonIgnore
    public Contract contract;

    @OneToMany(mappedBy="testTry",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<TryAnswers> tryAnswers = new HashSet<>();

    public TestTry() {
    }

    public Long getTestTryId() {
        return testTryId;
    }

    public void setTestTryId(Long testTryId) {
        this.testTryId = testTryId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getTest() {
        return isTest;
    }

    public void setTest(Boolean test) {
        isTest = test;
    }

    public Boolean getFinal() {
        return isFinal;
    }

    public void setFinal(Boolean aFinal) {
        isFinal = aFinal;
    }

    public Boolean getComplete() {
        return isComplete;
    }

    public void setComplete(Boolean complete) {
        isComplete = complete;
    }

    public Boolean getSuccessful() {
        return isSuccessful;
    }

    public void setSuccessful(Boolean successful) {
        isSuccessful = successful;
    }

    public StudyProgram getStudyProgram() {
        return studyProgram;
    }

    public void setStudyProgram(StudyProgram studyProgram) {
        this.studyProgram = studyProgram;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Set<TryAnswers> getTryAnswers() {
        return tryAnswers;
    }

    public void setTryAnswers(Set<TryAnswers> tryAnswers) {
        this.tryAnswers = tryAnswers;
    }
}
