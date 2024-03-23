package com.xma.surveys.repositories;

import com.xma.surveys.entities.Answer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, UUID> {
    @Query("from Answer a order by a.questionId, a.index")
    List<Answer> findAll();
    List<Answer> findByQuestionId(UUID questionId);

    @Transactional
    @Modifying
    @Query("update Answer asw set asw.count = asw.count + 1 where asw.answerId in (:ids)")
    void incrementCounts(Collection<UUID> ids);
}
