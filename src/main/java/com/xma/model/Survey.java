package com.xma.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Survey {
    @Getter
    private UUID surveyId;
    private final List<Question> questions = new ArrayList<>();

    public Survey(UUID surveyId) {
        this.surveyId = surveyId;
    }

    public Survey(UUID surveyId, Iterable<Question> questions) {
        this(surveyId);
        questions.forEach(answer -> addQuestion(this.questions.size(), answer));
    }

    public int getQuestionSize() {
        return questions.size();
    }

    public Iterable<Question> getQuestions() {
        return questions;
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }

    public int addQuestion(int index, Question question) {
        if (index > questions.size()) {
            index = questions.size();
        } else {
            for (int idx = index; idx < questions.size(); idx++) {
                questions.get(idx).putInSurvey(idx, surveyId);
            }
        }
        questions.add(index, question);
        question.putInSurvey(index, surveyId);
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
