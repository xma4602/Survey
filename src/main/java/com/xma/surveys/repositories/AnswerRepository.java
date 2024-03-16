package com.xma.surveys.repositories;

import com.xma.surveys.entities.Answer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class AnswerRepository {
    private final EntityManager entityManager;

    public Optional<Answer> find(UUID answerId) {
        try {
            return Optional.of(entityManager
                    .createQuery(
                            "from Answer where answerId = :id",
                            Answer.class)
                    .setParameter("id", answerId)
                    .getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    public Answer save(Answer answer) {
        entityManager.getTransaction().begin();
        entityManager.persist(answer);
        entityManager.getTransaction().commit();
        return answer;
    }

    public Answer update(Answer answer) {
        entityManager.getTransaction().begin();
        answer = entityManager.merge(answer);
        entityManager.getTransaction().commit();
        return answer;
    }

    public boolean delete(UUID answerId) {
        entityManager.getTransaction().begin();
        boolean deleted = entityManager.createQuery("delete from Answer where answerId = :id")
                .setParameter("id", answerId)
                .executeUpdate() == 1;
        entityManager.getTransaction().commit();
        return deleted;
    }

    public List<Answer> findAll() {
        return entityManager
                .createQuery("from Answer", Answer.class)
                .getResultList();
    }

    public List<Answer> findByQuestionId(UUID id) {
        return entityManager
                .createQuery("from Answer where questionId = :id", Answer.class)
                .setParameter("id", id)
                .getResultList();
    }
}
