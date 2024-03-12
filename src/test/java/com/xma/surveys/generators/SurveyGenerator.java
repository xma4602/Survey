package com.xma.surveys.generators;


import com.xma.surveys.entities.Question;
import com.xma.surveys.entities.Survey;

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
        Survey survey = new Survey();
        survey.setTitle("text" + ++count);
        survey.setQuestions(questions);
        return survey;
    }

}
