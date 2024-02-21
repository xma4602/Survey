package com.xma.surveys.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "answers",
        uniqueConstraints = @UniqueConstraint(columnNames = {"answer_id", "question_id", "index"}))
public class AnswerEntity {

    @Id
    @GeneratedValue
    @Column(name = "answer_id")
    private UUID answerId;

    @ManyToOne(fetch = FetchType.LAZY)
    private QuestionEntity question;

    @Column(name = "index")
    private int index;

    @Column(name = "text")
    private String text;

    @Column(name = "count")
    private int count = 0;

}
