CREATE TABLE "questions"
(
    question_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    survey_id   UUID             DEFAULT NULL,
    index       INT              DEFAULT NULL,
    topic       TEXT NOT NULL,
    status      TEXT NOT NULL    DEFAULT 'EDIT_ONLY',
    type        TEXT NOT NULL    DEFAULT 'SINGLE',
    FOREIGN KEY (survey_id) REFERENCES "surveys" (survey_id) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (survey_id, question_id, index)
);