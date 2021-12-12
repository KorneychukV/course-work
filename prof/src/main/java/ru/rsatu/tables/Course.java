package ru.rsatu.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * программа обучение
 */
@Entity()
@Table(name = "course")
public class Course extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "course_gen")
    public Long courseId;

    @Column(name = "name")
    public String name;

    @Column(name = "description")
    public String description;

    @Column(name = "is_deprecated")
    public Boolean isDeprecated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="studySectionId")
    @JsonIgnore
    private StudySection studySection;

    @OneToMany(mappedBy="course", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<StudyProgram> studyPrograms = new HashSet<>();

    public Course() {
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
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

    public Boolean getDeprecated() {
        return isDeprecated;
    }

    public void setDeprecated(Boolean deprecated) {
        isDeprecated = deprecated;
    }
}
