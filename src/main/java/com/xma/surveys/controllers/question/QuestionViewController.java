package com.xma.surveys.controllers.question;

import com.xma.surveys.controllers.dto.QuestionDto;
import com.xma.surveys.entities.Question;
import com.xma.surveys.services.QuestionService;
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
@RequestMapping("/questions")
public class QuestionViewController {
    private final QuestionService questionService;

    @GetMapping("/new")
    public String getCreatePage(Model model) {
        model.addAttribute("header", "Создание нового вопроса");
        model.addAttribute("isHidden", "hidden");
        model.addAttribute("placeholder", new QuestionDto());
        model.addAttribute("method", "create");
        return "questions_edit";
    }

    @GetMapping("/edit")
    public String getEditPage(@RequestParam UUID questionId, Model model) {
        Optional<Question> questionOptional = questionService.findById(questionId);
        questionOptional.orElseThrow(() -> new NoSuchElementException(
                "No such element with id=" + questionId
        ));
        model.addAttribute("header", "Редактирование опроса");
        model.addAttribute("question", questionOptional.get());
        model.addAttribute("method", "edit");

        return "questions_edit";
    }
}
