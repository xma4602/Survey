package com.xma.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

public class Survey {
    @Getter
    private UUID surveyId;
    private final List<Question> questions = new ArrayList<>();

    public Survey(UUID surveyId) {
        this.surveyId = surveyId;
    }

    public Iterable<Question> getQuestions() {
        return questions;
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }

    public int addQuestion(int index, @NonNull Question question) {
        if (index > questions.size()) {
            index = questions.size();
            questions.add(question);
        } else {
            questions.add(index, question);
            for (int idx = index + 1; idx < questions.size(); idx++) {
                questions.get(idx).putInSurvey(idx, surveyId);
            }
        }

        return index;
    }

    public Question removeQuestion(int index) {
        return questions.remove(index);
    }

    public void swapQuestions(int index1, int index2) {
        if (index1 != index2) {
            Question question1 = questions.get(index1);
            Question question2 = questions.get(index2);
            question1.putInSurvey(index2, surveyId);
            question2.putInSurvey(index1, surveyId);
            questions.sort(Comparator.comparing(Question::getIndex));
        }
    }

    public int answer(int questionIndex, int answerIndex) {
       return questions.get(questionIndex).answer(answerIndex);
    }
}
