import {checkResponse} from "../utils.js";
import {api} from "../api.js";

document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("form");
    const button = document.getElementById("submitButton");

    if (button.getAttribute('method') === 'create') {
        button.addEventListener("click", createAnswer)
    } else {
        button.addEventListener("click", updateAnswer)
    }

    function createAnswer(event) {
        event.preventDefault();
        const formData = new FormData(form);
        api.answers.create(JSON.stringify(Object.fromEntries(formData.entries())))
            .then((response) => checkResponse(response, () => location.replace('/answers')))
    }

    function updateAnswer(event) {
        event.preventDefault();

        const formData = new FormData(form);
        api.answers.update(JSON.stringify(Object.fromEntries(formData.entries())))
            .then(response => checkResponse(response, () => location.replace('/answers')))
    }

});
