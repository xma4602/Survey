package com.xma.surveys.services;

import com.xma.surveys.entities.Question;
import com.xma.surveys.entities.QuestionnaireStatistic;
import com.xma.surveys.entities.Survey;
import com.xma.surveys.repositories.AnswerRepository;
import com.xma.surveys.repositories.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionnaireService {

    private final SurveyRepository surveyRepository;
    private final AnswerRepository answerRepository;

    public Survey getQuestionnaire(UUID surveyId) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(
                () -> new NoSuchElementException("No such Survey with id=" + surveyId)
        );
        survey.getQuestions().removeIf(question -> !question.isVisible());
        return survey;
    }

    public void writeDownAnswers(Collection<UUID> answersIds) {
        answerRepository.incrementCounts(answersIds);
    }

    public List<QuestionnaireStatistic> getStatistics(UUID surveyId) {
        Survey survey = surveyRepository.findById(surveyId).orElseThrow(
                () -> new NoSuchElementException("No such Survey with id=" + surveyId)
        );
        return survey.getQuestions().stream()
                .filter(Question::isVisible)
                .map(QuestionnaireStatistic::new)
                .toList();
    }
}
