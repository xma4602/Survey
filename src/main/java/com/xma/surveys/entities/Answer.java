package com.xma.surveys.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "answers",
        uniqueConstraints = @UniqueConstraint(columnNames = {"answer_id", "question_id", "index"}))
public class Answer {

    @Id
    @GeneratedValue
    @Column(name = "answer_id")
    private UUID answerId;

    @Column(name = "question_id")
    private UUID questionId;

    @Column(name = "index")
    private int index;

    @Column(name = "text")
    private String text;

    @Column(name = "count")
    private int count = 0;

    public Answer(UUID questionId, int index, String text, int count) {
        this.questionId = questionId;
        this.index = index;
        this.text = text;
        this.count = count;
    }

    public int incrementCount() {
        return ++count;
    }

    public void clearCount() {
        count = 0;
    }
}
