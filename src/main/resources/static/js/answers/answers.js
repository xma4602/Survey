import {buttonDelete, buttonEdit, fillTable} from "../utils.js";
import {api} from "../api.js";

document.addEventListener("DOMContentLoaded", () => {
    const table = document.getElementById("table");
    const rowTemplate = document.querySelector("#row-template");
    const btnEdit = document.querySelector("#btn-edit");
    const btnDelete = document.querySelector("#btn-delete");

    const urlParams = new URLSearchParams(location.search).entries();

    const createButton = document.getElementById("create");
    createButton.addEventListener('click', event => {
        event.preventDefault();
        location.replace('/answers/new');
    })

    refreshData();

    function refreshData() {
        fillTable(table, createRow, api.answers.getAll(urlParams));
    }

    function createRow(number, item) {
        let row = rowTemplate.content.cloneNode(true);
        row.querySelector("th").textContent = number;
        let rowFields = row.querySelectorAll("td");
        rowFields[0].textContent = item.answerId;
        rowFields[1].textContent = item.questionId;
        rowFields[2].textContent = item.index;
        rowFields[3].textContent = item.text;
        rowFields[4].textContent = item.count;
        rowFields[5].firstElementChild.append(createButtonEdit(item.answerId))
        rowFields[5].firstElementChild.append(createButtonDelete(item.answerId))
        return row;
    }

    function createButtonEdit(id) {
        return buttonEdit(btnEdit, '/answers/edit?answerId=' + id);
    }

    function createButtonDelete(id) {
        return buttonDelete(btnDelete, () => api.answers.delete(id), refreshData);
    }
});
