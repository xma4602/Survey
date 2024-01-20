package com.xma.model;

public enum QuestionStatus {
    EDIT_ONLY, ANSWERS_ONLY, ARCHIVED;

    public boolean isEditable() {
        return this == EDIT_ONLY;
    }

    public boolean isAnswered() {
        return this == ANSWERS_ONLY;
    }

    public boolean isVisible() {
        return this == ANSWERS_ONLY;
    }
}
