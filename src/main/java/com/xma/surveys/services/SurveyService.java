package com.xma.surveys.services;

import com.xma.surveys.entities.Survey;
import com.xma.surveys.repositories.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Survey> getAll() {
        return surveyRepository.findAll(PageRequest.ofSize(Integer.MAX_VALUE));
    }

    public boolean update(Survey survey) {
        surveyRepository.update(survey);
        return true;
    }

    public boolean delete(UUID surveyId) {
        return surveyRepository.delete(surveyId);
    }
}
