package com.xma.surveys.controllers.survey;

import com.xma.surveys.controllers.dto.SurveyDto;
import com.xma.surveys.entities.Survey;
import com.xma.surveys.services.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
@RequestMapping("/surveys")
public class SurveyViewController {
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
}
