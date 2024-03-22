import {api} from "../api.js";
import {checkResponse} from "../utils.js";

document.addEventListener("DOMContentLoaded", () => {
    const survey = document.getElementById("survey");
    const questionTemplate = document.getElementById("question");
    const answerTemplate = document.getElementById("answer");
    const surveyId = new URLSearchParams(location.search).get("surveyId");

    const form = document.getElementById("form");
    const submitButton = document.getElementById("submitButton");
    submitButton.addEventListener('click', event => {
        const formData = new FormData(form);
        let data = Object.values(Object.fromEntries(formData))
        api.questionnaire.submit(surveyId, JSON.stringify(data))
            .then((response) => checkResponse(response, window.close))
    });

    fillForm();

    function fillForm() {
        api.questionnaire.get(surveyId)
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
            body.appendChild(createAnswer(answer, type, questionData.topic))
        }
        return question;
    }

    function createAnswer(answerData, type, name) {
        let answer = answerTemplate.content.cloneNode(true).firstElementChild;
        let input = answer.children.item(0);
        let label = answer.children.item(1);
        input.type = type;
        input.value = answerData.answerId;
        input.id = answerData.answerId;
        input.name = name;
        label.setAttribute('for', answerData.answerId);
        label.textContent = answerData.text;
        return answer;
    }
});
