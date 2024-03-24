package com.xma.surveys.services;

import com.xma.surveys.entities.Answer;
import com.xma.surveys.repositories.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Answer create(Answer answer) {
        return answerRepository.save(answer);
    }

    public Optional<Answer> findById(UUID id) {
        return answerRepository.findById(id);
    }

    public List<Answer> findByQuestionId(UUID questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    public Answer update(Answer answer) {
        UUID id = answer.getAnswerId();
        if (!answerRepository.existsById(id)) {
            notExists(id);
        }
        return answerRepository.save(answer);
    }

    public void delete(UUID answerId) {
        answerRepository.deleteById(answerId);
    }

    private static void notExists(UUID id) {
        throw new NoSuchElementException("No such answer with id=" + id);
    }


}
