package com.xma.surveys.services;

import com.xma.surveys.entities.Survey;
import com.xma.surveys.repositories.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    public Survey create(String title) {
        Survey survey = new Survey();
        survey.setTitle(title);
        return surveyRepository.save(survey);
    }

    public Optional<Survey> findById(UUID id) {
        return surveyRepository.find(id);
    }

}
