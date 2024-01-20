package com.xma;

import com.xma.model.Answer;
import com.xma.model.Survey;
import com.xma.statistic.QuestionStatistic;

import java.util.*;

public class SurveyManager {

    private final Map<UUID, Survey> surveys = new HashMap<>();

    public void addSurvey(Survey survey) {
        surveys.put(survey.getSurveyId(), survey);
    }

    public void addAllSurveys(Survey... surveys) {
        for (var survey : surveys) addSurvey(survey);
    }

    public Iterable<Map.Entry<String, Iterable<String>>> getOpenedQuestions(UUID surveyId) {
        Survey survey = surveys.get(surveyId);
        List<Map.Entry<String, Iterable<String>>> list = new ArrayList<>();

        for (var question : survey.getQuestions()) {
            if (question.isVisible()) {
                List<String> texts = new ArrayList<>();
                for (var answer : question.getAnswers()) texts.add(answer.getText());
                list.add(Map.entry(question.getTopic(), texts));
            }
        }

        return list;
    }

    public Iterable<QuestionStatistic> getOpenedStatistics(UUID surveyId) {
        Survey survey = surveys.get(surveyId);
        List<QuestionStatistic> list = new ArrayList<>();

        for (var question : survey.getQuestions()) {
            if (question.isVisible()) {
                list.add(new QuestionStatistic(question.getTopic(), question.getAnswers()));
            }
        }

        return list;
    }

    public Iterable<QuestionStatistic> getClosedStatistics(UUID surveyId) {
        Survey survey = surveys.get(surveyId);
        List<QuestionStatistic> list = new ArrayList<>();

        for (var question : survey.getQuestions()) {
            if (!question.isVisible() && !question.isEditable()) {
                list.add(new QuestionStatistic(question.getTopic(), question.getAnswers()));
            }
        }

        return list;
    }

    public int answer(UUID surveyId, int questionIndex, int answerIndex) {
        return surveys.get(surveyId).answer(questionIndex, answerIndex);
    }
}
