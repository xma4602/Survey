SET search_path TO test;

CREATE TABLE "answers"
(
    answer_id   UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    question_id UUID             DEFAULT NULL,
    index       INT              DEFAULT NULL,
    text        TEXT NOT NULL,
    FOREIGN KEY (question_id) REFERENCES "questions" (question_id),
    UNIQUE (answer_id, question_id, index)
);

INSERT INTO "answers" (answer_id, question_id, index, text)
VALUES ('f728b4fa-4248-4e3a-8a5d-2f346baa9455', 0, 'Красный'),
       ('f728b4fa-4248-4e3a-8a5d-2f346baa9455', 1, 'Зеленый'),
       ('f728b4fa-4248-4e3a-8a5d-2f346baa9455', 2, 'Синий'),
       ('f728b4fa-4248-4e3a-8a5d-2f346baa9455', 3, 'Желтый'),
       ('f728b4fa-4248-4e3a-8a5d-2f346baa9455', 4, 'Фиолетовый'),

       ('eb1167b3-67a9-4378-bc65-c1e582e2e662', 0, 'Комедия'),
       ('eb1167b3-67a9-4378-bc65-c1e582e2e662', 1, 'Драма'),
       ('eb1167b3-67a9-4378-bc65-c1e582e2e662', 2, 'Боевик'),
       ('eb1167b3-67a9-4378-bc65-c1e582e2e662', 3, 'Ужасы'),
       ('eb1167b3-67a9-4378-bc65-c1e582e2e662', 4, 'Научная фантастика'),

       ('5a921187-19c7-4df4-8f4f-f31e78de5857', 0, 'Хаус'),
       ('5a921187-19c7-4df4-8f4f-f31e78de5857', 1, 'Техно'),
       ('5a921187-19c7-4df4-8f4f-f31e78de5857', 2, 'Диско'),
       ('5a921187-19c7-4df4-8f4f-f31e78de5857', 3, 'Рок'),
       ('5a921187-19c7-4df4-8f4f-f31e78de5857', 4, 'Поп'),

       ('a3f2c9bf-9c63-46b9-90f2-44556f25e2a2', 0, 'Гитара'),
       ('a3f2c9bf-9c63-46b9-90f2-44556f25e2a2', 1, 'Барабаны'),
       ('a3f2c9bf-9c63-46b9-90f2-44556f25e2a2', 2, 'Труба'),
       ('a3f2c9bf-9c63-46b9-90f2-44556f25e2a2', 3, 'Скрипка'),
       ('a3f2c9bf-9c63-46b9-90f2-44556f25e2a2', 4, 'Фортепиано'),

       ('8d723104-f773-43c1-b458-a748e9bb17bc', 0, 'Суп'),
       ('8d723104-f773-43c1-b458-a748e9bb17bc', 1, 'Салат'),
       ('8d723104-f773-43c1-b458-a748e9bb17bc', 2, 'Второе блюдо'),

       ('38c1962e-9148-424f-aac1-c14f30e9c5cc', 0, 'Зима'),
       ('38c1962e-9148-424f-aac1-c14f30e9c5cc', 1, 'Весна'),
       ('38c1962e-9148-424f-aac1-c14f30e9c5cc', 2, 'Лето'),
       ('38c1962e-9148-424f-aac1-c14f30e9c5cc', 3, 'Осень'),

       ('247a8333-f7b0-47d2-8da8-056c3d15eef7', 0, 'Фантастику'),
       ('247a8333-f7b0-47d2-8da8-056c3d15eef7', 1, 'Детектив'),
       ('247a8333-f7b0-47d2-8da8-056c3d15eef7', 2, 'Роман'),
       ('247a8333-f7b0-47d2-8da8-056c3d15eef7', 3, 'Исторический роман');