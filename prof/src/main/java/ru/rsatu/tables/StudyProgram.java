package ru.rsatu.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity()
@Table(name = "study_program")
public class StudyProgram extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "study_program_gen")
    private Long studyProgramId;

    @Column(name = "name")
    public String name;

    @Column(name = "description")
    public String description;

    @Column(name = "study_group_alias")
    public String studyGroupAlias;

    @Column(name = "minimal_duration")
    public Integer minimalDuration;

    @Column(name = "complete_time")
    public Integer completeTime;

    @Column(name = "question_nums")
    public Integer questionNums;

    @Column(name = "tries_count")
    public Integer triesCount;

    @Column(name = "is_deprecated")
    public Boolean isDeprecated;

    @ManyToOne
    @JoinColumn(name="courseId")
    @JsonIgnore
    public Course course;

    @OneToMany(mappedBy="studyProgram",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<StudyProgramLiterature> studyProgramLiteratures = new HashSet<>();

    @OneToMany(mappedBy="studyProgram",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<StudyGroups> studyGroups = new HashSet<>();

    @OneToMany(mappedBy="studyProgram",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy="studyProgram",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<TestTry> testTries = new HashSet<>();
}
