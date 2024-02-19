package com.xma.surveys.repositories;

import com.xma.surveys.entities.AnswerEntity;
import com.xma.surveys.entities.QuestionEntity;
import com.xma.surveys.model.QuestionStatus;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
public class QuestionRepository {
    private final EntityManager entityManager;

    public Optional<QuestionEntity> findQuestion(UUID questionId) {
        return Optional.of(entityManager.find(QuestionEntity.class, questionId));
    }

    public List<AnswerEntity> findQuestionAnswers(UUID questionId) {
        return entityManager
                .createQuery(
                        "from AnswerEntity where id.questionId = :id order by id.index",
                        AnswerEntity.class)
                .setParameter("id", questionId)
                .getResultList();

    }

    public List<QuestionEntity> findClosedQuestions() {
        return entityManager
                .createQuery(
                        "from QuestionEntity where status = :status", QuestionEntity.class)
                .setParameter("status", QuestionStatus.EDIT_ONLY)
                .getResultList();
    }

    public List<QuestionEntity> findOpenedQuestions() {
        return entityManager
                .createQuery(
                        "from QuestionEntity where status = :status", QuestionEntity.class)
                .setParameter("status", QuestionStatus.ANSWERS_ONLY)
                .getResultList();
    }

    public QuestionEntity save(QuestionEntity question) {
        entityManager.getTransaction().begin();
        entityManager.persist(question);
        entityManager.getTransaction().commit();
        return question;
    }

    public QuestionEntity update(QuestionEntity question) {
        entityManager.getTransaction().begin();
        question = entityManager.merge(question);
        entityManager.getTransaction().commit();
        return question;
    }
}