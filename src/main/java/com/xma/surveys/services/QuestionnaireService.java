package com.xma.surveys.services;

import com.xma.surveys.entities.Survey;
import com.xma.surveys.repositories.AnswerRepository;
import com.xma.surveys.repositories.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
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

}
