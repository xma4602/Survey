package com.xma.surveys.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;


@Entity
@Table(name = "answers")
@NoArgsConstructor
@EqualsAndHashCode
public class AnswerEntity {

    @EmbeddedId
    private AnswerEntityId id = new AnswerEntityId();

    @Column(name = "text")
    @Getter
    @Setter
    private String text;

    @Column(name = "count")
    @Getter
    @Setter
    private int count = 0;

    @Transient
    public UUID getQuestionId() {
        return id.getQuestionId();
    }

    @Transient
    public int getIndex() {
        return id.getIndex();
    }

    @Transient
    public void setQuestionId(UUID questionId) {
        id.setQuestionId(questionId);
    }

    @Transient
    public void setIndex(int index) {
        id.setIndex(index);
    }

    @Embeddable
    @Data
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class AnswerEntityId implements Serializable {
        @JoinColumn(name = "question_id")
        private UUID questionId;

        @JoinColumn(name = "index")
        private int index;
    }
}
