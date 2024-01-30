package com.xma.model;

import com.xma.model.generators.QuestionGenerator;
import com.xma.model.generators.SurveyGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class SurveyTest {
    public static final int COUNT = 20;
    QuestionGenerator questionGenerator = new QuestionGenerator();
    SurveyGenerator surveyGenerator = new SurveyGenerator();

    @Test
    void getQuestions() {
        var questions = questionGenerator.generatelist(COUNT);
        Survey survey = surveyGenerator.generate();
        for (int i = 0; i < COUNT; i++) {
            survey.addQuestion(i, questions.get(i));
        }
        assertIterableEquals(questions, survey.getQuestions());
    }

    @Test
    void getQuestion() {
        var questions = questionGenerator.generatelist(COUNT);
        Survey survey = surveyGenerator.generate();
        for (int i = 0; i < COUNT; i++) {
            survey.addQuestion(i, questions.get(i));
        }
        for (int i = 0; i < COUNT; i++) {
            assertEquals(questions.get(i), survey.getQuestion(i));
        }
    }

    @Test
    void addQuestion() {
        Survey survey = surveyGenerator.generate();
        for (int i = 0; i < COUNT; i++) {
            var question = questionGenerator.generate();
            int index = (int) (Math.random() * COUNT);
            index = survey.addQuestion(index, question);
            for (var question1 : survey.getQuestions()) {
                if (question1 == question) {
                    assertEquals(index, question1.getIndex());
                    break;
                }
            }
        }
    }

    @Test
    void removeQuestion() {
        Survey survey = surveyGenerator.generate(true);
        Question question = questionGenerator.generate();
        int index = survey.addQuestion((int) (Math.random() * COUNT), question);

        assertEquals(question, survey.removeQuestion(index));
    }

    @Test
    void swapQuestions() {
        Survey survey = surveyGenerator.generate(true);
        int index1 = (int) (Math.random() * survey.getQuestionSize());
        int index2 = (int) (Math.random() * survey.getQuestionSize());
        Question question1 = survey.getQuestion(index1);
        Question question2 = survey.getQuestion(index2);

        survey.swapQuestions(index1, index2);

        assertEquals(question1, survey.getQuestion(index2));
        assertEquals(question2, survey.getQuestion(index1));
    }

    @Test
    void answer() {
        Survey survey = surveyGenerator.generate(true);
        for (Question question : survey.getQuestions()) {
            question.open();
        }
        int questionIndex = (int) (Math.random() * survey.getQuestionSize());
        int answerIndex = (int) (Math.random() * survey.getQuestionSize());

        int count = survey.getQuestion(questionIndex).getAnswer(answerIndex).getCount();

        survey.answer(questionIndex, answerIndex);
        assertEquals(count + 1, survey.getQuestion(questionIndex).getAnswer(answerIndex).getCount());
    }
}