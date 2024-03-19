document.addEventListener("DOMContentLoaded", () => {
    const table = document.getElementById("table");
    const rowTemplate = document.querySelector("#row-template");

    const createButton = document.getElementById("create");
    createButton.addEventListener('click', event=>{
        event.preventDefault();
        console.log('click');
        window.open('/surveys/new');
    })

    refreshData();

    function refreshData(){
        fillTable(table, createRow, api.surveys.getAll());
    }

    function createRow(index, item) {
        let row = rowTemplate.content.cloneNode(true);
        row.querySelector("th").textContent = index;
        let rowFields = row.querySelectorAll("td");
        rowFields[0].textContent = item.surveyId;
        rowFields[1].textContent = item.title;
        rowFields[2].innerHTML = getQuestionsLink(item.surveyId, item.questions);
        setEditButton(rowFields[3].firstElementChild.children.item(0), item.surveyId)
        setDeleteButton(rowFields[3].firstElementChild.children.item(1), item.surveyId)
        return row;
    }

    function getQuestionsLink(surveyId, count) {
        return `<a href="${api.host}/questions?surveyId=${surveyId}">${count}</a>`
    }

    function setEditButton(button, surveyId) {
        button.addEventListener('click', e => {
            e.preventDefault();
            window.open('/surveys/edit?surveyId='+surveyId);
        })
    }

    function setDeleteButton(button, surveyId) {
        button.addEventListener('click', e => {
            e.preventDefault();
            if (window.confirm("Точно удалить элемент?")) {
                api.surveys.delete(surveyId)
                    .then(() => refreshData())
            }
        })
    }

});
