package com.xma.surveys.controllers.questionnaire;

import com.xma.surveys.controllers.dto.QuestionnaireDto;
import com.xma.surveys.entities.QuestionnaireStatistic;
import com.xma.surveys.services.QuestionnaireService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/questionnaires")
@RequiredArgsConstructor
public class QuestionnaireRestController {
    private final QuestionnaireService questionnaireService;

    @GetMapping("/items")
    public QuestionnaireDto getQuestionnaire(@RequestParam UUID surveyId) {
        return new QuestionnaireDto(questionnaireService.getQuestionnaire(surveyId));
    }

    @PostMapping("/submit")
    @ResponseStatus(HttpStatus.OK)
    public List<QuestionnaireStatistic> submitQuestionnaire(@RequestParam UUID surveyId, @RequestBody List<UUID> answersIds){
        questionnaireService.writeDownAnswers(answersIds);
        return questionnaireService.getStatistics(surveyId);
    }

}
