package com.xma.model.generators;


import com.xma.model.Question;
import com.xma.model.Survey;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SurveyGenerator extends Generator<Survey> {

    QuestionGenerator questionGenerator = new QuestionGenerator();

    @Override
    public Survey generate(boolean fill) {
        List<Question> questions = new ArrayList<>();
        if (fill) {
            int len = (int) (Math.random() * LENGTH);
            questions = questionGenerator.generateList(len, true);

        }
        return new Survey(
                UUID.randomUUID(),
                questions
        );
    }

}
