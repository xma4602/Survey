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

    public Optional<AnswerEntity> find(UUID questionId, int answerIndex) {
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

    public boolean delete(UUID questionId) {
        return entityManager.createQuery("delete from AnswerEntity where id.questionId = :id")
                .setParameter("id", questionId)
                .executeUpdate() == 1;
    }

    public List<AnswerEntity> findAll() {
        return entityManager
                .createQuery("from AnswerEntity", AnswerEntity.class)
                .getResultList();
    }

    public int incrementCount(UUID questionId, int answerIndex) {
        return entityManager
                .createQuery(
                        "update AnswerEntity set count = count + 1 where id.questionId = :id and id.index = :index",
                        Integer.class)
                .setParameter("id", questionId)
                .setParameter("index", answerIndex)
                .getSingleResult();

    }

}
