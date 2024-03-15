
document.addEventListener("DOMContentLoaded", () => {
    const table = document.getElementById("table");
    const rowTemplate = document.querySelector("#row-template");
    const buttonEdit = document.querySelector("#btn-edit");
    const buttonDelete = document.querySelector("#btn-delete");
    const buttonClear = document.querySelector("#btn-clear");
    const buttonOpen = document.querySelector("#btn-open");
    const buttonClose = document.querySelector("#btn-close");

    fillTable();
    enableTooltips();

    function fillTable() {
        api.getQuestions()
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
        rowFields[0].textContent = item.question_id;
        rowFields[1].textContent = item.survey_id;
        rowFields[2].textContent = item.index;
        rowFields[3].textContent = item.topic;
        rowFields[4].innerHTML = getTypeView(item.type);
        rowFields[5].innerHTML = getStatusView(item.status);
        rowFields[6].innerHTML = `<a href="${api.host}/web/src/main/web/answers?squestion_id=${item.question_id}">${item.answers}</a>`;
        rowFields[7].firstElementChild.append(...getActionsView(item.status));
        return row;
    }

    function getTypeView(type) {
        if (type === 'SINGLE') {
            return `<img src="img/type_single.svg" height=15> Один ответ`
        } else {
            return `<img src="img/type_multi.svg" height=15> Много ответов`
        }
    }

    function getStatusView(status) {
        if (status === 'EDIT_ONLY') {
            return `<img src="img/status_created.svg" height=15> Не открыт`
        } else if (status === 'ANSWERS_ONLY') {
            return `<img src="img/status_opened.svg" height=15> Открыт`
        } else {
            return `<img src="img/status_closed.svg" height=15> Закрыт`
        }
    }


    function getActionsView(status) {
        buttons = [];
        buttons.push(buttonEdit.content.cloneNode(true));
        buttons.push(buttonDelete.content.cloneNode(true));
        buttons.push(buttonClear.content.cloneNode(true));
        if (status === 'EDIT_ONLY')
            buttons.push(buttonOpen.content.cloneNode(true));
        if (status === 'ANSWERS_ONLY')
            buttons.push(buttonClose.content.cloneNode(true));
        console.log(buttons);
        return buttons;
    }

    function enableTooltips() {
        const tooltipTriggerList = document.querySelectorAll('[data-bs-toggle="tooltip"]')
        const tooltipList = [...tooltipTriggerList].map(tooltipTriggerEl => new bootstrap.Tooltip(tooltipTriggerEl))
    }


});
