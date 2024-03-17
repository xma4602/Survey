
document.addEventListener("DOMContentLoaded", () => {
    const table = document.getElementById("table");
    const rowTemplate = document.querySelector("#row-template");
    const buttonEdit = document.querySelector("#btn-edit");
    const buttonDelete = document.querySelector("#btn-delete");

    fillTable(table, createRow, api.getAnswers());

    function createRow(number, item) {
        let row = rowTemplate.content.cloneNode(true);
        row.querySelector("th").textContent = number;
        let rowFields = row.querySelectorAll("td");
        rowFields[0].textContent = item.answer_id;
        rowFields[1].textContent = item.question_id;
        rowFields[2].textContent = item.index;
        rowFields[3].textContent = item.text;
        rowFields[4].textContent = item.count;
        setEditButton(rowFields[5].firstElementChild.children.item(0), item.answer_id)
        setDeleteButton(rowFields[5].firstElementChild.children.item(1), item.answer_id)
        return row;
    }

    function setEditButton(button, answer_id) {
        button.addEventListener('click', e => {
            e.preventDefault();
            window.open(`${api.host}/answers/${answer_id}/edit`);
        })
    }

    function setDeleteButton(button, answer_id) {
        button.addEventListener('click', e => {
            e.preventDefault();
            if (window.confirm("Точно удалить элемент?")) {
                api.deleteAnswer(answer_id)
            }
        })
    }
});
