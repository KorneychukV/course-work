package ru.rsatu.tables;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.Date;

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
    public String isComplete;

    @ManyToOne
    @JoinColumn(name="studyProgramId")
    public StudyProgram studyProgram;

}
