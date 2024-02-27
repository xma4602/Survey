package com.xma.surveys.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
@EqualsAndHashCode
public class Survey {

    private final String title;

    private final List<Question> questions = new ArrayList<>();

    public Survey(String title) {
        this.title = title;
    }

    public Survey(String title, Iterable<Question> questions) {
        this.title = title;
        questions.forEach(this::addQuestion);
    }

    public int getQuestionsCount() {
        return questions.size();
    }

    public Iterable<Question> getQuestions() {
        return questions;
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }

    public int addQuestion(int index, Question question) {
        if (index < 0) throw new IndexOutOfBoundsException("Index must be greater then 0, but was " + index);
        if (index > questions.size()) {
            index = questions.size();
        }
        questions.add(index, question);
        question.setSurvey(this);
        return index;
    }

    public int addQuestion(Question question) {
        questions.add(question);
        question.setSurvey(this);
        return questions.size();
    }

    public Question removeQuestion(int index) {
        return questions.remove(index);
    }

    public void swapQuestions(int index1, int index2) {
        if (index1 != index2) {
            Question question1 = questions.remove(index1);
            Question question2 = questions.remove(index2);
            questions.add(index2, question1);
            questions.add(index1, question2);
        }
    }

    public int answer(int questionIndex, int answerIndex) {
        return questions.get(questionIndex).answer(answerIndex);
    }
}
