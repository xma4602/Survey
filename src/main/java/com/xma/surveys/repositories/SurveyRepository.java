package com.xma.surveys.repositories;

import com.xma.surveys.entities.Survey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SurveyRepository extends CrudRepository<Survey, UUID> {

    @Query("from Survey s order by s.title")
    List<Survey> findAll();
}
