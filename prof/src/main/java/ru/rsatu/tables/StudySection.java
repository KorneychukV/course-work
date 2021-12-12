package ru.rsatu.tables;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    public Set<Course> courses;

    public StudySection() {
    }

    public Long getStudySectionId() {
        return studySectionId;
    }

    public void setStudySectionId(Long studySectionId) {
        this.studySectionId = studySectionId;
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
