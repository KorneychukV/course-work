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

    public StudyProgram(String name, String description, String studyGroupAlias, Integer minimalDuration,
                        Integer completeTime, Integer questionNums, Integer triesCount) {
        this.name = name;
        this.description = description;
        this.studyGroupAlias = studyGroupAlias;
        this.minimalDuration = minimalDuration;
        this.completeTime = completeTime;
        this.questionNums = questionNums;
        this.triesCount = triesCount;
    }

    public StudyProgram() {
    }

    public Long getStudyProgramId() {
        return studyProgramId;
    }

    public void setStudyProgramId(Long studyProgramId) {
        this.studyProgramId = studyProgramId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStudyGroupAlias() {
        return studyGroupAlias;
    }

    public void setStudyGroupAlias(String studyGroupAlias) {
        this.studyGroupAlias = studyGroupAlias;
    }

    public Integer getMinimalDuration() {
        return minimalDuration;
    }

    public void setMinimalDuration(Integer minimalDuration) {
        this.minimalDuration = minimalDuration;
    }

    public Integer getCompleteTime() {
        return completeTime;
    }

    public void setCompleteTime(Integer completeTime) {
        this.completeTime = completeTime;
    }

    public Integer getQuestionNums() {
        return questionNums;
    }

    public void setQuestionNums(Integer questionNums) {
        this.questionNums = questionNums;
    }

    public Integer getTriesCount() {
        return triesCount;
    }

    public void setTriesCount(Integer triesCount) {
        this.triesCount = triesCount;
    }

    public Boolean getDeprecated() {
        return isDeprecated;
    }

    public void setDeprecated(Boolean deprecated) {
        isDeprecated = deprecated;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
}
