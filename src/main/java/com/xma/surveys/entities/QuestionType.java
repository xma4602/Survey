package com.xma.surveys.entities;

public enum QuestionType {
    SINGLE, MULTI;

    public boolean isMultivariate(){
        return this == MULTI;
    }
}
