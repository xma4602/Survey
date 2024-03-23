export function fillTable(table, fillFunction, dataPromise) {
    dataPromise
        .then(
            items => {
                table.replaceChildren();
                for (let index = 0; index < items.length; index++) {
                    table.appendChild(fillFunction(index + 1, items[index]));
                }
            }
        )
        .catch(reason => console.log(reason))
}

export function checkResponse(response, onGood) {
    if (response.ok) {
        onGood(response.json())
    } else {
        response.json().then(({message}) => alert(message))
    }
}

export function buttonEdit(template, link) {
    let button = template.content.cloneNode(true).firstElementChild;
    button.addEventListener('click', e => {
        e.preventDefault();
        location.replace(link);
    })
    return button;
}

export function buttonDelete(template, requestFunc, responseFunc) {
    let button = template.content.cloneNode(true).firstElementChild;
    button.addEventListener('click', e => {
        e.preventDefault();
        if (window.confirm("Вы точно ходите удалить элемент?")) {
            requestFunc().then(() => responseFunc())
        }
    })
    return button;
}


export function buttonClear(template, requestFunc, responseFunc) {
    let button = template.content.cloneNode(true).firstElementChild;
    button.addEventListener('click', e => {
        e.preventDefault();
        if (window.confirm("Вы точно ходите сбросить ответы у вопроса?")) {
            requestFunc().then(() => responseFunc())
        }
    })
    return button;
}

export function buttonOpen(template, requestFunc, responseFunc) {
    let button = template.content.cloneNode(true).firstElementChild;
    button.addEventListener('click', e => {
        e.preventDefault();
        if (window.confirm("Вы точно ходите открыть вопрос для ответов?")) {
            requestFunc().then(() => responseFunc())
        }
    })
    return button;
}

export function buttonClose(template, requestFunc, responseFunc) {
    let button = template.content.cloneNode(true).firstElementChild;
    button.addEventListener('click', e => {
        e.preventDefault();
        if (window.confirm("Вы точно ходите закрыть вопрос для ответов?")) {
            requestFunc().then(() => responseFunc())
        }
    })
    return button;
}