package com.xma.surveys.model;

import com.xma.surveys.model.generators.QuestionGenerator;
import com.xma.surveys.model.generators.SurveyGenerator;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

class SurveyTest {
    public static final int COUNT = 20;
    final QuestionGenerator questionGenerator = new QuestionGenerator();
    final SurveyGenerator surveyGenerator = new SurveyGenerator();
    final Random random = new Random(100);

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
            int index = random.nextInt(COUNT);
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
        int index = survey.addQuestion(random.nextInt(COUNT), question);

        assertEquals(question, survey.removeQuestion(index));
    }

    @Test
    void swapQuestions() {
        Survey survey = surveyGenerator.generate(true);
        int index1 = random.nextInt(survey.getQuestionsCount());
        int index2 = random.nextInt(survey.getQuestionsCount());
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
        int questionIndex = random.nextInt(survey.getQuestionsCount());
        int answerIndex = random.nextInt(survey.getQuestion(questionIndex).getAnswersCount());

        int count = survey.getQuestion(questionIndex).getAnswer(answerIndex).getCount();

        survey.answer(questionIndex, answerIndex);
        assertEquals(count + 1, survey.getQuestion(questionIndex).getAnswer(answerIndex).getCount());
    }
}