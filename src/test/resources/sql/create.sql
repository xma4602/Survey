SET search_path TO test, public;

CREATE TABLE "surveys"
(
    survey_id UUID PRIMARY KEY,
    title     TEXT
);

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

CREATE TABLE "answers"
(
    answer_id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    question_id UUID             DEFAULT NULL,
    index       INT              DEFAULT NULL,
    count       INT              DEFAULT 0,
    text        TEXT NOT NULL,
    FOREIGN KEY (question_id) REFERENCES "questions" (question_id) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (answer_id, question_id, index)
);
