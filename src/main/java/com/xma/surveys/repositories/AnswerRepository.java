package com.xma.surveys.repositories;

import com.xma.surveys.entities.AnswerEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class AnswerRepository {
    private final EntityManager entityManager;

    public Optional<AnswerEntity> find(UUID answerId) {
        try {
            return Optional.of(entityManager
                    .createQuery(
                            "from AnswerEntity where answerId = :id",
                            AnswerEntity.class)
                    .setParameter("id", answerId)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
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

    public boolean delete(UUID answerId) {
        entityManager.getTransaction().begin();
        boolean deleted = entityManager.createQuery("delete from AnswerEntity where answerId = :id")
                .setParameter("id", answerId)
                .executeUpdate() == 1;
        entityManager.getTransaction().commit();
        return deleted;
    }

    public List<AnswerEntity> findAll() {
        return entityManager
                .createQuery("from AnswerEntity", AnswerEntity.class)
                .getResultList();
    }

    public int incrementCount(UUID answerId) {
        AnswerEntity answerEntity = entityManager.find(AnswerEntity.class, answerId);
        int count = answerEntity.incrementCount();
        entityManager.persist(answerEntity);
        return count;
    }

}
