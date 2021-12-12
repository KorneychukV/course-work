package ru.rsatu.tables;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;
import java.util.Set;

/**
 * раздел обучения
 */
@Entity()
@Table(name = "STUDYSECTION")
public class StudySection extends PanacheEntity {
    /*
    наименование
     */
    @Column(name = "NAME")
    public String name;


    @Column(name = "DESCRIPTION")
    public String description;


//    @OneToMany(mappedBy="agregateType",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    public Set<DesignDocumentation> designDocumentations;


    public StudySection() {
    }
}
