package com.xma.statistic;

import com.xma.model.Answer;
import lombok.Getter;

@Getter
public class AnswerStatistic {
    private final String text;
    private final int index;
    private final int count;
    private final double percent;

    public AnswerStatistic(Answer answer, int totalCount) {
        text = answer.getText();
        index = answer.getIndex();
        count = answer.getCount();
        percent = ((double) count) / totalCount;
    }
}
