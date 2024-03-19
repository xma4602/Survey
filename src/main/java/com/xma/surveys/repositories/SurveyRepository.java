package com.xma.surveys.repositories;

import com.xma.surveys.entities.Survey;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class SurveyRepository {
    private final EntityManager entityManager;

    public Optional<Survey> find(UUID surveyId) {
        return Optional.of(entityManager.find(Survey.class, surveyId));
    }

    @Transactional
    public Survey save(Survey survey) {
        entityManager.persist(survey);
        return survey;
    }

    @Transactional
    public Survey update(Survey survey) {
        return entityManager.merge(survey);
    }

    @Transactional
    public boolean delete(UUID serveyId) {
        return entityManager.createQuery("delete from Survey where id = :id")
                .setParameter("id", serveyId)
                .executeUpdate() == 1;
    }

    public List<Survey> findAll() {
        return entityManager
                .createQuery("from Survey", Survey.class)
                .getResultList();
    }
}
