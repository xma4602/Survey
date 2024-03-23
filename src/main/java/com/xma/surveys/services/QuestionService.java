package com.xma.surveys.services;

import com.xma.surveys.entities.Question;
import com.xma.surveys.repositories.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Question create(Question question) {
        return questionRepository.save(question);
    }

    public Optional<Question> findById(UUID questionId) {
        return questionRepository.findById(questionId);
    }

    public List<Question> findBySurveyId(UUID surveyId) {
        return questionRepository.findBySurveyId(surveyId);
    }

    public List<Question> findAll() {
        return questionRepository.findAll();
    }

    public Question update(Question question) {
        UUID questionId = question.getQuestionId();
        if (!questionRepository.existsById(questionId)) {
            notExists(questionId);
        }
        return questionRepository.save(question);
    }

    public void delete(UUID questionId) {
        questionRepository.deleteById(questionId);
    }

    public Question openQuestion(UUID questionId) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isEmpty()){
            notExists(questionId);
        }
        Question question = questionOptional.get();
        question.open();
        return questionRepository.save(question);
    }

    public Question closeQuestion(UUID questionId) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isEmpty()){
            notExists(questionId);
        }
        Question question = questionOptional.get();
        question.close();
        return questionRepository.save(question);
    }

    public Question clearQuestion(UUID questionId) {
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (questionOptional.isEmpty()){
            notExists(questionId);
        }
        Question question = questionOptional.get();
        question.clearAnswers();
        return questionRepository.save(question);
    }

    private static void notExists(UUID questionId) {
        throw new NoSuchElementException("No such question with questionId=" + questionId);
    }

}
