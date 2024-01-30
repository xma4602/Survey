package com.xma.model;

import lombok.*;

import java.util.UUID;

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Answer {
    private String text;
    private UUID questionId;
    private int index = -1;
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

    public void putInQuestion(int index, UUID questionId) {
        if (index < 0) throw new IndexOutOfBoundsException("Index must be greater then 0, but was " + index);
        this.index = index;
        this.questionId = questionId;
    }
}
