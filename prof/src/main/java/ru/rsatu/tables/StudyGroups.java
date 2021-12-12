package ru.rsatu.tables;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity()
@Table(name = "study_groups")
public class StudyGroups extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "study_groups_gen")
    private Long studyGroupsId;

    @Column(name = "name")
    public String name;

    @Column(name = "program")
    public String program;

    @Column(name = "start_date")
    public Date startDate;

    @Column(name = "end_date")
    public Date endDate;

    @Column(name = "is_complete")
    public Boolean isComplete;

    @ManyToOne
    @JoinColumn(name="studyProgramId")
    public StudyProgram studyProgram;

    @OneToMany(mappedBy="studyGroups",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Contract> contracts = new HashSet<>();

}
