package com.xma.surveys;

import com.xma.surveys.entities.AnswerEntity;
import com.xma.surveys.entities.QuestionEntity;
import com.xma.surveys.entities.SurveyEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SqlManager {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static void openSession() {
        entityManagerFactory = new Configuration()
                .configure("hibernate.test.cfg.xml")
                .addAnnotatedClass(AnswerEntity.class)
                .addAnnotatedClass(QuestionEntity.class)
                .addAnnotatedClass(SurveyEntity.class)
                .buildSessionFactory();
        SqlManager.run("create.sql");
    }

    public static EntityManager insertData() {
        SqlManager.run("insert.sql");
        entityManager = entityManagerFactory.createEntityManager();
        return entityManager;
    }

    public static void deleteData() {
        SqlManager.run("delete.sql");
        if (entityManager != null) {
            entityManager.close();
        }
    }

    public static void closeSession() {
        SqlManager.run("drop.sql");
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }

    @Transactional
    public static void run(String filePath) {
        try (EntityManager entityManager = entityManagerFactory.createEntityManager()) {
            String sqlScript = new String(Files.readAllBytes(Path.of("src", "test", "resources", "sql", filePath)));
            Query query = entityManager.createNativeQuery(sqlScript);
            entityManager.getTransaction().begin();
            query.executeUpdate();
            entityManager.getTransaction().commit();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}