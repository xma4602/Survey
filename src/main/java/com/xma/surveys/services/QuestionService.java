package com.xma.surveys.services;

import com.xma.surveys.entities.Question;
import com.xma.surveys.entities.QuestionType;
import com.xma.surveys.repositories.QuestionRepository;
import com.xma.surveys.statistic.QuestionStatistic;
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

    public Optional<Question> findById(UUID id) {
        return questionRepository.find(id);
    }

    public List<Question> findBySurveyId(UUID id) {
        return questionRepository.findBySurveyId(id);
    }

    public List<Question> getAll() {
        return questionRepository.findAll();
    }

    public Question update(Question question) {
        UUID id = question.getQuestionId();
        questionRepository.find(id).orElseThrow(
                () -> new NoSuchElementException("No such question with id=" + id)
        );
        return questionRepository.update(question);
    }

    public boolean delete(UUID questionId) {
        return questionRepository.delete(questionId);
    }

    public Question openQuestion(UUID id) {
        Question question = questionRepository.find(id)
                .orElseThrow(() -> new NoSuchElementException("No such question with id=" + id));
        question.open();
        return questionRepository.update(question);
    }

    public Question closeQuestion(UUID id) {
        Question question = questionRepository.find(id)
                .orElseThrow(() -> new NoSuchElementException("No such question with id=" + id));
        question.close();
        return questionRepository.update(question);
    }

    public Question clearQuestion(UUID id) {
        Question question = questionRepository.find(id)
                .orElseThrow(() -> new NoSuchElementException("No such question with id=" + id));
        question.clearAnswers();
        return questionRepository.update(question);
    }

    public List<Question> getOpenedQuestions(UUID id) {
        return findBySurveyId(id).stream()
                .filter(Question::isVisible)
                .toList();
    }

    public List<QuestionStatistic> getOpenedStatistics(UUID id) {
        return findBySurveyId(id).stream()
                .filter(Question::isVisible)
                .map(QuestionStatistic::new)
                .toList();
    }

    public List<QuestionStatistic> getClosedStatistics(UUID id) {
        return findBySurveyId(id).stream()
                .filter(question -> !question.isVisible() && !question.isEditable())
                .map(QuestionStatistic::new)
                .toList();
    }

}
