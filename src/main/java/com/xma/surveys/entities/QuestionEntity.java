package com.xma.surveys.entities;

import com.xma.surveys.model.QuestionStatus;
import com.xma.surveys.model.QuestionType;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;


@Entity
@Table(name = "questions")
@NoArgsConstructor
@EqualsAndHashCode
public class QuestionEntity {

    @EmbeddedId
    private QuestionEntityId id = new QuestionEntityId();

    @Column(name = "topic")
    @Getter
    @Setter
    private String topic;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private QuestionStatus status;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private QuestionType type;

    @OneToMany(mappedBy = "id.questionId", fetch = FetchType.LAZY)
    @Getter
    @Setter
    private List<AnswerEntity> answers;

    @Transient
    public UUID getQuestionId(){
        return id.getQuestionId();
    }

    @Transient
    public UUID getSurveyId(){
        return id.getSurveyId();
    }

    @Transient
    public int getIndex(){
        return id.getIndex();
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    public static class QuestionEntityId implements Serializable {

        @JoinColumn(name = "survey_id")
        private UUID surveyId;

        @JoinColumn(name = "question_id")
        @GeneratedValue
        private UUID questionId;

        @JoinColumn(name = "index")
        private int index;
    }
}
