package com.xma.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AnswerTest {

    Answer answer;

    @BeforeEach
    void setUp() {
        answer = new Answer("test_text");
    }

    @Test
    void incrementCount() {
        assertEquals(0, answer.getCount());

        int len = (int) (Math.random() * 100);
        for (int i = 1; i < len; i++) {
            answer.incrementCount();
            assertEquals(i, answer.getCount());
        }
    }

    @Test
    void clearCount() {
        int len = (int) (Math.random() * 100);
        for (int i = 1; i < len; i++) {
            answer.incrementCount();
        }
        answer.clearCount();
        assertEquals(0, answer.getCount());
    }

    @Test
    void putInQuestion() {
        assertEquals(-1, answer.getIndex());
        assertNull(answer.getQuestionId());

        int index = (int) (Math.random() * 100);
        UUID id = UUID.randomUUID();
        answer.putInQuestion(index, id);

        assertEquals(index, answer.getIndex());
        assertEquals(id, answer.getQuestionId());
    }
}