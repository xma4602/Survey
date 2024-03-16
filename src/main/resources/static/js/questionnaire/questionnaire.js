document.addEventListener("DOMContentLoaded", () => {
    const survey = document.getElementById("survey");
    const questionTemplate = document.getElementById("question");
    const answerTemplate = document.getElementById("answer");

    fillForm();

    function fillForm() {
        api.getQuestionnaire()
            .then(
                surveyData => {
                    document.title = 'Опросы.xma - ' + surveyData.title;
                    survey.firstElementChild.textContent = surveyData.title;
                    let body = survey.children.item(1);
                    for (let q of surveyData.questions) {
                        body.appendChild(createQuestion(q))
                    }
                }
            )
    }

    function createQuestion(questionData) {
        let question = questionTemplate.content.cloneNode(true).firstElementChild;
        question.firstElementChild.textContent = questionData.topic;
        let body = question.lastElementChild;
        for (let answer of questionData.answers) {
            let type = questionData.type === 'SINGLE' ? "radio" : "checkbox";
            body.appendChild(createAnswer(answer, type, questionData.question_id))
        }
        return question;
    }

    function createAnswer(answerData, type, name) {
        let answer = answerTemplate.content.cloneNode(true).firstElementChild;
        let input = answer.children.item(0);
        let label = answer.children.item(1);
        input.type = type;
        input.value = answerData.answer_id;
        input.id = answerData.answer_id;
        input.name = name;
        label.setAttribute('for', answerData.answer_id);
        label.textContent = answerData.text;
        return answer;
    }
});
