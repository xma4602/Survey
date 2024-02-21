SET search_path TO test;

CREATE TABLE "questions"
(
    question_id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    survey_id   UUID             DEFAULT NULL,
    index       INT              DEFAULT NULL,
    topic       TEXT NOT NULL,
    status      TEXT NOT NULL    DEFAULT 'EDIT_ONLY',
    type        TEXT NOT NULL    DEFAULT 'SINGLE',
    FOREIGN KEY (survey_id) REFERENCES "surveys" (survey_id)
);


INSERT INTO "questions" (question_id, survey_id, index, topic)
VALUES ('f728b4fa-4248-4e3a-8a5d-2f346baa9455', 'e3e70682-c209-4cac-a29f-6fbed82c07cd', '0', 'Какой ваш любимый цвет?'),
       ('eb1167b3-67a9-4378-bc65-c1e582e2e662', 'e3e70682-c209-4cac-a29f-6fbed82c07cd', '1', 'Какой жанр фильмов вы предпочитаете?'),

       ('5a921187-19c7-4df4-8f4f-f31e78de5857', '5487ce1e-af19-422a-99b8-a714e61a441c', '0', 'Какой тип музыки вы предпочитаете на вечеринках?'),
       ('a3f2c9bf-9c63-46b9-90f2-44556f25e2a2', '5487ce1e-af19-422a-99b8-a714e61a441c', '1', 'Какой инструмент вы бы хотели научиться играть?'),
       ('8d723104-f773-43c1-b458-a748e9bb17bc', '5487ce1e-af19-422a-99b8-a714e61a441c', '2', 'Какое блюдо вы предпочитаете на обед?'),

       ('38c1962e-9148-424f-aac1-c14f30e9c5cc', '101fbccc-ded7-43e8-b421-eaeb534097ca', '0', 'Какое время года вам нравится больше всего?'),
       ('247a8333-f7b0-47d2-8da8-056c3d15eef7', '101fbccc-ded7-43e8-b421-eaeb534097ca', '1', 'Какую книгу вы прочитали последней?');