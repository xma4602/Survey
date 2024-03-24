package com.xma.surveys.controllers.dto;

import com.xma.surveys.entities.Answer;
import com.xma.surveys.entities.Question;
import com.xma.surveys.entities.QuestionType;
import com.xma.surveys.entities.Survey;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class QuestionnaireDto {
    private String title;
    private List<QuestionDto> questions;

    public QuestionnaireDto(Survey survey) {
        title = survey.getTitle();
        questions = survey.getQuestions().stream()
                .map(QuestionDto::new).toList();
    }

    @Data
    private static class QuestionDto {
        private String topic;
        private QuestionType type;
        private List<AnswerDto> answers;

        public QuestionDto(Question question) {
            topic = question.getTopic();
            type = question.getType();
            answers = question.getAnswers().stream()
                    .map(AnswerDto::new).toList();
        }

        @Data
        private static class AnswerDto {
            private String text;
            private UUID answerId;

            public AnswerDto(Answer answer) {
                text = answer.getText();
                answerId = answer.getAnswerId();
            }
        }
    }
}