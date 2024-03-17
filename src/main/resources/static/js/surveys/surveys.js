document.addEventListener("DOMContentLoaded", () => {
    const table = document.getElementById("table");
    const rowTemplate = document.querySelector("#row-template");

    fillTable(table, createRow, api.getSurveys());

    function createRow(index, item) {
        let row = rowTemplate.content.cloneNode(true);
        row.querySelector("th").textContent = index;
        let rowFields = row.querySelectorAll("td");
        rowFields[0].textContent = item.survey_id;
        rowFields[1].textContent = item.title;
        rowFields[2].innerHTML = getQuestionsLink(item.survey_id, item.questions);
        setEditButton(rowFields[3].firstElementChild.children.item(0), item.survey_id)
        setDeleteButton(rowFields[3].firstElementChild.children.item(1), item.survey_id)
        return row;
    }

    function getQuestionsLink(survey_id, count) {
        return `<a href="${api.host}/questions?survey_id=${survey_id}">${count}</a>`
    }

    function setEditButton(button, survey_id) {
        button.addEventListener('click', e => {
            e.preventDefault();
            window.open(`${api.host}/surveys/${survey_id}/edit`);
        })
    }

    function setDeleteButton(button, survey_id) {
        button.addEventListener('click', e => {
            e.preventDefault();
            if (window.confirm("Точно удалить элемент?")) {
                api.deleteSurvey(survey_id)
            }
        })
    }

});
