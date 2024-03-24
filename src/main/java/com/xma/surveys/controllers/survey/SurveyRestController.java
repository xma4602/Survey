package com.xma.surveys.controllers.survey;

import com.xma.surveys.controllers.dto.SurveyDto;
import com.xma.surveys.services.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/surveys")
public class SurveyRestController {
    private final SurveyService surveyService;

    @GetMapping("/items")
    public List<SurveyDto> getSurveys() {
        return surveyService.getAll().stream().map(SurveyDto::new).toList();
    }

    @PostMapping("/create")
    public SurveyDto createSurvey(@RequestBody SurveyDto dto) {
        return new SurveyDto(surveyService.create(dto.toSurvey()));
    }

    @PutMapping("/update")
    public SurveyDto updateSurvey(@RequestBody SurveyDto dto) {
        return new SurveyDto(surveyService.update(dto.toSurvey()));
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteSurvey(@RequestParam UUID surveyId) {
        surveyService.delete(surveyId);
    }



}
