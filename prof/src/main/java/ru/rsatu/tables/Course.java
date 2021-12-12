package ru.rsatu.tables;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.*;

/**
 * программа обучение
 */
@Entity()
@Table(name = "COURSE")
public class Course extends PanacheEntity {

    @Column(name = "NAME")
    public String name;

    @Column(name = "DESCRIPTION")
    public String description;
}
