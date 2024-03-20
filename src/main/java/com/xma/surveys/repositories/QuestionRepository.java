package com.xma.surveys.repositories;

import com.xma.surveys.entities.Question;
import com.xma.surveys.entities.QuestionStatus;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
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

    @Transactional
    public Question save(Question question) {
        entityManager.persist(question);
        return question;
    }

    @Transactional
    public Question update(Question question) {
        return entityManager.merge(question);
    }

    @Transactional
    public boolean delete(UUID questionId) {
        return entityManager.createQuery("delete from Question where questionId = :id")
                .setParameter("id", questionId)
                .executeUpdate() == 1;
    }

    public List<Question> findBySurveyId(UUID id) {
        return entityManager
                .createQuery("from Question q where surveyId = :id order by surveyId, q.index", Question.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Question> findAll() {
        return entityManager
                .createQuery("from Question q order by surveyId, q.index", Question.class)
                .getResultList();
    }

    public List<Question> findClosedQuestions() {
        return entityManager
                .createQuery("from Question q where status = :status order by surveyId, q.index", Question.class)
                .setParameter("status", QuestionStatus.EDIT_ONLY)
                .getResultList();
    }

    public List<Question> findOpenedQuestions() {
        return entityManager
                .createQuery("from Question q where status = :status order by surveyId, q.index", Question.class)
                .setParameter("status", QuestionStatus.ANSWERS_ONLY)
                .getResultList();
    }
}
