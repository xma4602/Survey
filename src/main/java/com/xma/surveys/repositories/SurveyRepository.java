package com.xma.surveys.repositories;

import com.xma.surveys.entities.QuestionEntity;
import com.xma.surveys.entities.SurveyEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class SurveyRepository {
    private final EntityManager entityManager;

    public Optional<SurveyEntity> find(UUID surveyId) {
        return Optional.of(entityManager.find(SurveyEntity.class, surveyId));
    }

    public List<QuestionEntity> findSurveyQuestions(UUID surveyId) {
        return entityManager
                .createQuery(
                        "from QuestionEntity where id.surveyId = :id order by id.index",
                        QuestionEntity.class)
                .setParameter("id", surveyId)
                .getResultList();

    }

    public SurveyEntity save(SurveyEntity survey) {
        entityManager.getTransaction().begin();
        entityManager.persist(survey);
        entityManager.getTransaction().commit();
        return survey;
    }

    public SurveyEntity update(SurveyEntity survey) {
        entityManager.getTransaction().begin();
        survey = entityManager.merge(survey);
        entityManager.getTransaction().begin();
        return survey;
    }
}
