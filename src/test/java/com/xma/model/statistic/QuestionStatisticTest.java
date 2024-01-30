package com.xma.model.statistic;

import com.xma.model.Answer;
import com.xma.model.Question;
import com.xma.model.generators.QuestionGenerator;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class QuestionStatisticTest {

    Map<Question, QuestionStatistic> statisticMap = new HashMap<>();

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
            List<Integer> counts = new ArrayList<>();
            q.getKey().getAnswers().forEach(answer -> counts.add(answer.getCount()));
            assertIterableEquals(counts, q.getValue().getAnswersCounts());
        }
    }

    @Test
    void getAnswersPercents() {
        for (var q : statisticMap.entrySet()) {
            int count = 0;
            for (Answer answer : q.getKey().getAnswers()) {
                count += answer.getCount();
            }
            List<Double> percents = new ArrayList<>();
            for (Answer answer : q.getKey().getAnswers()) {
                percents.add((double) answer.getCount() / count);
            }
            assertIterableEquals(percents, q.getValue().getAnswersPercents());
        }
    }
}