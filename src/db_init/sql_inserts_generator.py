import json
import uuid
import random
import os


# +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
# Скрипт, который из файла data.json с тестовыми данными опросов генерирует sql-скрипты их вставки в БД.
# +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

# ==============================================================================
# необходимые настройки
# ==============================================================================

class UUIDEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, uuid.UUID):
            # if the obj is uuid, we simply return the value of uuid
            return str(obj)
        return json.JSONEncoder.default(self, obj)


rd = random.Random()
rd.seed(0)

rd2 = random.Random()
rd2.seed(0)


def get_uuid():
    return uuid.UUID(int=rd.getrandbits(128), version=4)


def get_count():
    return rd2.randint(0, 30)


def get_type():
    if rd2.randint(0, 2) % 2 == 0:
        return 'SINGLE'
    else:
        return 'MULTI'


def get_status():
    n = rd2.randint(0, 2)
    if n == 0:
        return 'EDIT_ONLY'
    elif n == 1:
        return 'ANSWERS_ONLY'
    else:
        return 'ARCHIVED'


data_path = r"data.json"
result_path = r"result.json"
questions_path = r"sql\insert_questions.sql"
answers_path = r"sql\insert_answers.sql"
surveys_path = r"sql\insert_surveys.sql"

# ==============================================================================
# чтение данных вопросов, форматирование и сохранение в более удобный вариант
# ==============================================================================

with open(data_path, 'r', encoding='utf-8') as file:
    data = json.load(file)

surveys = []
questions = []
answers = []
count = 10
for n in range(len(data)):
    item = data[n]
    if n % count == 0:
        survey_id = get_uuid()
        survey = {"survey_id": survey_id,
                  "title": 'Опрос ' + str(n // count + 1),
                  "questions": []}
        surveys.append(survey)
    question_id = get_uuid()
    question = {"question_id": question_id,
                "survey_id": survey_id,
                "topic": item['topic'],
                "index": n % count,
                "type": get_type(),
                "status": get_status(),
                "answers": []}
    survey["questions"].append(question)
    questions.append(question)
    ans = item['answers']
    for index in range(len(ans)):
        answer = {"answer_id": get_uuid(),
                  "question_id": question_id,
                  "index": index,
                  "count": get_count(),
                  "text": ans[index]}
        question["answers"].append(answer)
        answers.append(answer)

with open("_surveys.json", 'w+', encoding='utf-8') as file:
    json.dump(surveys, file, indent=4, ensure_ascii=False, cls=UUIDEncoder)
with open("_questions.json", 'w+', encoding='utf-8') as file:
    json.dump(questions, file, indent=4, ensure_ascii=False, cls=UUIDEncoder)
with open("_answers.json", 'w+', encoding='utf-8') as file:
    json.dump(answers, file, indent=4, ensure_ascii=False, cls=UUIDEncoder)

# ==============================================================================
# чтение данных опросов и сохранение в SQL-insertы
# ==============================================================================
if 'sql' not in os.listdir():
    os.mkdir('sql')

surveys_file = open(surveys_path, 'w+', encoding='utf-8')
surveys_file.write('INSERT INTO "surveys" (survey_id, title) VALUES\n')

questions_file = open(questions_path, 'w+', encoding='utf-8')
questions_file.write('INSERT INTO "questions" (question_id, survey_id, index, type, status, topic) VALUES\n')

answers_file = open(answers_path, 'w+', encoding='utf-8')
answers_file.write('INSERT INTO "answers" (answer_id, question_id, index, text, count) VALUES\n')

for survey in surveys:
    end = ';' if survey == surveys[-1] else ','
    s = "('{}', '{}')".format(survey["survey_id"], survey["title"])
    surveys_file.write(s + end + '\n')

    questions = survey["questions"]
    for question in questions:
        end = ';' if survey == surveys[-1] and question == questions[-1] else ','
        s = "('{}', '{}', '{}', '{}', '{}', '{}')".format(question['question_id'], question['survey_id'],
                                                          question['index'], question['type'],
                                                          question['status'], question['topic'])
        questions_file.write(s + end + '\n')

        ans = question["answers"]
        for answer in ans:
            end = ';' if survey == surveys[-1] and question == questions[-1] and answer == ans[-1] else ','
            s = "('{}','{}', {}, '{}', {})".format(answer["answer_id"], answer["question_id"], answer['index'],
                                                   answer['text'], answer['count'])
            answers_file.write(s + end + '\n')

surveys_file.close()
questions_file.close()
answers_file.close()
