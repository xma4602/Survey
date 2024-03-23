package com.xma.surveys.controllers;

import com.xma.surveys.entities.Question;
import com.xma.surveys.entities.QuestionStatus;
import com.xma.surveys.entities.QuestionType;
import com.xma.surveys.services.QuestionService;
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
@RequestMapping("/questions")
public class QuestionController {
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

    @GetMapping("/items")
    @ResponseBody
    public List<QuestionDto> getQuestions(@RequestParam(required = false) UUID surveyId) {
        List<Question> list = surveyId == null ?
                questionService.findAll() :
                questionService.findBySurveyId(surveyId);
        return list.stream().map(QuestionDto::new).toList();
    }

    @PostMapping("/create")
    @ResponseBody
    public QuestionDto createQuestion(@RequestBody QuestionDto dto) {
        return new QuestionDto(questionService.create(dto.toQuestion()));
    }

    @PutMapping("/update")
    @ResponseBody
    public QuestionDto updateQuestion(@RequestBody QuestionDto dto) {
        return new QuestionDto(questionService.update(dto.toQuestion()));
    }

    @PutMapping("/open")
    @ResponseBody
    public QuestionDto openQuestion(@RequestParam UUID questionId) {
        return new QuestionDto(questionService.openQuestion(questionId));
    }

    @PutMapping("/close")
    @ResponseBody
    public QuestionDto closeQuestion(@RequestParam UUID questionId) {
        return new QuestionDto(questionService.closeQuestion(questionId));
    }


    @PutMapping("/clear")
    @ResponseBody
    public QuestionDto clearQuestion(@RequestParam UUID questionId) {
        return new QuestionDto(questionService.clearQuestion(questionId));
    }


    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteQuestion(@RequestParam UUID questionId) {
        questionService.delete(questionId);
    }

    @Data
    private static class QuestionDto {
        UUID questionId;
        UUID surveyId;
        String topic;
        QuestionStatus status;
        QuestionType type;
        int index;
        int count;

        public QuestionDto(Question question) {
            questionId = question.getQuestionId();
            surveyId = question.getSurveyId();
            topic = question.getTopic();
            index = question.getIndex();
            status = question.getStatus();
            type = question.getType();
            count = question.getAnswersCount();
        }

        public QuestionDto() {
            surveyId = new UUID(0, 0);
            topic = "Введите тему вопроса";
            status = QuestionStatus.EDIT_ONLY;
            type = QuestionType.SINGLE;
            count = 0;
        }

        public Question toQuestion() {
            Question question = new Question();
            question.setQuestionId(questionId);
            question.setSurveyId(surveyId);
            question.setType(type);
            question.setStatus(status);
            question.setIndex(index);
            question.setTopic(topic);
            return question;
        }
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Map<String, String>> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", e.getMessage()));
    }

}
