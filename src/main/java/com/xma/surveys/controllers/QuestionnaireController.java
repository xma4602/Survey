package com.xma.surveys.controllers;

import com.xma.surveys.entities.*;
import com.xma.surveys.services.QuestionnaireService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/questionnaires")
@RequiredArgsConstructor
public class QuestionnaireController {
    private final QuestionnaireService questionnaireService;

    @GetMapping("/items")
    @ResponseBody
    public QuestionnaireDto getQuestionnaire(@RequestParam UUID surveyId) {
        return new QuestionnaireDto(questionnaireService.getQuestionnaire(surveyId));
    }

    @PostMapping("/submit")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public List<QuestionnaireStatistic> submitQuestionnaire(@RequestParam UUID surveyId, @RequestBody List<UUID> answersIds){
        questionnaireService.writeDownAnswers(answersIds);
        return questionnaireService.getStatistics(surveyId);
    }

    @Data
    private static class QuestionnaireDto {
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


}
