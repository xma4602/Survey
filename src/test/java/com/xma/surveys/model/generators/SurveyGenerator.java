package com.xma.surveys.model.generators;


import com.xma.surveys.model.Question;
import com.xma.surveys.model.Survey;

import java.util.ArrayList;
import java.util.List;

public class SurveyGenerator extends Generator<Survey> {

    final QuestionGenerator questionGenerator = new QuestionGenerator();

    @Override
    public Survey generate(boolean fill) {
        List<Question> questions = new ArrayList<>();
        if (fill) {
            int len = generateLength();
            questions = questionGenerator.generateList(len, true);
        }
        return new Survey("text" + ++count, questions);
    }

}
