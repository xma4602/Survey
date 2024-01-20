package com.xma.statistic;

import com.xma.model.Answer;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class QuestionStatistic {
    private final String topic;
    private final List<AnswerStatistic> answerStatistics = new ArrayList<>();

    public QuestionStatistic(String topic, Iterable<Answer> answers ) {
        this.topic = topic;
        int totalCount = getTotalCount(answers);
        answers.forEach(answer -> answerStatistics.add(new AnswerStatistic(answer, totalCount)));
    }

    public int getTotalAnswersCount() {
        return answerStatistics.stream()
                .map(AnswerStatistic::getCount)
                .reduce(0, Integer::sum);
    }

    public Iterable<Integer> getAnswersCounts() {
        return answerStatistics.stream()
                .map(AnswerStatistic::getCount)
                .collect(Collectors.toList());
    }

    public Iterable<Double> getAnswersPercents() {
        return answerStatistics.stream()
                .map(AnswerStatistic::getPercent)
                .collect(Collectors.toList());
    }

    private int getTotalCount(Iterable<Answer> answers) {
        int count = 0;
        for (var answer : answers) {
            count += answer.getCount();
        }
        return count;
    }
}
