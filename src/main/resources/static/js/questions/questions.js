import {fillTable, buttonOpen, buttonDelete, buttonEdit, buttonClose, buttonClear} from "../utils.js";
import {api} from "../api.js";

document.addEventListener("DOMContentLoaded", () => {
    const table = document.getElementById("table");
    const rowTemplate = document.querySelector("#row-template");

    const btnEdit = document.querySelector("#btn-edit");
    const btnDelete = document.querySelector("#btn-delete");
    const btnClear = document.querySelector("#btn-clear");
    const btnOpen = document.querySelector("#btn-open");
    const btnClose = document.querySelector("#btn-close");

    const urlParams = new URLSearchParams(location.search).toString();

    const type_single = '../img/type_single.svg'
    const type_multi = '../img/type_multi.svg'
    const status_created = '../img/status_created.svg'
    const status_opened = '../img/status_opened.svg'
    const status_closed = '../img/status_closed.svg'

    const createButton = document.getElementById("create");
    createButton.addEventListener('click', event => {
        event.preventDefault();
        location.replace('/questions/new');
    })

    refreshData();

    function refreshData() {
        fillTable(table, createRow, api.questions.getAll(urlParams));
    }

    function createRow(number, item) {
        let row = rowTemplate.content.cloneNode(true);
        row.querySelector("th").textContent = number;
        let rowFields = row.querySelectorAll("td");
        rowFields[0].textContent = item.questionId;
        rowFields[1].textContent = item.surveyId;
        rowFields[2].textContent = item.index;
        rowFields[3].textContent = item.topic;
        rowFields[4].innerHTML = getTypeView(item.type);
        rowFields[5].innerHTML = getStatusView(item.status);
        rowFields[6].innerHTML = getAnswersLink(item.questionId);
        rowFields[7].innerHTML = item.count;
        rowFields[8].firstElementChild.append(...getActionsView(item.questionId, item.status));
        return row;
    }

    function getTypeView(type) {
        if (type === 'SINGLE') {
            return `<img src="${type_single}" height=15> Один ответ`
        } else {
            return `<img src="${type_multi}" height=15> Много ответов`
        }
    }

    function getStatusView(status) {
        if (status === 'EDIT_ONLY') {
            return `<img src="${status_created}" height=15> Не открыт`
        } else if (status === 'ANSWERS_ONLY') {
            return `<img src="${status_opened}" height=15> Открыт`
        } else {
            return `<img src="${status_closed}" height=15> Закрыт`
        }
    }

    function getAnswersLink(questionId) {
        return `<a href="/answers?questionId=${questionId}">Ответы</a>`;
    }

    function getActionsView(questionId, status) {
        let buttons = [];
        buttons.push(createButtonEdit(questionId));
        buttons.push(createButtonDelete(questionId));
        buttons.push(createButtonClear(questionId));
        if (status === 'EDIT_ONLY') buttons.push(createButtonOpen(questionId));
        else if (status === 'ANSWERS_ONLY') buttons.push(createButtonClose(questionId));
        return buttons;
    }

    function createButtonEdit(id) {
        return buttonEdit(btnEdit, '/questions/edit?questionId=' + id);
    }

    function createButtonDelete(id) {
        return buttonDelete(btnDelete, () => api.questions.delete(id), refreshData);
    }

    function createButtonClear(id) {
        return buttonClear(btnClear, () => api.questions.clear(id), refreshData);
    }

    function createButtonClose(id) {
        return buttonClose(btnClose, () => api.questions.close(id), refreshData);
    }

    function createButtonOpen(id) {
        return buttonOpen(btnOpen, () => api.questions.open(id), refreshData);
    }
});
