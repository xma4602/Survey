package com.xma.surveys.model;

import com.xma.surveys.model.generators.QuestionGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class AnswerTest {

    Answer answer;
    final QuestionGenerator questionGenerator = new QuestionGenerator();
    final Random random = new Random();

    @BeforeEach
    void setUp() {
        answer = new Answer("test_text");
    }

    @Test
    void incrementCount() {
        assertEquals(0, answer.getCount());

        int len = random.nextInt(100);
        for (int i = 1; i < len; i++) {
            answer.incrementCount();
            assertEquals(i, answer.getCount());
        }
    }

    @Test
    void clearCount() {
        int len = random.nextInt(100);
        for (int i = 1; i < len; i++) {
            answer.incrementCount();
        }
        answer.clearCount();
        assertEquals(0, answer.getCount());
    }

}