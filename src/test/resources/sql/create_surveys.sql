SET search_path TO test;

CREATE TABLE "surveys"
(
    survey_id UUID PRIMARY KEY,
    title     TEXT
);

INSERT INTO "surveys" (survey_id, title)
VALUES ('e3e70682-c209-4cac-a29f-6fbed82c07cd', 'Опрос 1'),
       ('5487ce1e-af19-422a-99b8-a714e61a441c', 'Опрос 2'),
       ('101fbccc-ded7-43e8-b421-eaeb534097ca', 'Опрос 3');