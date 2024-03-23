package com.xma.surveys.entities;

import lombok.Value;

import java.util.*;

@Value
public class QuestionnaireStatistic {
    Map<UUID, AnswerStatistic> answerStats = new HashMap<>();

    public QuestionnaireStatistic(Question question) {
        int totalCount = 0;

        for (Answer answer : question.getAnswers()) {
            totalCount += answer.getCount();
        }
        for (Answer answer : question.getAnswers()) {
            answerStats.put(
                    answer.getAnswerId(),
                    new AnswerStatistic(
                            answer.getCount(),
                            getPercent(answer.getCount(), totalCount)
                    )
            );
        }
    }

    private int getPercent(int count, int totalCount) {
        return (int) Math.round(count * 100.0 / totalCount);
    }


    @Value
    public static class AnswerStatistic {
        int count;
        int percent;
    }

}
