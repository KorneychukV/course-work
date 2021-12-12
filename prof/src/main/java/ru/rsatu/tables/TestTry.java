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
}
