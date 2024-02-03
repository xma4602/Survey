package com.xma.surveys.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class Answer {
    @Getter
    private String text;
    @Getter
    @Setter
    private Question question;
    @Getter
    private int count = 0;

    public Answer(String text) {
        this.text = text;
    }

    public Answer(String text, Question question) {
        this.text = text;
        this.question = question;
    }

    public int getIndex() {
        int index = 0;
        for (Answer answer : question.getAnswers()) {
            if (this.equals(answer)) return index;
            index++;
        }
        throw new IllegalStateException(
                "The answer (%s) is not contained in the question (%s)."
                        .formatted(text, question.getTopic())
        );
    }

    public int incrementCount() {
        return ++count;
    }

    public void clearCount() {
        count = 0;
    }
}
