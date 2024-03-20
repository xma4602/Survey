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
        if (answerRepository.existQuestion(answer.getQuestionId())) {
            return answerRepository.save(answer);
        } else throw new IllegalArgumentException("Not exists question with id=" + answer.getQuestionId());
    }

    public Optional<Answer> findById(UUID id) {
        return answerRepository.find(id);
    }

    public List<Answer> findByQuestionId(UUID questionId) {
        return answerRepository.findByQuestionId(questionId);
    }

    public List<Answer> getAll() {
        return answerRepository.findAll();
    }

    public Answer update(Answer answer) {
        UUID id = answer.getAnswerId();
        answerRepository.find(id).orElseThrow(
                () -> new NoSuchElementException("No such question with id=" + id)
        );
        return answerRepository.update(answer);
    }

    public boolean delete(UUID answerId) {
        return answerRepository.delete(answerId);
    }

    public void answer(Collection<UUID> answersIds) {
        List<Answer> answers = new ArrayList<>(answersIds.size());
        for (UUID id : answersIds) {
            answers.add(answerRepository.find(id).orElseThrow(
                    () -> new NoSuchElementException("No such question with id=" + id))
            );
        }
        for (Answer answer : answers) {
            answer.incrementCount();
            answerRepository.update(answer);
        }
    }

}
