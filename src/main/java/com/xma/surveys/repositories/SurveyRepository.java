package com.xma.surveys.repositories;

import com.xma.surveys.entities.Survey;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class SurveyRepository {
    private final EntityManager entityManager;

    public Optional<Survey> find(UUID surveyId) {
        return Optional.of(entityManager.find(Survey.class, surveyId));
    }

    public Survey save(Survey survey) {
        entityManager.getTransaction().begin();
        entityManager.persist(survey);
        entityManager.getTransaction().commit();
        return survey;
    }

    public Survey update(Survey survey) {
        entityManager.getTransaction().begin();
        survey = entityManager.merge(survey);
        entityManager.getTransaction().commit();
        return survey;
    }

    public boolean delete(UUID serveyId) {
        entityManager.getTransaction().begin();
        boolean deleted = entityManager.createQuery("delete from Survey where id = :id")
                .setParameter("id", serveyId)
                .executeUpdate() == 1;
        entityManager.getTransaction().commit();
        return deleted;
    }

    public List<Survey> findAll() {
        return entityManager
                .createQuery("from Survey", Survey.class)
                .getResultList();
    }
}
