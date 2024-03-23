import {api} from "../api.js";
import {fillTable, buttonEdit, buttonDelete} from "../utils.js";

document.addEventListener("DOMContentLoaded", () => {
    const table = document.getElementById("table");
    const rowTemplate = document.querySelector("#row-template");
    const btnEdit = document.querySelector("#btn-edit");
    const btnDelete = document.querySelector("#btn-delete");

    const createButton = document.getElementById("create");
    createButton.addEventListener('click', event => {
        event.preventDefault();
        location.replace('/surveys/new');
    })

    refreshData();

    function refreshData() {
        fillTable(table, createRow, api.surveys.getAll());
    }


    function createRow(index, item) {
        let row = rowTemplate.content.cloneNode(true);
        row.querySelector("th").textContent = index;
        let rowFields = row.querySelectorAll("td");
        rowFields[0].textContent = item.surveyId;
        rowFields[1].textContent = item.title;
        rowFields[2].innerHTML = getQuestionnaireLink(item.surveyId);
        rowFields[3].innerHTML = getQuestionsLink(item.surveyId);
        rowFields[4].innerHTML = item.count;
        rowFields[5].firstElementChild.append(
            createButtonEdit(item.surveyId),
            createButtonDelete(item.surveyId)
        )
        return row;
    }

    function getQuestionsLink(id) {
        return `<a href="/questions?surveyId=${id}">Вопросы</a>`
    }

    function getQuestionnaireLink(id) {
        return `<a href="/questionnaires?surveyId=${id}">Анкета</a>`
    }

    function createButtonEdit(id) {
        return buttonEdit(btnEdit, '/surveys/edit?surveyId=' + id);
    }

    function createButtonDelete(id) {
        return buttonDelete(btnDelete, () => api.surveys.delete(id), refreshData);
    }

});
