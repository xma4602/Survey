document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("form");
    const button = document.getElementById("submitButton");

    if (button.getAttribute('method') === 'create') {
        button.addEventListener("click", createSurvey)
    } else {
        button.addEventListener("click", updateSurvey)
    }

    function createSurvey(event) {
        event.preventDefault();
        const formData = new FormData(form);
        api.surveys.create(JSON.stringify(Object.fromEntries(formData.entries())))
            .then(() =>
                window.location.replace('/surveys')
            )
            .catch(reason => console.log(reason))
    }

    function updateSurvey(event) {
        event.preventDefault();
        const formData = new FormData(form);
        api.surveys.update(JSON.stringify(Object.fromEntries(formData.entries())))
            .then(() =>
                window.location.replace('/surveys')
            )
            .catch(reason => console.log(reason))
    }

});
