function fillTable(table, fillFunction, dataPromise) {
    dataPromise
        .then(
            items => {
                table.replaceChildren();
                for (let index = 0; index < items.length; index++) {
                    table.appendChild(fillFunction(index + 1, items[index]));
                }
            }
        )
}