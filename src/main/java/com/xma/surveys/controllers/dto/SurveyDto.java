package com.xma.surveys.controllers.dto;


import com.xma.surveys.entities.Survey;
import lombok.Data;

import java.util.UUID;

@Data
public class SurveyDto {
    UUID surveyId;
    String title;
    int count;

    public SurveyDto(Survey survey) {
        surveyId = survey.getSurveyId();
        title = survey.getTitle();
        count = survey.getAnswersCountSum();
    }

    public SurveyDto() {
        surveyId = new UUID(0, 0);
        title = "Введите заголовок опроса";
        count = 0;
    }

    public Survey toSurvey() {
        Survey survey = new Survey();
        survey.setSurveyId(surveyId);
        survey.setTitle(title);
        return survey;
    }
}