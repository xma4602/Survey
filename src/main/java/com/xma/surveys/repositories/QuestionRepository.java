package com.xma.surveys.repositories;

import com.xma.surveys.entities.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QuestionRepository extends CrudRepository<Question, UUID> {

    @Query("from Question q order by q.surveyId, q.index")
    List<Question> findAll();
    List<Question> findBySurveyId(UUID surveyId);
}
