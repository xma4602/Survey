package com.xma.model.generators;

import com.xma.model.Answer;
import com.xma.model.Question;
import com.xma.model.QuestionType;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionGenerator extends Generator<Question> {

    private static QuestionType[] types = QuestionType.values();
    private AnswerGenerator answerGenerator = new AnswerGenerator();

    public Question generate(boolean fill) {
        List<Answer> answers = new ArrayList<>();
        if (fill) {
            int len = (int) (Math.random() * LENGTH);
            answers = answerGenerator.generateList(len, true);
        }
        return new Question(UUID.randomUUID(),
                "text" + count++,
                types[(int) (Math.random() * types.length)],
                answers
        );
    }

}
