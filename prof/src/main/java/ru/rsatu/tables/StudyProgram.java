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

    //Минимальное время подготовки, час.
    @Column(name = "minimal_duration")
    public Integer minimalDuration;

    //Максимальное время выполнения теста, мин.
    @Column(name = "complete_time")
    public Integer completeTime;

    //Количество вопросов в тесте
    @Column(name = "question_nums")
    public Integer questionNums;

    //Количество попыток итогового теста
    @Column(name = "tries_count")
    public Integer triesCount;

    //Стоимость
    @Column(name = "price")
    public Integer price;

    //
    @Column(name = "is_deprecated")
    public Boolean isDeprecated;

    @ManyToOne
    @JoinColumn(name="courseId")
    @JsonIgnore
    public Course course;

    @OneToMany(mappedBy="studyProgram",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    public Set<StudyProgramLiterature> studyProgramLiteratures = new HashSet<>();

    @OneToMany(mappedBy="studyProgram",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    public Set<Contract> contracts = new HashSet<>();

    @OneToMany(mappedBy="studyProgram",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    public Set<Question> questions = new HashSet<>();

    @OneToMany(mappedBy="studyProgram",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    public Set<TestTry> testTries = new HashSet<>();

    public StudyProgram(String name, String description, Integer minimalDuration, Integer completeTime, 
                        Integer questionNums, Integer triesCount, Integer price) {
        this.name = name;
        this.description = description;
        this.minimalDuration = minimalDuration;
        this.completeTime = completeTime;
        this.questionNums = questionNums;
        this.triesCount = triesCount;
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
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

    @Override
    public String toString() {
        return "StudyProgram{" +
                "studyProgramId=" + studyProgramId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", minimalDuration=" + minimalDuration +
                ", completeTime=" + completeTime +
                ", questionNums=" + questionNums +
                ", triesCount=" + triesCount +
                ", price=" + price +
                ", isDeprecated=" + isDeprecated +
                '}';
    }
}
