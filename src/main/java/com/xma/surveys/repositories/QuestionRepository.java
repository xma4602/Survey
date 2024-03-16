package com.xma.surveys.repositories;

import com.xma.surveys.entities.Question;
import com.xma.surveys.entities.QuestionStatus;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@Repository
public class QuestionRepository {
    private final EntityManager entityManager;

    public Optional<Question> find(UUID questionId) {
        return Optional.of(entityManager.find(Question.class, questionId));
    }

    public Question save(Question question) {
        entityManager.getTransaction().begin();
        entityManager.persist(question);
        entityManager.getTransaction().commit();
        return question;
    }

    public Question update(Question question) {
        entityManager.getTransaction().begin();
        question = entityManager.merge(question);
        entityManager.getTransaction().commit();
        return question;
    }

    public boolean delete(UUID questionId) {
        entityManager.getTransaction().begin();
        boolean deleted = entityManager.createQuery("delete from Question where questionId = :id")
                .setParameter("id", questionId)
                .executeUpdate() == 1;
        entityManager.getTransaction().commit();
        return deleted;
    }

    public List<Question> findBySurveyId(UUID id) {
        return entityManager
                .createQuery("from Question where surveyId = :id", Question.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Question> findAll() {
        return entityManager
                .createQuery("from Question", Question.class)
                .getResultList();
    }

    public List<Question> findClosedQuestions() {
        return entityManager
                .createQuery(
                        "from Question where status = :status", Question.class)
                .setParameter("status", QuestionStatus.EDIT_ONLY)
                .getResultList();
    }

    public List<Question> findOpenedQuestions() {
        return entityManager
                .createQuery(
                        "from Question where status = :status", Question.class)
                .setParameter("status", QuestionStatus.ANSWERS_ONLY)
                .getResultList();
    }
}
