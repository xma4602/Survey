package com.xma.surveys.data;

import com.xma.surveys.model.QuestionStatus;
import com.xma.surveys.model.QuestionType;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class QuestionEntity {
    private UUID questionId;
    private SurveyEntity survey;
    private String topic;
    private int index;
    private QuestionStatus status;
    private QuestionType type;
    private List<AnswerEntity> answers;
}
