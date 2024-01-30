package com.xma.model.generators;

import com.xma.model.Answer;

import java.util.stream.IntStream;

public class AnswerGenerator extends Generator<Answer> {

    @Override
    public Answer generate(boolean fill) {
        Answer answer = new Answer("text" + count++);
        if (fill) {
            int len = (int) (Math.random() * LENGTH);
            for (int i = 0; i < len; i++) {
                answer.incrementCount();
            }
        }
        return answer;
    }
}
