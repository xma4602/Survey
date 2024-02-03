package com.xma.surveys.model;

import com.xma.surveys.model.generators.AnswerGenerator;
import com.xma.surveys.model.generators.QuestionGenerator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {
    final int COUNT = 20;
    final String topic = "test_topic";
    final QuestionGenerator questionGenerator = new QuestionGenerator();
    final AnswerGenerator answerGenerator = new AnswerGenerator();
    final Random random = new Random(1000);

    @Test
    void isMultivariate() {
        Question question;

        question = new Question(topic, QuestionType.SINGLE);
        assertFalse(question.isMultivariate());

        question = new Question(topic, QuestionType.MULTI);
        assertTrue(question.isMultivariate());

    }

    @Test
    void isEditable() {
        Question question1;
        Question question2;

        question1 = new Question(topic, QuestionType.SINGLE);
        question2 = new Question(topic, QuestionType.MULTI);

        assertTrue(question1.isEditable());
        assertTrue(question2.isEditable());

        question1.open();
        question2.open();
        assertFalse(question1.isEditable());
        assertFalse(question2.isEditable());

        question1.close();
        question2.close();
        assertFalse(question1.isEditable());
        assertFalse(question2.isEditable());
    }

    @Test
    void isAnswered() {
        Question question1;
        Question question2;

        question1 = new Question(topic, QuestionType.SINGLE);
        question2 = new Question(topic, QuestionType.MULTI);

        assertFalse(question1.isAnswered());
        assertFalse(question2.isAnswered());

        question1.open();
        question2.open();
        assertTrue(question1.isAnswered());
        assertTrue(question2.isAnswered());

        question1.close();
        question2.close();
        assertFalse(question1.isAnswered());
        assertFalse(question2.isAnswered());
    }

    @Test
    void isVisible() {
        Question question1;
        Question question2;

        question1 = new Question(topic, QuestionType.SINGLE);
        question2 = new Question(topic, QuestionType.MULTI);

        assertFalse(question1.isVisible());
        assertFalse(question2.isVisible());

        question1.open();
        question2.open();
        assertTrue(question1.isVisible());
        assertTrue(question2.isVisible());

        question1.close();
        question2.close();
        assertFalse(question1.isVisible());
        assertFalse(question2.isVisible());
    }

    @Test
    void getAnswers() {
        var answers = answerGenerator.generatelist(COUNT);
        Question question = questionGenerator.generate();
        for (int i = 0; i < COUNT; i++) {
            question.addAnswer(i, answers.get(i));
        }
        assertIterableEquals(answers, question.getAnswers());
    }

    @Test
    void getAnswer() {
        var answers = answerGenerator.generatelist(COUNT);
        Question question = questionGenerator.generate();
        for (int i = 0; i < COUNT; i++) {
            question.addAnswer(i, answers.get(i));
        }
        for (int i = 0; i < COUNT; i++) {
            Assertions.assertEquals(answers.get(i), question.getAnswer(i));
        }
    }

    @Test
    void addAnswer() {
        Question question = questionGenerator.generate();
        for (int i = 0; i < COUNT; i++) {
            Answer answer = answerGenerator.generate();
            int index = random.nextInt(COUNT);
            index = question.addAnswer(index, answer);
            for (var answer1 : question.getAnswers()) {
                if (answer1 == answer) {
                    assertEquals(index, answer1.getIndex());
                    break;
                }
            }
        }
    }

    @Test
    void removeAnswer() {
        Question question = questionGenerator.generate(true);
        Answer answer = answerGenerator.generate();
        int index = question.addAnswer(random.nextInt(COUNT), answer);

        assertEquals(answer, question.removeAnswer(index));
    }

    @Test
    void clearAnswersCounts() {
        Question question = questionGenerator.generate(true);
        question.clearAnswersCounts();

        for (var answer : question.getAnswers()) {
            assertEquals(0, answer.getCount());
        }
    }

    @Test
    void swapAnswers() {
        Question question = questionGenerator.generate(true);
        int index1 = random.nextInt(question.getAnswersCount());
        int index2 = random.nextInt(question.getAnswersCount());
        Answer answer1 = question.getAnswer(index1);
        Answer answer2 = question.getAnswer(index2);

        question.swapAnswers(index1, index2);

        assertEquals(answer1, question.getAnswer(index2));
        assertEquals(answer2, question.getAnswer(index1));

    }

    @Test
    void answer() {
        Question question = questionGenerator.generate(true);
        question.open();

        int index = random.nextInt(question.getAnswersCount());
        int count = question.getAnswer(index).getCount();

        question.answer(index);
        assertEquals(count + 1, question.getAnswer(index).getCount());
    }

    @Test
    void openAndClose() {
        Question question = questionGenerator.generate();

        assertTrue(question.isEditable());
        assertFalse(question.isAnswered());
        assertFalse(question.isVisible());

        question.open();

        assertFalse(question.isEditable());
        assertTrue(question.isAnswered());
        assertTrue(question.isVisible());

        question.close();

        assertFalse(question.isEditable());
        assertFalse(question.isAnswered());
        assertFalse(question.isVisible());
    }

}