package com.xma.surveys.controllers.answer;

import com.xma.surveys.controllers.dto.AnswerDto;
import com.xma.surveys.entities.Answer;
import com.xma.surveys.services.AnswerService;
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
@RequestMapping("/answers")
public class AnswerViewController {
    private final AnswerService answerService;

    @GetMapping("/new")
    public String getCreatePage(Model model) {
        model.addAttribute("header", "Создание нового ответа");
        model.addAttribute("isHidden", "hidden");
        model.addAttribute("method", "create");
        AnswerDto answer = new AnswerDto(new UUID(0, 0), new UUID(0, 0), "Введите заголовок опроса", 0, 0);
        model.addAttribute("placeholder", answer);

        return "answers_edit";
    }

    @GetMapping("/edit")
    public String getEditPage(@RequestParam UUID answerId, Model model) {
        Optional<Answer> answerOptional = answerService.findById(answerId);
        answerOptional.orElseThrow(() -> new NoSuchElementException(
                "No such element with id=" + answerId
        ));
        model.addAttribute("header", "Редактирование ответа");
        model.addAttribute("answer", answerOptional.get());
        model.addAttribute("method", "edit");

        return "answers_edit";
    }
}
