package com.xma.surveys.model.generators;

import com.xma.surveys.model.Answer;

public class AnswerGenerator extends Generator<Answer> {

    @Override
    public Answer generate(boolean fill) {
        Answer answer = new Answer("text" + count++);
        if (fill) {
            int len = generateLength();
            for (int i = 0; i < len; i++) {
                answer.incrementCount();
            }
        }
        return answer;
    }
}
