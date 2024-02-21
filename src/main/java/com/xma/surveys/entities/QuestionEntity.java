package com.xma.surveys.entities;

import com.xma.surveys.model.QuestionStatus;
import com.xma.surveys.model.QuestionType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;


@Data
@Entity
@Table(name = "questions",
        uniqueConstraints = @UniqueConstraint(columnNames = {"question_id", "survey_id", "index"}))
@NoArgsConstructor
public class QuestionEntity {

    @Id
    @Column(name = "question_id")
    @GeneratedValue
    private UUID questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    private SurveyEntity survey;

    @OneToMany(mappedBy = "question_id", fetch = FetchType.LAZY)
    private List<AnswerEntity> answers;

    @Column(name = "index")
    private int index;

    @Column(name = "topic")
    private String topic;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private QuestionStatus status;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private QuestionType type;
}
