package com.xma.surveys;

import com.xma.surveys.entities.Question;
import com.xma.surveys.entities.Survey;
import com.xma.surveys.generators.QuestionGenerator;
import com.xma.surveys.generators.SurveyGenerator;
import com.xma.surveys.statistic.QuestionStatistic;
import com.xma.surveys.services.SurveyService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SurveyServiceTest {
    final SurveyGenerator surveyGenerator = new SurveyGenerator();
    final QuestionGenerator questionGenerator = new QuestionGenerator();
    public static final int COUNT = 20;

    @Test
    void getOpenedQuestions() {
        List<Question> questions = questionGenerator.generateList(COUNT, true);
        Survey survey = surveyGenerator.generate();
        List<Question> openedQuestions = new ArrayList<>();

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            survey.getQuestions().add(i, question);
            if (i % 2 == 0) {
                question.open();
                openedQuestions.add(question);
            }
        }
        var result = SurveyService.getOpenedQuestions(survey);

        assertIterableEquals(openedQuestions, result);
    }

    @Test
    void getOpenedStatistics() {
        List<Question> questions = questionGenerator.generateList(COUNT, true);
        Survey survey = new Survey();
        survey.setTitle("test_survey");
        survey.setQuestions(questions);

        List<QuestionStatistic> openedQuestions = new ArrayList<>();
        for (int i = 0; i < questions.size(); i += 2) {
            Question question = questions.get(i);
            question.open();
            openedQuestions.add(new QuestionStatistic(question));
        }
        Iterable<QuestionStatistic> result = SurveyService.getOpenedStatistics(survey);

        assertIterableEquals(openedQuestions, result);
    }

    @Test
    void getClosedStatistics() {
        List<Question> questions = questionGenerator.generateList(COUNT, true);
        Survey survey = new Survey();
        survey.setTitle("test_survey");
        survey.setQuestions(questions);

        List<QuestionStatistic> closedQuestions = new ArrayList<>();
        for (int i = 0; i < questions.size(); i += 2) {
            Question question = questions.get(i);
            question.open();
            question.close();
            closedQuestions.add(new QuestionStatistic(question));
        }
        Iterable<QuestionStatistic> result = SurveyService.getClosedStatistics(survey);

        assertIterableEquals(closedQuestions, result);
    }

}