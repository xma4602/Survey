package com.xma.surveys.repositories;

import com.xma.surveys.entities.Answer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transactional;
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

    @Transactional
    public Answer save(Answer answer) {
        entityManager.persist(answer);
        return answer;
    }

    @Transactional
    public Answer update(Answer answer) {
        return entityManager.merge(answer);
    }

    @Transactional
    public boolean delete(UUID answerId) {
        return entityManager.createQuery("delete from Answer where answerId = :id")
                .setParameter("id", answerId)
                .executeUpdate() == 1;
    }

    public List<Answer> findAll() {
        return entityManager
                .createQuery("from Answer a order by questionId, a.index", Answer.class)
                .getResultList();
    }

    public List<Answer> findByQuestionId(UUID id) {
        return entityManager
                .createQuery("from Answer as a where questionId = :id order by questionId, a.index", Answer.class)
                .setParameter("id", id)
                .getResultList();
    }

    public boolean existQuestion(UUID questionId) {
        try {
            entityManager
                    .createQuery("from Question where questionId = :id", Answer.class)
                    .setParameter("id", questionId)
                    .getSingleResult();
            return true;
        } catch (NoResultException e) {
            return false;
        }

    }
}
