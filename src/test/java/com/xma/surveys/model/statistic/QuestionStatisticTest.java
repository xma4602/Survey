package com.xma.surveys.model.statistic;

import com.xma.surveys.model.Answer;
import com.xma.surveys.model.Question;
import com.xma.surveys.model.generators.QuestionGenerator;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QuestionStatisticTest {

    final Map<Question, QuestionStatistic> statisticMap = new HashMap<>();

    {
        List<Question> questions = new QuestionGenerator().generateList(50, true);
        for (Question question : questions) {
            statisticMap.put(question, new QuestionStatistic(question));
        }
    }

    @Test
    void getTopic() {
        for (var q : statisticMap.entrySet()) {
            assertEquals(q.getKey().getTopic(), q.getValue().getTopic());
        }
    }

    @Test
    void getTotalAnswersCount() {
        for (var q : statisticMap.entrySet()) {
            int count = 0;
            for (Answer answer : q.getKey().getAnswers()) {
                count += answer.getCount();
            }
            assertEquals(count, q.getValue().getTotalAnswersCount());
        }
    }

    @Test
    void getAnswersCounts() {
        for (var q : statisticMap.entrySet()) {
            int i = 0;
            for (Answer answer : q.getKey().getAnswers()) {
                AnswerStatistic answerStatistic = q.getValue().getAnswerStatistics()[i++];
                assertEquals(answer.getCount(), answerStatistic.getCount());
            }
        }
    }

    @Test
    void getAnswersPercents() {
        for (var q : statisticMap.entrySet()) {
            int count = 0;
            for (Answer answer : q.getKey().getAnswers()) {
                count += answer.getCount();
            }
            int i = 0;
            for (Answer answer : q.getKey().getAnswers()) {
                AnswerStatistic answerStatistic = q.getValue().getAnswerStatistics()[i++];
                assertEquals(answer.getCount() / (double) count, answerStatistic.getPercent());
            }
        }
    }
}