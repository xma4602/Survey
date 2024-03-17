document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("form");
    const button = document.getElementById("submitButton");

    button.addEventListener("click", event => {
        event.preventDefault();
        const formData = new FormData(form);
        fetch('/surveys/create', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json;charset=utf-8'
            },
            body: JSON.stringify(Object.fromEntries(formData.entries()))
        })
            .then(res =>
                console.log(res)
            )
    })

});
