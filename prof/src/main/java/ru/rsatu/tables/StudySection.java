package ru.rsatu.tables;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * раздел обучения
 */
@Entity()
@Table(name = "study_section")
public class StudySection extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "study_section_gen")
    public Long studySectionId;

    @Column(name = "name")
    public String name;

    @Column(name = "description")
    public String description;

    @Column(name = "is_deprecated")
    public Boolean isDeprecated;

    @OneToMany(mappedBy="studySection",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public Set<Course> courses = new HashSet<>();

    public StudySection() {
    }
}
