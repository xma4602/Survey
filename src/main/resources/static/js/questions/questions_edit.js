import {api} from "../api.js";
import {checkResponse} from "../utils.js";

document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("form");
    const button = document.getElementById("submitButton");

    if (button.getAttribute('method') === 'create') {
        button.addEventListener("click", createQuestion)
    } else {
        button.addEventListener("click", updateQuestion)
    }

    function createQuestion(event) {
        event.preventDefault();

        const formData = new FormData(form);
        api.questions.create(JSON.stringify(Object.fromEntries(formData.entries())))
            .then((response) => checkResponse(response, () => location.replace('/questions')))

    }

    function updateQuestion(event) {
        event.preventDefault();
        const formData = new FormData(form);
        api.questions.update(JSON.stringify(Object.fromEntries(formData.entries())))
            .then((response) => checkResponse(response, () => location.replace('/questions')))
    }


});
