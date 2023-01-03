package com.ideationstorm.com.ideationstorm.language;

import jakarta.persistence.*;


@Entity
@Table(name="languages", schema = "IDEATION_STORM")
public class LanguageEntity {
    @Id
    @GeneratedValue
    private long id;

    private String name;
}
