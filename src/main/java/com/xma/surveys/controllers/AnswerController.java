package com.xma.surveys.controllers;

import com.xma.surveys.entities.Answer;
import com.xma.surveys.services.AnswerService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/answers")
public class AnswerController {
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

    @GetMapping("/items")
    @ResponseBody
    public List<AnswerDto> getAnswersById(@RequestParam(required = false) UUID questionId) {
        List<Answer> list = questionId == null ?
                answerService.findAll() :
                answerService.findByQuestionId(questionId);
        return list.stream().map(AnswerDto::new).toList();
    }


    @PostMapping("/create")
    @ResponseBody
    public AnswerDto createAnswer(@RequestBody AnswerDto dto) {
        return new AnswerDto(answerService.create(dto.toAnswer()));
    }

    @PutMapping("/update")
    @ResponseBody
    public AnswerDto updateAnswer(@RequestBody AnswerDto dto) {
        return new AnswerDto(answerService.update(dto.toAnswer()));
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAnswer(@RequestParam UUID answerId) {
        answerService.delete(answerId);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.entry("message", e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.entry("message", e.getMessage()));
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    private static class AnswerDto {
        UUID questionId;
        UUID answerId;
        String text;
        int count;
        int index;

        public AnswerDto(Answer answer) {
            answerId = answer.getAnswerId();
            questionId = answer.getQuestionId();
            text = answer.getText();
            count = answer.getCount();
            index = answer.getIndex();
        }

        public Answer toAnswer() {
            Answer answer = new Answer();
            answer.setAnswerId(answerId);
            answer.setText(text);
            answer.setCount(count);
            answer.setIndex(index);
            answer.setQuestionId(questionId);
            return answer;
        }
    }
}
