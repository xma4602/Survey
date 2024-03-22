package com.xma.surveys.repositories;

import com.xma.surveys.entities.Survey;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SurveyRepository extends CrudRepository<Survey, UUID> {

    List<Survey> findAll();
}
