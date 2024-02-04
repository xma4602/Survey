CREATE TABLE "answers"
(
    question_id UUID NOT NULL,
    index       INT  NOT NULL,
    text        TEXT NOT NULL,
    FOREIGN KEY (question_id)
        REFERENCES "questions" (question_id)
        ON DELETE CASCADE ON UPDATE CASCADE
)
