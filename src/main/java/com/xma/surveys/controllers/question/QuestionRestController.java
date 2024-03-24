package com.xma.surveys.controllers.question;

import com.xma.surveys.controllers.dto.QuestionDto;
import com.xma.surveys.entities.Question;
import com.xma.surveys.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/questions")
public class QuestionRestController {
    private final QuestionService questionService;

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

}
