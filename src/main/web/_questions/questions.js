document.addEventListener("DOMContentLoaded", () => {
    const table = document.getElementById("table");
    const rowTemplate = document.querySelector("#row-template");
    const buttonEdit = document.querySelector("#btn-edit");
    const buttonDelete = document.querySelector("#btn-delete");
    const buttonClear = document.querySelector("#btn-clear");
    const buttonOpen = document.querySelector("#btn-open");
    const buttonClose = document.querySelector("#btn-close");

    const type_single = '../img/type_single.svg'
    const type_multi = '../img/type_multi.svg'
    const status_created = '../img/status_created.svg'
    const status_opened = '../img/status_opened.svg'
    const status_closed = '../img/status_closed.svg'

    fillTable();

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
        rowFields[7].firstElementChild.append(...getActionsView(item.question_id, item.status));
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

    function getActionsView(question_id, status) {
        let buttons = [];
        buttons.push(createButtonEdit(question_id));
        buttons.push(createButtonDelete(question_id));
        buttons.push(createButtonClear(question_id));
        if (status === 'EDIT_ONLY') buttons.push(createButtonOpen(question_id));
        if (status === 'ANSWERS_ONLY') buttons.push(createButtonClose());
        return buttons;
    }

    function createButtonEdit(question_id) {
        let button = buttonEdit.content.cloneNode(true).firstElementChild;
        button.addEventListener('click', e => {
            e.preventDefault();
            window.open(`${api.host}/web/src/main/web/_questions/questions_edit.html`);
        })
        return button;
    }

    function createButtonDelete(question_id) {
        let button = buttonDelete.content.cloneNode(true).firstElementChild;
        button.addEventListener('click', e => {
            e.preventDefault();
            if (window.confirm("Вы точно ходите удалить элемент?")) {
                api.deleteQuestion(question_id)
            }
        })
        return button;
    }

    function createButtonClear(question_id) {
        let button = buttonClear.content.cloneNode(true).firstElementChild;
        button.addEventListener('click', e => {
            e.preventDefault();
            if (window.confirm("Вы точно ходите сбросить ответы у вопроса?")) {
                api.clearQuestion(question_id)
            }
        })
        return button;
    }

    function createButtonClose(question_id) {
        let button = buttonClose.content.cloneNode(true).firstElementChild;
        button.addEventListener('click', e => {
            e.preventDefault();
            if (window.confirm("Вы точно ходите закрыть вопрос для ответов?")) {
                api.closeQuestion(question_id)
            }
        })
        return button;
    }


    function createButtonOpen(question_id) {
        let button = buttonOpen.content.cloneNode(true).firstElementChild;
        button.addEventListener('click', e => {
            e.preventDefault();
            if (window.confirm("Вы точно ходите открыть вопрос для ответов?")) {
                api.openQuestion(question_id)
            }
        })
        return button;
    }
});
