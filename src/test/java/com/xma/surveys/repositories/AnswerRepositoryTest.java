package com.xma.surveys.repositories;

import com.xma.surveys.SqlManager;
import com.xma.surveys.entities.AnswerEntity;
import org.junit.jupiter.api.*;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class AnswerRepositoryTest {
    static AnswerRepository repository;

    @BeforeAll
    static void setup() {
        repository = new AnswerRepository(SqlManager.openSession());
    }

    @BeforeEach
    void setUp() {
        SqlManager.run("create_answers.sql");
    }

    @AfterEach
    void tearDown() {
        SqlManager.run("delete_answers.sql");
    }

    @AfterAll
    static void tear() {
        SqlManager.closeSession();
    }

    @Test
    void find() {
        for (int i = 0; i < 10; i++) {
            String text = "text" + i;
            UUID questionId = new UUID(i, i);

            AnswerEntity answer1 = new AnswerEntity();
            answer1.setText(text);
            answer1.setCount(i);
            answer1.setIndex(i);
            answer1.setQuestionId(questionId);
            answer1 = repository.save(answer1);
            Optional<AnswerEntity> answer2 = repository.find(questionId, i);

            assertTrue(answer2.isPresent());
            assertEquals(answer1, answer2.get());
            assertTrue(repository.delete(questionId));
        }
    }

    @Test
    void incrementAnswerCount() {
        for (int i = 0; i < 10; i++) {
            String text = "text" + i;
            UUID questionId = new UUID(i, i);

            AnswerEntity answer1 = new AnswerEntity();
            answer1.setText(text);
            answer1.setCount(i);
            answer1.setIndex(i);
            answer1.setQuestionId(questionId);
            answer1 = repository.save(answer1);

            repository.incrementCount(answer1.getQuestionId(), answer1.getIndex());

            Optional<AnswerEntity> answer2 = repository.find(questionId, i);

            assertTrue(answer2.isPresent());
            assertEquals(answer1.getCount() + 1, answer2.get().getCount());
            assertTrue(repository.delete(questionId));
        }
    }

    @Test
    void save() {
        for (int i = 0; i < 10; i++) {
            String text = "text" + i;
            UUID questionId = new UUID(i, i);

            AnswerEntity answer1 = new AnswerEntity();
            answer1.setText(text);
            answer1.setCount(i);
            answer1.setIndex(i);
            answer1.setQuestionId(questionId);
            answer1 = repository.save(answer1);
            Optional<AnswerEntity> answer2 = repository.find(questionId, i);

            assertTrue(answer2.isPresent());
            assertEquals(answer1, answer2.get());
            assertTrue(repository.delete(questionId));
        }
    }

    @Test
    void update() {
        for (int i = 0; i < 10; i++) {
            String text = "text" + i;
            UUID questionId = new UUID(i, i);

            AnswerEntity answer1 = new AnswerEntity();
            answer1.setText(text);
            answer1.setCount(i);
            answer1.setIndex(i);
            answer1.setQuestionId(questionId);
            answer1 = repository.save(answer1);

            answer1.setText("my text");
            answer1 = repository.update(answer1);

            Optional<AnswerEntity> answer2 = repository.find(questionId, i);

            assertTrue(answer2.isPresent());
            assertEquals(answer1.getText(), answer2.get().getText());
            assertTrue(repository.delete(questionId));
        }
    }

    @Test
    void delete() {
        for (int i = 0; i < 10; i++) {
            String text = "text" + i;
            UUID questionId = new UUID(i, i);

            AnswerEntity answer1 = new AnswerEntity();
            answer1.setText(text);
            answer1.setCount(i);
            answer1.setIndex(i);
            answer1.setQuestionId(questionId);

            assertTrue(repository.delete(questionId));

            Optional<AnswerEntity> answer2 = repository.find(questionId, i);
            assertTrue(answer2.isEmpty());
        }
    }
}