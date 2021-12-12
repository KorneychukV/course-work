package ru.rsatu.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity()
@Table(name = "contract")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "contract_gen")
    private Long contractId;

    @Column(name = "user_id")
    public Long userID;

    @Column(name = "enrollment_date")
    public Date enrollmentDate;

    @Column(name = "is_complete")
    public Boolean isComplete;

    @ManyToOne
    @JoinColumn(name="studyGroupsId")
    @JsonIgnore
    public StudyGroups studyGroups;

    @OneToMany(mappedBy="contract",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<TestTry> testTries = new HashSet<>();
}
