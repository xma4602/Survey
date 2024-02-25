package com.xma.surveys.repositories;

import com.xma.surveys.SqlManager;
import com.xma.surveys.entities.SurveyEntity;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class SurveyRepositoryTest {
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
            "e3e70682-c209-4cac-a29f-6fbed82c07cd",
            "176ea1b1-6426-4cd5-9ea4-5cd69371a71f",
            "d29dc5df-cf1d-4110-8cc3-6d8c77863fe5"})
    void find(String value) {
        SurveyRepository repository = new SurveyRepository(entityManager);
        UUID uuid = UUID.fromString(value);

        assertTrue(repository.find(uuid).isPresent());
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void save(int value) {
        SurveyRepository repository = new SurveyRepository(entityManager);
        SurveyEntity surveyEntity = new SurveyEntity();
        surveyEntity.setTitle("text" + value);

        surveyEntity = repository.save(surveyEntity);

        Optional<SurveyEntity> optional = repository.find(surveyEntity.getSurveyId());
        assertTrue(optional.isPresent());
        assertEquals(surveyEntity, optional.get());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "e3e70682-c209-4cac-a29f-6fbed82c07cd",
            "176ea1b1-6426-4cd5-9ea4-5cd69371a71f",
            "d29dc5df-cf1d-4110-8cc3-6d8c77863fe5"})
    void update(String value) {
        SurveyRepository repository = new SurveyRepository(entityManager);
        UUID uuid = UUID.fromString(value);
        SurveyEntity surveyEntity1 = repository.find(uuid).get();

        surveyEntity1.setTitle("text");
        surveyEntity1 = repository.update(surveyEntity1);
        SurveyEntity surveyEntity2 = repository.find(uuid).get();

        assertEquals(surveyEntity2, surveyEntity1);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "e3e70682-c209-4cac-a29f-6fbed82c07cd",
            "176ea1b1-6426-4cd5-9ea4-5cd69371a71f",
            "d29dc5df-cf1d-4110-8cc3-6d8c77863fe5"})
    void delete(String value) {
        SurveyRepository repository = new SurveyRepository(entityManager);
        UUID uuid = UUID.fromString(value);

        assertTrue(repository.delete(uuid));
    }

    @Test
    void findAll() {
        SurveyRepository repository = new SurveyRepository(entityManager);
        assertEquals(3, repository.findAll().size());
    }
}