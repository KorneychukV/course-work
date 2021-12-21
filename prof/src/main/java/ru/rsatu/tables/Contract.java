package ru.rsatu.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity()
@Table(name = "contract")
public class Contract extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "contract_gen")
    private Long contractId;

    @Column(name = "user_id")
    public String userID;

    @Column(name = "enrollment_date")
    public Date enrollmentDate;

    @Column(name = "is_complete")
    public Boolean isComplete;

    @ManyToOne
    @JoinColumn(name="studyProgramId")
    @JsonIgnore
    public StudyProgram studyProgram;

    @OneToMany(mappedBy="contract",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<TestTry> testTries = new HashSet<>();

    public Contract() {
    }

    public Contract(String userID, StudyProgram studyProgram, boolean isComplete) {
        this.userID = userID;
        this.studyProgram = studyProgram;
        this.isComplete = isComplete;
    }
}
