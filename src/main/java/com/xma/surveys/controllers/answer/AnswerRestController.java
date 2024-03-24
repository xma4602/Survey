package com.xma.surveys.controllers.answer;

import com.xma.surveys.controllers.dto.AnswerDto;
import com.xma.surveys.entities.Answer;
import com.xma.surveys.services.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/answers")
public class AnswerRestController {
    private final AnswerService answerService;

    @GetMapping("/items")
    public List<AnswerDto> getAnswersById(@RequestParam(required = false) UUID questionId) {
        List<Answer> list = questionId == null ?
                answerService.findAll() :
                answerService.findByQuestionId(questionId);
        return list.stream().map(AnswerDto::new).toList();
    }

    @PostMapping("/create")
    public AnswerDto createAnswer(@RequestBody AnswerDto dto) {
        return new AnswerDto(answerService.create(dto.toAnswer()));
    }

    @PutMapping("/update")
    public AnswerDto updateAnswer(@RequestBody AnswerDto dto) {
        return new AnswerDto(answerService.update(dto.toAnswer()));
    }

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAnswer(@RequestParam UUID answerId) {
        answerService.delete(answerId);
    }

}
