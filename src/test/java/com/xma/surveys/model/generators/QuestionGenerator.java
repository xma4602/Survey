package com.xma.surveys.model.generators;

import com.xma.surveys.model.Answer;
import com.xma.surveys.model.Question;
import com.xma.surveys.model.QuestionType;

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
        return new Question(
                "text" + count++,
                types[random.nextInt(types.length)],
                answers
        );
    }

}
