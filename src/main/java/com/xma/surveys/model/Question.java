package com.xma.surveys.model;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static com.xma.surveys.model.QuestionStatus.ANSWERS_ONLY;
import static com.xma.surveys.model.QuestionStatus.ARCHIVED;

@ToString
@EqualsAndHashCode
public class Question {
    @Getter
    private String topic;
    @Getter
    @Setter
    private Survey survey;
    private QuestionStatus status;
    private final QuestionType type;
    private final List<Answer> answers = new ArrayList<>();

    public Question(String topic, QuestionType type) {
        this.topic = topic;
        this.type = type;
        this.status = QuestionStatus.EDIT_ONLY;
    }

    public Question(String topic, QuestionType type, Survey survey) {
        this(topic, type);
        this.survey = survey;
    }

    public Question(String topic, QuestionType type, Iterable<Answer> answers) {
        this(topic, type);
        answers.forEach(this::addAnswer);
    }

    public Question(String topic, QuestionType type, Survey survey, Iterable<Answer> answers) {
        this(topic, type, survey);
        answers.forEach(this::addAnswer);
    }

    public int getIndex() {
        int index = 0;
        for (Question question : survey.getQuestions()) {
            if (this.equals(question)) return index;
            index++;
        }
        throw new IllegalStateException(
                "The question (%s) is not contained in the survey.".formatted(topic)
        );
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

    public int getAnswersCount() {
        return answers.size();
    }

    public Iterable<Answer> getAnswers() {
        return answers;
    }

    public Answer getAnswer(int index) {
        return answers.get(index);
    }

    public int addAnswer(int index, Answer answer) {
        checkForEditable();
        if (index < 0) throw new IndexOutOfBoundsException("Index must be greater then 0, but was " + index);
        if (index > answers.size()) {
            index = answers.size();
        }
        answers.add(index, answer);
        answer.setQuestion(this);
        return index;
    }

    public int addAnswer(Answer answer) {
        checkForEditable();
        answers.add(answer);
        answer.setQuestion(this);
        return answers.size();
    }

    public Answer removeAnswer(int index) {
        checkForEditable();
        return answers.remove(index);
    }

    public void clearAnswersCounts() {
        answers.forEach(Answer::clearCount);
    }

    public void swapAnswers(int index1, int index2) {
        checkForEditable();
        if (index1 != index2) {
            Answer answer1 = answers.remove(index1);
            Answer answer2 = answers.remove(index2);
            answers.add(index2, answer1);
            answers.add(index1, answer2);
        }
    }

    public int answer(int answerIndex) {
        if (!isAnswered()) {
            throw new IllegalStateException("The question is not opened and cannot be answered");
        }
        return answers.get(answerIndex).incrementCount();
    }

    public void open() {
        if (status == ARCHIVED) {
            throw new IllegalStateException("The question is not editable and cannot be opened");
        }
        status = ANSWERS_ONLY;
    }

    public void close() {
        status = ARCHIVED;
    }

    private void checkForEditable() {
        if (!isEditable()) {
            throw new IllegalStateException("The question is closed and cannot be edited");
        }
    }
}
