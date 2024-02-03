package com.xma.surveys.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class AnswerEntity {
    private UUID questionId;
    private QuestionEntity question;
    private String text;
    private int index;
    private int count;
}
