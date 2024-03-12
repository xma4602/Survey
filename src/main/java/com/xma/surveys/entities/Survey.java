package com.xma.surveys.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "surveys")
public class Survey {
    @Id
    @Column(name = "survey_id")
    @GeneratedValue
    private UUID surveyId;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "surveyId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Question> questions;
}
