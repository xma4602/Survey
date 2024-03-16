const questionsData = [
    {
        "question_id": "f728b4fa-4248-4e3a-8a5d-2f346baa9455",
        "survey_id": "e3e70682-c209-4cac-a29f-6fbed82c07cd",
        "topic": "Какой ваш любимый цвет?",
        "index": 0,
        "type": "MULTI",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "fcbd04c3-4021-4ef7-8ca5-a5a19e4d6e3c",
        "survey_id": "e3e70682-c209-4cac-a29f-6fbed82c07cd",
        "topic": "Какой жанр фильмов вы предпочитаете?",
        "index": 1,
        "type": "MULTI",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "a3f2c9bf-9c63-46b9-90f2-44556f25e2a2",
        "survey_id": "e3e70682-c209-4cac-a29f-6fbed82c07cd",
        "topic": "Какой вид спорта вам больше всего нравится?",
        "index": 2,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "a0116be5-ab0c-4681-88f8-e3d0d3290a4c",
        "survey_id": "e3e70682-c209-4cac-a29f-6fbed82c07cd",
        "topic": "Какой супергерой вам больше всего импонирует?",
        "index": 3,
        "type": "SINGLE",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "1759edc3-72ae-4244-8b01-63c1cd9d2b7d",
        "survey_id": "e3e70682-c209-4cac-a29f-6fbed82c07cd",
        "topic": "Какой вкус мороженого вы предпочитаете?",
        "index": 4,
        "type": "SINGLE",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "3405095c-8a50-46c1-ac18-8efbd080e66e",
        "survey_id": "e3e70682-c209-4cac-a29f-6fbed82c07cd",
        "topic": "Какой музыкальный жанр вам больше всего нравится?",
        "index": 5,
        "type": "SINGLE",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "42930b33-a81a-4477-bb36-75b89cdeb3e6",
        "survey_id": "e3e70682-c209-4cac-a29f-6fbed82c07cd",
        "topic": "Какой вид кофе вы предпочитаете?",
        "index": 6,
        "type": "MULTI",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "85940927-468f-453d-864a-7a50b48d73f1",
        "survey_id": "e3e70682-c209-4cac-a29f-6fbed82c07cd",
        "topic": "Какой вид кофе вы предпочитаете?",
        "index": 7,
        "type": "MULTI",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "5306f3f5-1516-4570-9b7c-709acb175a5a",
        "survey_id": "e3e70682-c209-4cac-a29f-6fbed82c07cd",
        "topic": "Какой ваш любимый фильм?",
        "index": 8,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "c87a7463-19c1-4a0d-8feb-d845d0dfae43",
        "survey_id": "e3e70682-c209-4cac-a29f-6fbed82c07cd",
        "topic": "Какой тип книг вы предпочитаете?",
        "index": 9,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "1db53334-fb03-43a1-9576-d4155ec17dbe",
        "survey_id": "176ea1b1-6426-4cd5-9ea4-5cd69371a71f",
        "topic": "Какой тип музыки вы предпочитаете на вечеринках?",
        "index": 0,
        "type": "MULTI",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "11ebcd49-428a-4c22-95fd-b76a19fbeb1d",
        "survey_id": "176ea1b1-6426-4cd5-9ea4-5cd69371a71f",
        "topic": "Какой инструмент вы бы хотели научиться играть?",
        "index": 1,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "e6fd68e8-d69c-41c2-b860-1602bb4a06cb",
        "survey_id": "176ea1b1-6426-4cd5-9ea4-5cd69371a71f",
        "topic": "Какое блюдо вы предпочитаете на обед?",
        "index": 2,
        "type": "SINGLE",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "878b9f6b-57a1-4b71-a975-d279d86dbf11",
        "survey_id": "176ea1b1-6426-4cd5-9ea4-5cd69371a71f",
        "topic": "Какой предмет вам больше всего нравился в школе?",
        "index": 3,
        "type": "SINGLE",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "eae2025e-8233-4e23-9ff3-334b91b15f5d",
        "survey_id": "176ea1b1-6426-4cd5-9ea4-5cd69371a71f",
        "topic": "Какой вид отдыха вы предпочитаете?",
        "index": 4,
        "type": "MULTI",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "bd30291a-55fe-408e-943e-2e04bdd7d19b",
        "survey_id": "176ea1b1-6426-4cd5-9ea4-5cd69371a71f",
        "topic": "Какая погода вам больше нравится?",
        "index": 5,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "e456697c-f268-4baa-971c-702d5bf49c04",
        "survey_id": "176ea1b1-6426-4cd5-9ea4-5cd69371a71f",
        "topic": "Какую музыку вы предпочитаете слушать?",
        "index": 6,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "b2d650af-313b-42b7-9836-31890063e42f",
        "survey_id": "176ea1b1-6426-4cd5-9ea4-5cd69371a71f",
        "topic": "Какой стиль одежды вы предпочитаете?",
        "index": 7,
        "type": "SINGLE",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "df26f517-66fa-4989-8813-5d586a1689ad",
        "survey_id": "176ea1b1-6426-4cd5-9ea4-5cd69371a71f",
        "topic": "Какой жанр книг вам больше всего нравится?",
        "index": 8,
        "type": "MULTI",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "e28bc9ff-870f-484c-b244-f536285e25b4",
        "survey_id": "176ea1b1-6426-4cd5-9ea4-5cd69371a71f",
        "topic": "Как вы обычно проводите выходные?",
        "index": 9,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "6a46721a-cffa-4cdd-b963-a7efe00111e5",
        "survey_id": "d29dc5df-cf1d-4110-8cc3-6d8c77863fe5",
        "topic": "Какое время года вам нравится больше всего?",
        "index": 0,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "03a89879-36a9-4d74-80de-59f550f0fc2b",
        "survey_id": "d29dc5df-cf1d-4110-8cc3-6d8c77863fe5",
        "topic": "Какую книгу вы прочитали последней?",
        "index": 1,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "2ea60b99-fa7f-48bf-b044-284a47acf2f6",
        "survey_id": "d29dc5df-cf1d-4110-8cc3-6d8c77863fe5",
        "topic": "Какое животное является хищником?",
        "index": 2,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "d138d150-8557-416a-a750-2a812227d96d",
        "survey_id": "d29dc5df-cf1d-4110-8cc3-6d8c77863fe5",
        "topic": "Какое из предложенных языков не является славянским?",
        "index": 3,
        "type": "MULTI",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "914591ae-f03d-466a-9dec-c06af24dfdd8",
        "survey_id": "d29dc5df-cf1d-4110-8cc3-6d8c77863fe5",
        "topic": "Кто является автором книги \"Гарри Поттер\"?",
        "index": 4,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "2da44da1-89b5-4368-9f14-c6125f58d5b5",
        "survey_id": "d29dc5df-cf1d-4110-8cc3-6d8c77863fe5",
        "topic": "Какое из чисел является простым?",
        "index": 5,
        "type": "MULTI",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "4505f4f6-0a8c-46c7-8921-5f4f9edb95f2",
        "survey_id": "d29dc5df-cf1d-4110-8cc3-6d8c77863fe5",
        "topic": "Какой город является столицей России?",
        "index": 6,
        "type": "MULTI",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "db66bfda-2df9-4747-8ed1-35530c5a876f",
        "survey_id": "d29dc5df-cf1d-4110-8cc3-6d8c77863fe5",
        "topic": "Какая планета находится ближе всего к Солнцу?",
        "index": 7,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "cff4c56b-f9ea-4c64-8c41-7e7cd741d609",
        "survey_id": "d29dc5df-cf1d-4110-8cc3-6d8c77863fe5",
        "topic": "Какой из перечисленных приборов служит для измерения температуры воздуха?",
        "index": 8,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "ce5dc807-6025-4199-9082-3edaa0722aa0",
        "survey_id": "d29dc5df-cf1d-4110-8cc3-6d8c77863fe5",
        "topic": "Какое из этих слов не является синонимом слова \"умный\"?",
        "index": 9,
        "type": "MULTI",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "b6e355f6-95bb-440d-89cd-4af97d161f29",
        "survey_id": "eb8f2056-72d3-4c5d-8a31-b24384dd6da6",
        "topic": "Какое море омывает побережье России?",
        "index": 0,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "da54f267-dd13-4266-926d-53961058fe8c",
        "survey_id": "eb8f2056-72d3-4c5d-8a31-b24384dd6da6",
        "topic": "Какой из перечисленных инструментов обычно используется для игры на классической гитаре?",
        "index": 1,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "9f982188-3744-4a64-8c24-9558f2ad985f",
        "survey_id": "eb8f2056-72d3-4c5d-8a31-b24384dd6da6",
        "topic": "Какая страна является самой большой по территории?",
        "index": 2,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "ade7cef3-7ed2-4c2f-856f-3d95e0ae1a1b",
        "survey_id": "eb8f2056-72d3-4c5d-8a31-b24384dd6da6",
        "topic": "В каком году произошла Французская революция?",
        "index": 3,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "52631db9-d170-44ce-9179-7350e6256403",
        "survey_id": "eb8f2056-72d3-4c5d-8a31-b24384dd6da6",
        "topic": "Какое из этих животных является насекомым?",
        "index": 4,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "10fc9eee-0a17-47f7-aa5f-24b6de6fec4b",
        "survey_id": "eb8f2056-72d3-4c5d-8a31-b24384dd6da6",
        "topic": "Кто написал произведение \"Война и мир\"?",
        "index": 5,
        "type": "SINGLE",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "089b30a0-809f-4923-87a1-798fe6addd9e",
        "survey_id": "eb8f2056-72d3-4c5d-8a31-b24384dd6da6",
        "topic": "Какое имя является женским?",
        "index": 6,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "d872298c-7b72-490b-b8f8-f071d360da69",
        "survey_id": "eb8f2056-72d3-4c5d-8a31-b24384dd6da6",
        "topic": "Какой город столица Италии?",
        "index": 7,
        "type": "MULTI",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "4312ece2-dc21-41e1-be56-ac3d10cc8711",
        "survey_id": "eb8f2056-72d3-4c5d-8a31-b24384dd6da6",
        "topic": "Какой возраст называется \"совершеннолетием\"?",
        "index": 8,
        "type": "MULTI",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "2412579d-6af9-44e0-bb38-785b0932f5b6",
        "survey_id": "eb8f2056-72d3-4c5d-8a31-b24384dd6da6",
        "topic": "Кто из перечисленных является изобретателем лампы?",
        "index": 9,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "21f8c156-9e0d-445b-992a-34a1084fa819",
        "survey_id": "052daad3-26c0-4984-8734-bb05788c31f6",
        "topic": "Какое из этих растений является ягодой?",
        "index": 0,
        "type": "SINGLE",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "a6855857-567e-4862-af15-1673a1df3da7",
        "survey_id": "052daad3-26c0-4984-8734-bb05788c31f6",
        "topic": "Какое из этих слов является глаголом?",
        "index": 1,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "dc45488d-84dd-49b9-9f1e-0ee9cf6c9992",
        "survey_id": "052daad3-26c0-4984-8734-bb05788c31f6",
        "topic": "Какая планета солнечной системы самая большая?",
        "index": 2,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "06d2ed7c-e6ac-4d8a-8160-ff927c7550f2",
        "survey_id": "052daad3-26c0-4984-8734-bb05788c31f6",
        "topic": "Какое из этих напитков не содержит алкоголь?",
        "index": 3,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "254bf7ae-1d0a-4994-b20b-575d4e28e674",
        "survey_id": "052daad3-26c0-4984-8734-bb05788c31f6",
        "topic": "Кто является автором произведения \"Гарри Поттер\"?",
        "index": 4,
        "type": "MULTI",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "7f22cd12-07b6-408e-aac1-ca75afb918c8",
        "survey_id": "052daad3-26c0-4984-8734-bb05788c31f6",
        "topic": "В каком году состоялась первая мировая война?",
        "index": 5,
        "type": "MULTI",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "98ba0f0e-120d-4126-9da5-3b38d1aa6c5e",
        "survey_id": "052daad3-26c0-4984-8734-bb05788c31f6",
        "topic": "Какой город является столицей России?",
        "index": 6,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "bf7b68ae-1f89-41b6-a6a1-a40bf031f4b9",
        "survey_id": "052daad3-26c0-4984-8734-bb05788c31f6",
        "topic": "Какая планета является самой большой в Солнечной системе?",
        "index": 7,
        "type": "MULTI",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "743c7e9d-2fde-4035-a452-bc39dbf2eed1",
        "survey_id": "052daad3-26c0-4984-8734-bb05788c31f6",
        "topic": "Какой цвет имеет переводчик AI в интерфейсе OpenAI?",
        "index": 8,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "55c36c3d-5cbb-4080-b547-5c5ef76dce6e",
        "survey_id": "052daad3-26c0-4984-8734-bb05788c31f6",
        "topic": "Какая операционная система разработана компанией Apple?",
        "index": 9,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "88819f42-1a42-4629-94af-e646fe3216bd",
        "survey_id": "97d01e70-2f1d-4bef-93b5-3b92a2cb5f38",
        "topic": "Как называется наука, изучающая звезды, планеты и галактики?",
        "index": 0,
        "type": "MULTI",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "82221345-50de-4292-b90a-9016cdec85da",
        "survey_id": "97d01e70-2f1d-4bef-93b5-3b92a2cb5f38",
        "topic": "Кто создал первый электрический телефон?",
        "index": 1,
        "type": "SINGLE",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "99e8c828-21da-432c-9c68-d4fd0bd7696f",
        "survey_id": "97d01e70-2f1d-4bef-93b5-3b92a2cb5f38",
        "topic": "Каким образом отображаются числа в римской системе исчисления?",
        "index": 2,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "ed28508d-bdaa-4bfa-aab0-15638cffbbb1",
        "survey_id": "97d01e70-2f1d-4bef-93b5-3b92a2cb5f38",
        "topic": "Какой газ является основным в атмосфере Земли?",
        "index": 3,
        "type": "MULTI",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "a3155940-5e87-405a-a1fd-cdf171df24d9",
        "survey_id": "97d01e70-2f1d-4bef-93b5-3b92a2cb5f38",
        "topic": "Кто написал произведение \"Война и мир\"?",
        "index": 4,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "70f8dd99-5217-4eb7-a6b9-20daba6a098f",
        "survey_id": "97d01e70-2f1d-4bef-93b5-3b92a2cb5f38",
        "topic": "Какое животное является символом бренда Ferrari?",
        "index": 5,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "1cb66f67-46e7-45ad-9bce-35d8cbe88a3f",
        "survey_id": "97d01e70-2f1d-4bef-93b5-3b92a2cb5f38",
        "topic": "Какая из следующих планет находится ближе к Солнцу?",
        "index": 6,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "f5a3e893-3f7a-4748-acbb-93c26e84f8ea",
        "survey_id": "97d01e70-2f1d-4bef-93b5-3b92a2cb5f38",
        "topic": "Кто написал роман \"Война и мир\"?",
        "index": 7,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "d4864482-0078-4b12-8b73-50d13421beaf",
        "survey_id": "97d01e70-2f1d-4bef-93b5-3b92a2cb5f38",
        "topic": "Какое животное является символом Австралии?",
        "index": 8,
        "type": "SINGLE",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "6c98f73d-27fe-4b4b-9bf1-f1d18b92c247",
        "survey_id": "97d01e70-2f1d-4bef-93b5-3b92a2cb5f38",
        "topic": "Сколько игроков составляют футбольную команду?",
        "index": 9,
        "type": "MULTI",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "ab2212c9-e23b-480e-8523-dbbb1eeed219",
        "survey_id": "0588d91d-fbe8-4a8e-a1cf-0d1d47b3df41",
        "topic": "Какая из следующих стран является самой большой по территории?",
        "index": 0,
        "type": "MULTI",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "df0d6301-488c-4c4e-9a94-14b840aaec7a",
        "survey_id": "0588d91d-fbe8-4a8e-a1cf-0d1d47b3df41",
        "topic": "Какое самое глубокое озеро мира?",
        "index": 1,
        "type": "MULTI",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "dc0520a4-87ba-4b90-9e41-5c4e57030ede",
        "survey_id": "0588d91d-fbe8-4a8e-a1cf-0d1d47b3df41",
        "topic": "Какой металл является самым редким на Земле?",
        "index": 2,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "35d4cd35-a08c-4a00-85e7-425092f078b8",
        "survey_id": "0588d91d-fbe8-4a8e-a1cf-0d1d47b3df41",
        "topic": "Кто из этих художников является автором \"Мона Лизы\"?",
        "index": 3,
        "type": "SINGLE",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "9177b6d4-5f2e-4167-b13e-ceb14ad12b4c",
        "survey_id": "0588d91d-fbe8-4a8e-a1cf-0d1d47b3df41",
        "topic": "Сколько штатов в США?",
        "index": 4,
        "type": "MULTI",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "a1d20ebc-5aa3-492a-8c88-b9d8ab12fb53",
        "survey_id": "0588d91d-fbe8-4a8e-a1cf-0d1d47b3df41",
        "topic": "Какой газ является основным компонентом воздуха?",
        "index": 5,
        "type": "MULTI",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "248f4a25-4cd8-4f24-b1a8-c9c60f6ab75b",
        "survey_id": "0588d91d-fbe8-4a8e-a1cf-0d1d47b3df41",
        "topic": "Какая страна не входит в Европейскую Юнию?",
        "index": 6,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "5c7cbc62-01a4-47a1-a526-4961ab4414ae",
        "survey_id": "0588d91d-fbe8-4a8e-a1cf-0d1d47b3df41",
        "topic": "Какой национальности не существует?",
        "index": 7,
        "type": "MULTI",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "a5e3a719-6bf5-4dfd-94a8-762b2b12c92c",
        "survey_id": "0588d91d-fbe8-4a8e-a1cf-0d1d47b3df41",
        "topic": "Какая планета является самой близкой к Солнцу?",
        "index": 8,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "707421d4-3239-404b-8bab-5b51385c5fdc",
        "survey_id": "0588d91d-fbe8-4a8e-a1cf-0d1d47b3df41",
        "topic": "Кто написал роман \"Война и мир\"?",
        "index": 9,
        "type": "SINGLE",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "e2091f49-e0a1-4d2b-857c-799848d002be",
        "survey_id": "c061c99b-831a-452a-9e6c-8762b475e15e",
        "topic": "Какой вид спорта не включает мяч?",
        "index": 0,
        "type": "MULTI",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "0d742256-0b36-43cd-8acf-eba4441030ae",
        "survey_id": "c061c99b-831a-452a-9e6c-8762b475e15e",
        "topic": "Какой период времени называется \"Средневековье\"?",
        "index": 1,
        "type": "SINGLE",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "51abf5e5-cb77-4e24-95eb-3a3de2014a45",
        "survey_id": "c061c99b-831a-452a-9e6c-8762b475e15e",
        "topic": "Какой океан самый большой?",
        "index": 2,
        "type": "MULTI",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "6279d944-dc28-47c6-8453-98134fee7144",
        "survey_id": "c061c99b-831a-452a-9e6c-8762b475e15e",
        "topic": "Какая из этих стран не имеет свойственных для нефти ресурсов?",
        "index": 3,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "3dbba3af-14db-4d22-95ea-e9d21e3d59d0",
        "survey_id": "c061c99b-831a-452a-9e6c-8762b475e15e",
        "topic": "Какого цвета яблоки?",
        "index": 4,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "476c4878-deff-4d7a-9728-525620ca35ab",
        "survey_id": "c061c99b-831a-452a-9e6c-8762b475e15e",
        "topic": "Какая страна самая населенная в мире?",
        "index": 5,
        "type": "MULTI",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "9d683a9e-1e65-4ac1-843d-2c473b56735e",
        "survey_id": "c061c99b-831a-452a-9e6c-8762b475e15e",
        "topic": "Какое из этих животных является насекомым?",
        "index": 6,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "ff4533fe-bc6f-46ac-a139-d15d48d8b9ef",
        "survey_id": "c061c99b-831a-452a-9e6c-8762b475e15e",
        "topic": "Какой из следующих языков является наиболее распространенным?",
        "index": 7,
        "type": "SINGLE",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "45c190a8-a52b-40ce-a27b-585f1f2de275",
        "survey_id": "c061c99b-831a-452a-9e6c-8762b475e15e",
        "topic": "Какая планета является самой большой в Солнечной системе?",
        "index": 8,
        "type": "SINGLE",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "483c1a4e-a91d-42cd-8bf0-6f64b4178592",
        "survey_id": "c061c99b-831a-452a-9e6c-8762b475e15e",
        "topic": "Кто написал роман \"Война и мир\"?",
        "index": 9,
        "type": "MULTI",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "9501e917-496d-427b-baf2-f402a0e624f1",
        "survey_id": "5f89d54d-3bcd-4aec-93ca-8c05acb5959f",
        "topic": "Какой из этих красителей является основным цветом радуги?",
        "index": 0,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "cfdef303-0679-4b47-a283-453695decd6e",
        "survey_id": "5f89d54d-3bcd-4aec-93ca-8c05acb5959f",
        "topic": "Какая страна является родиной танго?",
        "index": 1,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "34dd7624-c3d9-43b0-a2b5-6be11392a255",
        "survey_id": "5f89d54d-3bcd-4aec-93ca-8c05acb5959f",
        "topic": "Какой самый высокий горный хребет в мире?",
        "index": 2,
        "type": "MULTI",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "21d32e24-bb89-4746-bc3d-a91136a52df9",
        "survey_id": "5f89d54d-3bcd-4aec-93ca-8c05acb5959f",
        "topic": "Какой из этих фильмов является самым кассовым в истории кинематографа?",
        "index": 3,
        "type": "MULTI",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "eaf94919-e69b-4971-bf9b-76efa1057256",
        "survey_id": "5f89d54d-3bcd-4aec-93ca-8c05acb5959f",
        "topic": "Какая химическая формула обозначает воду?",
        "index": 4,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "f568c9c6-033c-4469-8f06-ea1874a26444",
        "survey_id": "5f89d54d-3bcd-4aec-93ca-8c05acb5959f",
        "topic": "Кто из этих художников является автором картины \"Мона Лиза\"?",
        "index": 5,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "8bc88bcf-7b1d-4049-bcc4-616df40d2a37",
        "survey_id": "5f89d54d-3bcd-4aec-93ca-8c05acb5959f",
        "topic": "Какого цвета национальный флаг Российской Федерации?",
        "index": 6,
        "type": "MULTI",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "5100f186-d25a-4049-b0e9-be17b4134208",
        "survey_id": "5f89d54d-3bcd-4aec-93ca-8c05acb5959f",
        "topic": "Сколько предметов было изобретено Томасом Эдисоном?",
        "index": 7,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "3fe65f90-f631-481e-bf40-1e04b9f94ec6",
        "survey_id": "5f89d54d-3bcd-4aec-93ca-8c05acb5959f",
        "topic": "Какой из этих городов является столицей Италии?",
        "index": 8,
        "type": "MULTI",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "298e9a79-abec-4c0b-981b-a30742965873",
        "survey_id": "5f89d54d-3bcd-4aec-93ca-8c05acb5959f",
        "topic": "Сколько игральных карт содержится в стандартной колоде?",
        "index": 9,
        "type": "MULTI",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "3dd0c866-0649-4ef5-8fb2-de1ea0b976c2",
        "survey_id": "7db931ce-05a0-4421-9a79-b46a26b61b06",
        "topic": "Кто является автором пьесы \"Гамлет\"?",
        "index": 0,
        "type": "SINGLE",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "a9fa4ea6-f8db-4a4d-984d-3c020ff9d318",
        "survey_id": "7db931ce-05a0-4421-9a79-b46a26b61b06",
        "topic": "Какое из этих животных может летать?",
        "index": 1,
        "type": "MULTI",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "a629d332-2d4b-45a4-8fdc-13515ba830dc",
        "survey_id": "7db931ce-05a0-4421-9a79-b46a26b61b06",
        "topic": "Какой газ является наиболее распространенным в атмосфере Земли?",
        "index": 2,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "88a0edce-4384-460c-a413-961f68c6dd5e",
        "survey_id": "7db931ce-05a0-4421-9a79-b46a26b61b06",
        "topic": "Кто является автором скульптуры \"Давид\"?",
        "index": 3,
        "type": "MULTI",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "1f626a97-ebe7-4f91-9092-0b690abc847e",
        "survey_id": "7db931ce-05a0-4421-9a79-b46a26b61b06",
        "topic": "Какой спортсмен установил мировой рекорд на длиннейшем прыжке в длину?",
        "index": 4,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "d70da545-8391-47a6-a977-4b71de6d2d7e",
        "survey_id": "7db931ce-05a0-4421-9a79-b46a26b61b06",
        "topic": "Какой из перечисленных продуктов является фруктом?",
        "index": 5,
        "type": "SINGLE",
        "status": "ANSWERS_ONLY",
        "answers": 10
    },
    {
        "question_id": "e575ae93-ce74-4e8a-b4e1-680b05a02c72",
        "survey_id": "7db931ce-05a0-4421-9a79-b46a26b61b06",
        "topic": "Какая из следующих команд является футбольной?",
        "index": 6,
        "type": "SINGLE",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "a0e77486-9ed9-4124-9a89-5c62990e8f01",
        "survey_id": "7db931ce-05a0-4421-9a79-b46a26b61b06",
        "topic": "Кто написал картину \"Мона Лиза\"?",
        "index": 7,
        "type": "MULTI",
        "status": "ARCHIVED",
        "answers": 10
    },
    {
        "question_id": "e3b4df81-eeb3-462c-a032-149d06fafb60",
        "survey_id": "7db931ce-05a0-4421-9a79-b46a26b61b06",
        "topic": "Что означает аббревиатура URL?",
        "index": 8,
        "type": "MULTI",
        "status": "EDIT_ONLY",
        "answers": 10
    },
    {
        "question_id": "566ff3ec-679b-49e8-80c3-2da9bc49b58e",
        "survey_id": "7db931ce-05a0-4421-9a79-b46a26b61b06",
        "topic": "В каком году произошло Великое французское революционное восстание?",
        "index": 9,
        "type": "SINGLE",
        "status": "EDIT_ONLY",
        "answers": 10
    }
]

