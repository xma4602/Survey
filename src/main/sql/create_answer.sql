CREATE TABLE "answers"
(
    answer_id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    question_id UUID             DEFAULT NULL,
    index       INT              DEFAULT NULL,
    count       INT              DEFAULT 0,
    text        TEXT NOT NULL,
    FOREIGN KEY (question_id) REFERENCES "questions" (question_id),
    UNIQUE (answer_id, question_id, index)
);