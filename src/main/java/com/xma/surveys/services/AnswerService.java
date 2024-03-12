package com.xma.surveys.services;

import com.xma.surveys.entities.Answer;
import com.xma.surveys.repositories.AnswerRepository;
import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class AnswerService {
    private final AnswerRepository answerRepository;

    public Answer create(String text) {
        Answer answer = new Answer();
        answer.setText(text);
        return answerRepository.save(answer);
    }

    public Optional<Answer> findById(UUID id) {
        return answerRepository.find(id);
    }

    public List<Answer> findBySurveyId(UUID id) {
        return answerRepository.findByQuestionId(id);
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
