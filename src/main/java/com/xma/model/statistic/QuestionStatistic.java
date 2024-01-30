package com.xma.model.statistic;

import com.xma.model.Answer;
import com.xma.model.Question;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@EqualsAndHashCode
@ToString
public class QuestionStatistic {
    private final String topic;
    private final int totalAnswersCount;
    private final Iterable<Integer> answersCounts;
    private final Iterable<Double> answersPercents;

    public QuestionStatistic(Question question) {
        List<Integer> counts = new ArrayList<>();
        List<Double> percents = new ArrayList<>();
        int answersCount = 0;

        for (Answer answer : question.getAnswers()) {
            answersCount += answer.getCount();
        }
        for (Answer answer : question.getAnswers()) {
            counts.add(answer.getCount());
            percents.add(((double) answer.getCount()) / answersCount);
        }

        topic = question.getTopic();
        totalAnswersCount = answersCount;
        answersCounts = counts;
        answersPercents = percents;
    }
}
