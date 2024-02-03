package com.xma.surveys.data;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
public class SurveyEntity {
    private UUID surveyId;
    private List<QuestionEntity> questions;
}
