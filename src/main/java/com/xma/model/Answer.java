package com.xma.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.UUID;

@Getter
public class Answer {
    private UUID questionId;
    private int index;
    private final String text;
    private int count = 0;

    public Answer(String text) {
        this.text = text;
    }

    public int incrementCount() {
        return ++count;
    }

    public void clearCount() {
        count = 0;
    }

    public Answer putInQuestion(int index, @NonNull UUID questionId) {
        if (index < 0) throw new IndexOutOfBoundsException("Index must be greater then 0, but was " + index);
        this.index = index;
        this.questionId = questionId;
        return this;
    }
}
