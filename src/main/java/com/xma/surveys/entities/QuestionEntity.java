package com.xma.surveys.entities;

import com.xma.surveys.model.QuestionStatus;
import com.xma.surveys.model.QuestionType;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "questions", schema = "dev")
public class QuestionEntity {
    @Id
    @Column(name = "question_id")
    @GeneratedValue
    private UUID questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    private SurveyEntity survey;

    @Column(name = "topic")
    private String topic;

    @Column(name = "index")
    private int index;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private QuestionStatus status;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @OneToMany(mappedBy = "question_id")
    private List<AnswerEntity> answers;
}
