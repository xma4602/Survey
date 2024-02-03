package com.xma.surveys.model;

public enum QuestionType {
    SINGLE, MULTI;

    public boolean isMultivariate(){
        return this == MULTI;
    }
}
