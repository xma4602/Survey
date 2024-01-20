package com.xma.model;

import lombok.Getter;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static com.xma.model.QuestionStatus.ANSWERS_ONLY;
import static com.xma.model.QuestionStatus.ARCHIVED;

public class Question {
    @Getter
    private UUID questionId;
    @Getter
    private UUID surveyId;
    @Getter
    private String topic;
    @Getter
    private int index;
    private QuestionStatus status = QuestionStatus.EDIT_ONLY;
    private final QuestionType type;
    private final List<Answer> answers = new ArrayList<>();

    public Question(UUID questionId, String topic, QuestionType type) {
        this.questionId = questionId;
        this.topic = topic;
        this.type = type;
    }

    public boolean isMultivariate() {
        return type.isMultivariate();
    }

    public boolean isEditable() {
        return status.isEditable();
    }

    public boolean isAnswered() {
        return status.isAnswered();
    }

    public boolean isVisible() {
        return status.isVisible();
    }

    public Iterable<Answer> getAnswers() {
        return answers;
    }

    public Answer getAnswer(int index) {
        return answers.get(index);
    }

    public Question putInSurvey(int index, @NonNull UUID surveyId) {
        checkForMutability();
        if (index < 0) throw new IndexOutOfBoundsException("Index must be greater then 0, but was " + index);
        this.index = index;
        this.surveyId = surveyId;
        return this;
    }

    public int addAnswer(int index, @NonNull Answer answer) {
        checkForMutability();
        if (index < 0) throw new IndexOutOfBoundsException("Index must be greater then 0, but was " + index);
        if (index > answers.size()) {
            index = answers.size();
            answers.add(answer);
        } else {
            answers.add(index, answer);
            for (int idx = index + 1; idx < answers.size(); idx++) {
                answers.get(idx).putInQuestion(idx, questionId);
            }
        }

        return index;
    }

    public Answer removeAnswer(int index) {
        checkForMutability();
        return answers.remove(index);
    }

    public void clearAnswersCounts() {
        answers.forEach(Answer::clearCount);
    }

    public void swapAnswers(int index1, int index2) {
        if (index1 != index2) {
            Answer answer1 = answers.get(index1);
            Answer answer2 = answers.get(index2);
            answer1.putInQuestion(index2, questionId);
            answer2.putInQuestion(index1, questionId);
            answers.sort(Comparator.comparing(Answer::getIndex));
        }
    }

    public int answer(int answerIndex) {
        if (!isAnswered()) {
            throw new IllegalStateException("The question is not opened and cannot be answered");
        }
        return answers.get(answerIndex).incrementCount();
    }

    public void open() {
        status = ANSWERS_ONLY;
    }

    public void close() {
        status = ARCHIVED;
    }

    private void checkForMutability() {
        if (!isEditable()) {
            throw new IllegalStateException("The question is closed and cannot be edited");
        }
    }
}
