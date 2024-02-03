package com.xma.surveys.model.statistic;

import com.xma.surveys.model.Answer;
import lombok.Getter;
import lombok.Setter;


public class AnswerStatistic {
    @Getter
    private int count;
    @Getter
    private String text;
    @Setter
    private int totalCount;

    public AnswerStatistic(Answer answer) {
        count = answer.getCount();
        text = answer.getText();
    }

    public double getPercent(){
        return (double) count / totalCount;
    }
}
