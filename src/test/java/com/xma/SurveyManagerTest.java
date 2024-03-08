package com.xma;

import com.xma.model.Question;
import com.xma.model.Survey;
import com.xma.model.generators.QuestionGenerator;
import com.xma.model.generators.SurveyGenerator;
import com.xma.model.statistic.QuestionStatistic;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SurveyManagerTest {
    SurveyGenerator surveyGenerator = new SurveyGenerator();
    QuestionGenerator questionGenerator = new QuestionGenerator();
    public static final int COUNT = 20;

    @Test
    void getOpenedQuestions() {
        List<Question> questions = questionGenerator.generateList(COUNT, true);
        Survey survey = surveyGenerator.generate();
        List<Question> openedQuestions = new ArrayList<>();

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            survey.addQuestion(i, question);
            if (i % 2 == 0) {
                question.open();
                openedQuestions.add(question);
            }
        }
        var result = SurveyManager.getOpenedQuestions(survey);

        assertIterableEquals(openedQuestions, result);
    }

    @Test
    void getOpenedStatistics() {
        List<Question> questions = questionGenerator.generateList(COUNT, true);
        Survey survey = new Survey(UUID.randomUUID(), questions);

        List<QuestionStatistic> openedQuestions = new ArrayList<>();
        for (int i = 0; i < questions.size(); i += 2) {
            Question question = questions.get(i);
            question.open();
            openedQuestions.add(new QuestionStatistic(question));
        }
        Iterable<QuestionStatistic> result = SurveyManager.getOpenedStatistics(survey);

        assertIterableEquals(openedQuestions, result);
    }

    @Test
    void getClosedStatistics() {
        List<Question> questions = questionGenerator.generateList(COUNT, true);
        Survey survey = new Survey(UUID.randomUUID(), questions);

        List<QuestionStatistic> closedQuestions = new ArrayList<>();
        for (int i = 0; i < questions.size(); i += 2) {
            Question question = questions.get(i);
            question.open();
            question.close();
            closedQuestions.add(new QuestionStatistic(question));
        }
        Iterable<QuestionStatistic> result = SurveyManager.getClosedStatistics(survey);

        assertIterableEquals(closedQuestions, result);
    }

    @Test
    void answer() {
        List<Question> questions = questionGenerator.generateList(COUNT, true);
        Survey survey = new Survey(UUID.randomUUID(), questions);
        int questionIndex = (int) (Math.random() * questions.size());
        int answerIndex = (int) (Math.random() * questions.get(questionIndex).getAnswersSize());

        int count1 = questions.get(questionIndex).getAnswer(answerIndex).getCount();
        questions.get(questionIndex).open();
        survey.answer(questionIndex, answerIndex);
        int count2 = questions.get(questionIndex).getAnswer(answerIndex).getCount();

        assertEquals(count1 + 1, count2);
    }
}