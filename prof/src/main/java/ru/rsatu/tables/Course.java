package ru.rsatu.tables;

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

    @ManyToOne
    @JoinColumn(name="studySectionId")
    private StudySection studySection;

    @OneToMany(mappedBy="course",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<StudyProgram> studyPrograms = new HashSet<>();

}
