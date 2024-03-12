package com.xma.surveys.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

import static com.xma.surveys.entities.QuestionStatus.ANSWERS_ONLY;
import static com.xma.surveys.entities.QuestionStatus.ARCHIVED;


@Data
@Entity
@Table(name = "questions",
        uniqueConstraints = @UniqueConstraint(columnNames = {"question_id", "survey_id", "index"}))
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue
    @Column(name = "question_id")
    private UUID questionId;

    @Column(name = "survey_id")
    private UUID surveyId;

    @OneToMany(mappedBy = "questionId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Answer> answers;

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

    public boolean isMultivariate() {
        return type.isMultivariate();
    }

    public boolean isEditable() {
        return status.isEditable();
    }

    public boolean isAnswered() {
        return status.isAnswered();
    }

    public boolean isVisible() {
        return status.isVisible();
    }

    public void open() {
        if (status == ARCHIVED) {
            throw new IllegalStateException("The question is not editable and cannot be opened");
        }
        status = ANSWERS_ONLY;
    }

    public void close() {
        status = ARCHIVED;
    }
}
