package com.xma.surveys.repositories;

import com.xma.surveys.SqlManager;
import com.xma.surveys.entities.AnswerEntity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AnswerRepositoryTest {
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
            "eb1167b3-67a9-4378-bc65-c1e582e2e662",
            "ba26d851-35e8-479a-baaf-0e891fb797fa",
            "e786ab37-5bca-47be-8298-17c53308fb2e"})
    void find(String value) {
        AnswerRepository repository = new AnswerRepository(entityManager);
        UUID uuid = UUID.fromString(value);

        assertTrue(repository.find(uuid).isPresent());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void save(int value) {
        AnswerRepository repository = new AnswerRepository(entityManager);
        AnswerEntity answerEntity = new AnswerEntity();
        answerEntity.setText("text" + value);

        answerEntity = repository.save(answerEntity);

        Optional<AnswerEntity> answerEntityOpt = repository.find(answerEntity.getAnswerId());
        assertTrue(answerEntityOpt.isPresent());
        assertEquals(answerEntity, answerEntityOpt.get());
    }


    @ParameterizedTest
    @ValueSource(strings = {
            "eb1167b3-67a9-4378-bc65-c1e582e2e662",
            "ba26d851-35e8-479a-baaf-0e891fb797fa",
            "e786ab37-5bca-47be-8298-17c53308fb2e"})
    void update(String value) {
        AnswerRepository repository = new AnswerRepository(entityManager);
        UUID uuid = UUID.fromString(value);
        AnswerEntity answerEntity1 = repository.find(uuid).get();

        answerEntity1.setText("text");
        answerEntity1 = repository.update(answerEntity1);
        AnswerEntity answerEntity2 = repository.find(uuid).get();

        assertEquals(answerEntity2, answerEntity1);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "eb1167b3-67a9-4378-bc65-c1e582e2e662",
            "ba26d851-35e8-479a-baaf-0e891fb797fa",
            "e786ab37-5bca-47be-8298-17c53308fb2e"})
    void delete(String value) {
        AnswerRepository repository = new AnswerRepository(entityManager);
        UUID uuid = UUID.fromString(value);

        assertTrue(repository.delete(uuid));
    }

    @Test
    void findAll() {
        AnswerRepository repository = new AnswerRepository(entityManager);
        assertEquals(31, repository.findAll().size());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "eb1167b3-67a9-4378-bc65-c1e582e2e662",
            "ba26d851-35e8-479a-baaf-0e891fb797fa",
            "e786ab37-5bca-47be-8298-17c53308fb2e"})
    void incrementCount(String value) {
        AnswerRepository repository = new AnswerRepository(entityManager);
        UUID uuid = UUID.fromString(value);
        int count1 = repository.find(uuid).get().getCount();

        repository.incrementCount(uuid);

        int count2 = repository.find(uuid).get().getCount();
        assertEquals(count1 + 1, count2);
    }
}