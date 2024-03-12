package com.xma.surveys.repositories;

import com.xma.surveys.SqlManager;
import com.xma.surveys.entities.QuestionEntity;
import com.xma.surveys.model.QuestionStatus;
import com.xma.surveys.model.QuestionType;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class QuestionRepositoryTest {
    EntityManager entityManager;

    @BeforeAll
    static void openSession() {
        SqlManager.openSession();
    }

    @BeforeEach
    void insertData() {
        entityManager = SqlManager.insertData();
    }

    @AfterEach
    void deleteData() {
        SqlManager.deleteData();
    }

    @AfterAll
    static void closeSession() {
        SqlManager.closeSession();
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "f728b4fa-4248-4e3a-8a5d-2f346baa9455",
            "11ebcd49-428a-4c22-95fd-b76a19fbeb1d",
            "03a89879-36a9-4d74-80de-59f550f0fc2b"})
    void find(String value) {
        QuestionRepository repository = new QuestionRepository(entityManager);
        UUID uuid = UUID.fromString(value);

        assertTrue(repository.find(uuid).isPresent());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void save(int value) {
        QuestionRepository repository = new QuestionRepository(entityManager);
        QuestionEntity questionEntity = new QuestionEntity();
        questionEntity.setTopic("text" + value);
        questionEntity.setStatus(QuestionStatus.ANSWERS_ONLY);
        questionEntity.setType(QuestionType.SINGLE);

        questionEntity = repository.save(questionEntity);

        Optional<QuestionEntity> optional = repository.find(questionEntity.getQuestionId());
        assertTrue(optional.isPresent());
        assertEquals(questionEntity, optional.get());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "f728b4fa-4248-4e3a-8a5d-2f346baa9455",
            "11ebcd49-428a-4c22-95fd-b76a19fbeb1d",
            "03a89879-36a9-4d74-80de-59f550f0fc2b"})
    void update(String value) {
        QuestionRepository repository = new QuestionRepository(entityManager);
        UUID uuid = UUID.fromString(value);
        QuestionEntity questionEntity1 = repository.find(uuid).get();

        questionEntity1.setTopic("text");
        questionEntity1.setStatus(QuestionStatus.ANSWERS_ONLY);
        questionEntity1.setType(QuestionType.SINGLE);
        questionEntity1 = repository.update(questionEntity1);
        QuestionEntity questionEntity2 = repository.find(uuid).get();

        assertEquals(questionEntity2, questionEntity1);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "f728b4fa-4248-4e3a-8a5d-2f346baa9455",
            "11ebcd49-428a-4c22-95fd-b76a19fbeb1d",
            "03a89879-36a9-4d74-80de-59f550f0fc2b"})
    void delete(String value) {
        QuestionRepository repository = new QuestionRepository(entityManager);
        UUID uuid = UUID.fromString(value);

        assertTrue(repository.delete(uuid));
    }

    @Test
    void findAll() {
        QuestionRepository repository = new QuestionRepository(entityManager);
        assertEquals(7, repository.findAll().size());
    }

    @Test
    void findClosedQuestions() {
        QuestionRepository repository = new QuestionRepository(entityManager);
        assertEquals(2, repository.findClosedQuestions().size());
    }

    @Test
    void findOpenedQuestions() {
        QuestionRepository repository = new QuestionRepository(entityManager);
        assertEquals(2, repository.findOpenedQuestions().size());
    }
}