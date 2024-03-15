
document.addEventListener("DOMContentLoaded", () => {
    const table = document.getElementById("table");
    const rowTemplate = document.querySelector("#row-template");
    const buttonEdit = document.querySelector("#btn-edit");
    const buttonDelete = document.querySelector("#btn-delete");

    fillTable();

    function fillTable() {
        api.getAnswers()
            .then(
                items => {
                    for (let index = 0; index < items.length; index++) {
                        table.appendChild(createRow(index + 1, items[index]));
                    }
                }
            )
    }

    function createRow(number, item) {
        let row = rowTemplate.content.cloneNode(true);
        row.querySelector("th").textContent = number;
        let rowFields = row.querySelectorAll("td");
        rowFields[0].textContent = item.answer_id;
        rowFields[1].textContent = item.question_id;
        rowFields[2].textContent = item.index;
        rowFields[3].textContent = item.text;
        rowFields[4].textContent = item.count;
        return row;
    }


});
