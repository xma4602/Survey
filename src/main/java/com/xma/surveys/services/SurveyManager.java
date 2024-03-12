package com.xma.surveys.services;

import com.xma.surveys.entities.Question;
import com.xma.surveys.entities.Survey;
import com.xma.surveys.statistic.QuestionStatistic;

import java.util.stream.StreamSupport;

public class SurveyManager {

    public static Iterable<Question> getOpenedQuestions(Survey survey) {
        return StreamSupport.stream(survey.getQuestions().spliterator(), false)
                .filter(Question::isVisible)
                .toList();
    }

    public static Iterable<QuestionStatistic> getOpenedStatistics(Survey survey) {
        return StreamSupport.stream(survey.getQuestions().spliterator(), false)
                .filter(Question::isVisible)
                .map(QuestionStatistic::new)
                .toList();
    }

    public static Iterable<QuestionStatistic> getClosedStatistics(Survey survey) {
        return StreamSupport.stream(survey.getQuestions().spliterator(), false)
                .filter(question -> !question.isVisible() && !question.isEditable())
                .map(QuestionStatistic::new)
                .toList();
    }
}
