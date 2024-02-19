package com.xma.surveys.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@Entity
@Table(name = "answers", schema = "dev")
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
    public UUID getQuestionId(){
        return id.getQuestionId();
    }

    @Transient
    public int getIndex(){
        return id.getIndex();
    }

    @Embeddable
    @Data
    static class AnswerEntityId implements Serializable {
        @JoinColumn(name = "question_id")
        private UUID questionId;

        @JoinColumn(name = "index")
        private int index;
    }
}
