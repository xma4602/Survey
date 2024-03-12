package com.xma.surveys.generators;

import com.xma.surveys.entities.Answer;
import com.xma.surveys.entities.Question;
import com.xma.surveys.entities.QuestionType;

import java.util.ArrayList;
import java.util.List;

public class QuestionGenerator extends Generator<Question> {

    private static final QuestionType[] types = QuestionType.values();
    private final AnswerGenerator answerGenerator = new AnswerGenerator();

    public Question generate(boolean fill) {
        List<Answer> answers = new ArrayList<>();
        if (fill) {
            int len = generateLength();
            answers = answerGenerator.generateList(len, true);
        }
        Question question = new Question();
        question.setTopic("text" + count++);
        question.setType(types[random.nextInt(types.length)]);
        question.setAnswers(answers);
        return question;
    }

}
