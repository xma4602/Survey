import json
import uuid
import random
import os


# ==============================================================================
# необходимые настройки
# ==============================================================================

class UUIDEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, uuid.UUID):
            # if the obj is uuid, we simply return the value of uuid
            return str(obj)
        return json.JSONEncoder.default(self, obj)


rd_uuid = random.Random()
rd_uuid.seed(0)

rd_int = random.Random()
rd_int.seed(0)


def get_uuid():
    return uuid.UUID(int=rd_uuid.getrandbits(128), version=4)


def get_count():
    return rd_int.randint(0, 100)


def get_type():
    if rd_int.randint(0, 2) == 0:
        return r"'SINGLE'"
    else:
        return r"'MULTI'"


def get_status():
    num = rd_int.randint(0, 3)
    if num == 0:
        return r"'EDIT_ONLY'"
    elif num == 1:
        return r"'ANSWERS_ONLY'"
    else:
        return r"'ARCHIVED'"


data_path = r".\data.json"
questions_path = r".\sql\insert_questions.sql"
answers_path = r".\sql\insert_answers.sql"
surveys_path = r".\sql\insert_surveys.sql"

# ==============================================================================
# чтение данных вопросов, форматирование и сохранение в более удобный вариант
# ==============================================================================

with open(data_path, 'r', encoding='utf-8') as file:
    data = json.load(file)

surveys = []
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
                "status": get_status(),
                "type": get_type(),
                "index": n % count,
                "answers": []}
    survey["questions"].append(question)
    answers = item['answers']
    for index in range(len(answers)):
        answer = {"answer_id": get_uuid(),
                  "question_id": question_id,
                  "index": index,
                  "count": get_count(),
                  "text": answers[index]}
        question["answers"].append(answer)

# ==============================================================================
# чтение данных опросов и сохранение в SQL-insertы
# ==============================================================================
if 'sql' not in os.listdir():
    os.mkdir('sql')

surveys_file = open(surveys_path, 'w+', encoding='utf-8')
surveys_file.write("INSERT INTO \"surveys\" (survey_id, title) VALUES\n")

questions_file = open(questions_path, 'w+', encoding='utf-8')
questions_file.write("INSERT INTO \"questions\" (question_id, survey_id, status, type, index, topic) VALUES\n")

answers_file = open(answers_path, 'w+', encoding='utf-8')
answers_file.write("INSERT INTO \"answers\" (answer_id, question_id, count, index, text) VALUES\n")

for survey in surveys:
    end = ';' if survey == surveys[-1] else ','
    s = "('{}', '{}')".format(survey["survey_id"], survey["title"])
    surveys_file.write(s + end + '\n')

    questions = survey["questions"]
    for question in questions:
        end = ';' if survey == surveys[-1] and question == questions[-1] else ','
        s = "('{}', '{}', {:14}, {:8}, {}, '{}')".format(question['question_id'], question['survey_id'],
                                                           question['status'], question['type'],
                                                           question['index'], question['topic'])
        questions_file.write(s + end + '\n')

        answers = question["answers"]
        for answer in answers:
            end = ';' if survey == surveys[-1] and question == questions[-1] and answer == answers[-1] else ','
            s = "('{}', '{}', {:2}, {}, '{}')".format(answer["answer_id"], answer["question_id"],
                                                    answer['count'], answer['index'], answer['text'])
            answers_file.write(s + end + '\n')

surveys_file.close()
questions_file.close()
answers_file.close()
