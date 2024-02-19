package com.xma.surveys.repositories;

import com.xma.surveys.entities.AnswerEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class AnswerRepository {
    private final EntityManager entityManager;

    public Optional<AnswerEntity> findAnswer(UUID questionId, int answerIndex) {
        try {
            return Optional.of(entityManager
                    .createQuery(
                            "from AnswerEntity where id.questionId = :id and id.index = :index",
                            AnswerEntity.class)
                    .setParameter("id", questionId)
                    .setParameter("index", answerIndex)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public int incrementAnswerCount(UUID questionId, int answerIndex) {
        return entityManager
                .createQuery(
                        "update AnswerEntity set count = count + 1 where id.questionId = :id and id.index = :index",
                        Integer.class)
                .setParameter("id", questionId)
                .setParameter("index", answerIndex)
                .getSingleResult();

    }

    public AnswerEntity save(AnswerEntity answer) {
        entityManager.getTransaction().begin();
        entityManager.persist(answer);
        entityManager.getTransaction().commit();
        return answer;
    }

    public AnswerEntity update(AnswerEntity answer) {
        entityManager.getTransaction().begin();
        answer = entityManager.merge(answer);
        entityManager.getTransaction().commit();
        return answer;
    }

}
