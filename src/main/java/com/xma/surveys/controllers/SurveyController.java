package com.xma.surveys.controllers;

import com.xma.surveys.entities.Survey;
import com.xma.surveys.services.SurveyService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/surveys")
public class SurveyController {
    private final SurveyService surveyService;

    @GetMapping("/new")
    public String getCreatePage(Model model) {
        model.addAttribute("header", "Создание нового опроса");
        model.addAttribute("isHidden", "hidden");
        model.addAttribute("survey", new Survey());
        model.addAttribute("method", "create");
        return "surveys_edit";
    }

    @GetMapping("/edit")
    public String getEditPage(@RequestParam UUID surveyId, Model model) {
        Optional<Survey> surveyOptional = surveyService.findById(surveyId);
        surveyOptional.orElseThrow(() -> new NoSuchElementException(
                "No such element with id=" + surveyId
        ));
        model.addAttribute("header", "Редактирование опроса");
        model.addAttribute("isDisabled", "disabled");
        model.addAttribute("survey", surveyOptional.get());
        model.addAttribute("method", "edit");

        return "surveys_edit";
    }

    @GetMapping("/items")
    @ResponseBody
    public List<SurveyDto> getSurveys() {
        return surveyService.getAll().stream().map(SurveyDto::new).toList();
    }

    @PostMapping("/crate")
    @ResponseBody
    public Survey createSurvey(@RequestBody SurveyDto dto) {
        return surveyService.create(dto.getTitle());
    }

    @PutMapping("/update")
    @ResponseBody
    public boolean updateSurvey(@RequestBody SurveyDto dto) {
        return surveyService.update(dto.toSurvey());
    }

    @DeleteMapping("/delete")
    @ResponseBody
    public boolean deleteSurvey(@RequestParam UUID surveyId) {
        return surveyService.delete(surveyId);
    }

    @Data
    @NoArgsConstructor
    private static class SurveyDto {
        UUID surveyId;
        String title;
        int questions;

        public SurveyDto(Survey survey) {
            surveyId = survey.getSurveyId();
            title = survey.getTitle();
            questions = survey.getQuestions().size();
        }

        public Survey toSurvey() {
            Survey survey = new Survey();
            survey.setSurveyId(surveyId);
            survey.setTitle(title);
            return survey;
        }
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

}
