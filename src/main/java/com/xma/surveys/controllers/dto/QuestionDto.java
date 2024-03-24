package com.xma.surveys.controllers.dto;

import com.xma.surveys.entities.Question;
import com.xma.surveys.entities.QuestionStatus;
import com.xma.surveys.entities.QuestionType;
import lombok.Data;

import java.util.UUID;

@Data
public class QuestionDto {
    UUID questionId;
    UUID surveyId;
    String topic;
    QuestionStatus status;
    QuestionType type;
    int index;
    int count;

    public QuestionDto(Question question) {
        questionId = question.getQuestionId();
        surveyId = question.getSurveyId();
        topic = question.getTopic();
        index = question.getIndex();
        status = question.getStatus();
        type = question.getType();
        count = question.getAnswersCountSum();
    }

    public QuestionDto() {
        surveyId = new UUID(0, 0);
        topic = "Введите тему вопроса";
        status = QuestionStatus.EDIT_ONLY;
        type = QuestionType.SINGLE;
        count = 0;
    }

    public Question toQuestion() {
        Question question = new Question();
        question.setQuestionId(questionId);
        question.setSurveyId(surveyId);
        question.setType(type);
        question.setStatus(status);
        question.setIndex(index);
        question.setTopic(topic);
        return question;
    }
}