package com.xma.surveys.controllers;

import com.xma.surveys.entities.Survey;
import com.xma.surveys.services.SurveyService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/surveys")
public class SurveyController {
    private final SurveyService surveyService;

    @GetMapping("/new")
    public String getCreatePage(Model model) {
        model.addAttribute("header", "Создание нового опроса");
        model.addAttribute("isHidden", "hidden");
        model.addAttribute("placeholder", new SurveyDto());
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
        model.addAttribute("survey", surveyOptional.get());
        model.addAttribute("method", "edit");

        return "surveys_edit";
    }

    @GetMapping("/items")
    @ResponseBody
    public List<SurveyDto> getSurveys() {
        return surveyService.getAll().stream().map(SurveyDto::new).toList();
    }

    @PostMapping("/create")
    @ResponseBody
    public SurveyDto createSurvey(@RequestBody SurveyDto dto) {
        return new SurveyDto(surveyService.create(dto.toSurvey()));
    }

    @PutMapping("/update")
    @ResponseBody
    public SurveyDto updateSurvey(@RequestBody SurveyDto dto) {
        return new SurveyDto(surveyService.update(dto.toSurvey()));
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSurvey(@RequestParam UUID surveyId) {
        surveyService.delete(surveyId);
    }

    @Data
    private static class SurveyDto {
        UUID surveyId;
        String title;
        int count;

        public SurveyDto(Survey survey) {
            surveyId = survey.getSurveyId();
            title = survey.getTitle();
            count = survey.getAnswersCountSum();
        }

        public SurveyDto() {
            surveyId = new UUID(0, 0);
            title = "Введите заголовок опроса";
            count = 0;
        }

        public Survey toSurvey() {
            Survey survey = new Survey();
            survey.setSurveyId(surveyId);
            survey.setTitle(title);
            return survey;
        }
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", e.getMessage()));
    }

}
