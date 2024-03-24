package com.xma.surveys.services;

import com.xma.surveys.entities.Survey;
import com.xma.surveys.repositories.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    public Survey create(Survey survey) {
        return surveyRepository.save(survey);
    }

    public Optional<Survey> findById(UUID id) {
        return surveyRepository.findById(id);
    }

    public List<Survey> getAll() {
        return surveyRepository.findAll();
    }

    public Survey update(Survey survey) {
        UUID id = survey.getSurveyId();
        if (!surveyRepository.existsById(id)) {
            notExists(id);
        }
        return surveyRepository.save(survey);
    }

    public void delete(UUID surveyId) {
        surveyRepository.deleteById(surveyId);
    }

    private static void notExists(UUID id) {
        throw new NoSuchElementException("No such survey with id=" + id);
    }

}
