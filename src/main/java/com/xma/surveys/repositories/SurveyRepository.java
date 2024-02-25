package com.xma.surveys.repositories;

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

    public SurveyEntity save(SurveyEntity survey) {
        entityManager.getTransaction().begin();
        entityManager.persist(survey);
        entityManager.getTransaction().commit();
        return survey;
    }

    public SurveyEntity update(SurveyEntity survey) {
        entityManager.getTransaction().begin();
        survey = entityManager.merge(survey);
        entityManager.getTransaction().commit();
        return survey;
    }

    public boolean delete(UUID serveyId) {
        entityManager.getTransaction().begin();
        boolean deleted = entityManager.createQuery("delete from SurveyEntity where id = :id")
                .setParameter("id", serveyId)
                .executeUpdate() == 1;
        entityManager.getTransaction().commit();
        return deleted;
    }

    public List<SurveyEntity> findAll() {
        return entityManager
                .createQuery("from SurveyEntity", SurveyEntity.class)
                .getResultList();
    }
}
