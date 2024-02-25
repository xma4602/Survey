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
    @GeneratedValue
    @Column(name = "question_id")
    private UUID questionId;

    @Column(name = "survey_id")
    private UUID surveyId;

    @OneToMany(mappedBy = "questionId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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
