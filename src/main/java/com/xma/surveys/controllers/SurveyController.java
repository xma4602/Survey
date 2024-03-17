package com.xma.surveys.controllers;

import com.xma.surveys.entities.Survey;
import com.xma.surveys.services.SurveyService;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/surveys")
public class SurveyController {
    private final SurveyService surveyService;

    @GetMapping("create")
    public String getCreatePage(Model model) {
        model.addAttribute("isHidden", "hidden");
        model.addAttribute("title", "Создание нового опроса");
        return "surveys_edit";
    }

    @PostMapping("create")
    @ResponseBody
    public Survey createSurvey(@RequestBody SurveyDto dto) {
        return surveyService.create(dto.getTitle());
    }

    @Data
    @NoArgsConstructor
    private static class SurveyDto {
        String title;
    }
}
