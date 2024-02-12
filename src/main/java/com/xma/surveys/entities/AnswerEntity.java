package com.xma.surveys.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "answers", schema = "dev")
public class AnswerEntity {
    @Id
    @Column(name = "question_id")
    private UUID questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    private QuestionEntity question;

    @Column(name = "text")
    private String text;

    @Column(name = "index")
    private int index;

    @Column(name = "count")
    private int count = 0;
}
