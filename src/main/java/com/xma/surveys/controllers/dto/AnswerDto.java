package com.xma.surveys.controllers.dto;

import com.xma.surveys.entities.Answer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDto {
    UUID questionId;
    UUID answerId;
    String text;
    int count;
    int index;

    public AnswerDto(Answer answer) {
        answerId = answer.getAnswerId();
        questionId = answer.getQuestionId();
        text = answer.getText();
        count = answer.getCount();
        index = answer.getIndex();
    }

    public Answer toAnswer() {
        Answer answer = new Answer();
        answer.setAnswerId(answerId);
        answer.setText(text);
        answer.setCount(count);
        answer.setIndex(index);
        answer.setQuestionId(questionId);
        return answer;
    }
}