package com.xma.surveys.services;

import com.xma.surveys.entities.Question;
import com.xma.surveys.entities.QuestionType;
import com.xma.surveys.repositories.QuestionRepository;
import com.xma.surveys.statistic.QuestionStatistic;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;

    public Question create(String topic, QuestionType type){
        Question question = new Question();
        question.setTopic(topic);
        question.setType(type);
        return questionRepository.save(question);
    }

    public Optional<Question> findById(UUID id) {
        return questionRepository.find(id);
    }

    public List<Question> findBySurveyId(UUID id) {
        return questionRepository.findBySurveyId(id);
    }

    public void openQuestion(UUID id) {
        Question question = questionRepository.find(id)
                .orElseThrow(() -> new NoSuchElementException("No such question with id=" + id));
        question.open();
        questionRepository.update(question);
    }

    public void closeQuestion(UUID id) {
        Question question = questionRepository.find(id)
                .orElseThrow(() -> new NoSuchElementException("No such question with id=" + id));
        question.close();
        questionRepository.update(question);
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
