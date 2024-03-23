import {api} from "../api.js";
import {checkResponse} from "../utils.js";

document.addEventListener("DOMContentLoaded", () => {
    const survey = document.getElementById("survey");
    const questionTemplate = document.getElementById("question");
    const answerTemplate = document.getElementById("answer");
    const messageSubmitted = document.getElementById("submitted");
    const footer = document.getElementById("footer");
    const form = document.getElementById("form");

    const surveyId = new URLSearchParams(location.search).get("surveyId");

    document.getElementById("submitButton").addEventListener('click', submitData);

    function submitData() {
        const formData = new FormData(form);
        const data = Array.from(formData.values())
        api.questionnaire.submit(surveyId, JSON.stringify(data))
            .then(response => checkResponse(response, fillStats))
    }

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

    function fillStats(stats) {
        stats.then(data => {
            footer.replaceChildren(messageSubmitted.content);

            for (let question of document.querySelectorAll('.question')) {
                for (let answer of question.querySelectorAll('.answer')) {
                    setStat(answer, data);
                }
            }
        })
    }

    function setStat(answer, stats) {
        let id = answer.children[0].id;
        let data = findData(id, stats);
        answer.children[1].textContent += ` (${data.count} / ${data.percent}%)`
        answer.append(createStatsBar(data.count))
    }

    function findData(id, stats) {
        for (let stat of stats) {
            let answerStats = stat.answerStats;
            for (let statId in answerStats) {
                if (statId === id){
                    return answerStats[statId];
                }
            }
        }
    }

    function createStatsBar(percent) {
        let progressBar = document.createElement('div');
        progressBar.innerHTML = `
           <div class="progress" role="progressbar" aria-valuenow="${percent}" aria-valuemin="0" aria-valuemax="100">
            <div class="progress-bar" style="width: ${percent}%"></div>
           </div>
        `;
        return progressBar;
    }
});
