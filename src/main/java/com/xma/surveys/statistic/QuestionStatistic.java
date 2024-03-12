package com.xma.surveys.statistic;

import com.xma.surveys.entities.Answer;
import com.xma.surveys.entities.Question;
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
    private final AnswerStatistic[] answerStatistics;

    public QuestionStatistic(Question question) {
        List<Integer> counts = new ArrayList<>();
        List<Double> percents = new ArrayList<>();

        answerStatistics = new AnswerStatistic[question.getAnswers().size()];
        int totalCount = 0;

        int i = 0;
        for (Answer answer : question.getAnswers()) {
            answerStatistics[i++] = new AnswerStatistic(answer);
            totalCount += answer.getCount();
        }
        for (AnswerStatistic answerStatistic : answerStatistics) {
            answerStatistic.setTotalCount(totalCount);
        }

        topic = question.getTopic();
        totalAnswersCount = totalCount;
    }
}