package com.xma;

import com.xma.model.Question;
import com.xma.model.Survey;
import com.xma.model.statistic.QuestionStatistic;

import java.util.stream.Collectors;
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

    public static int answer(Survey survey, int questionIndex, int answerIndex) {
        return survey.answer(questionIndex, answerIndex);
    }
}
