document.addEventListener("DOMContentLoaded", () => {
    const table = document.getElementById("table");
    const rowTemplate = document.querySelector("#row-template");

    fillTable();

    function fillTable() {
        api.getSurveys()
            .then(
                items => {
                    for (let index = 0; index < items.length; index++) {
                        table.appendChild(createRow(index + 1, items[index]));
                    }
                }
            )
    }

    function createRow(index, item) {
        let row = rowTemplate.content.cloneNode(true);
        row.querySelector("th").textContent = index;
        let rowFields = row.querySelectorAll("td");
        rowFields[0].textContent = item.survey_id;
        rowFields[1].textContent = item.title;
        rowFields[2].innerHTML = `<a href="${api.host}/web/src/main/web/questions?survey_id=${item.survey_id}">${item.questions}</a>`;
        return row;
    }

});
