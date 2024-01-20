package com.xma.model;

public enum QuestionType {
    SINGLE, MULTI;

    public boolean isMultivariate(){
        return this == MULTI;
    }
}
