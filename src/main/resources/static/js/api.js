//import * as mock from "./answers_mock.js";
//export
const api = {
    host: "http://localhost:8080",

    async getObject(url, params) {
        let uri = this.host + url + new URLSearchParams(params);
        console.log('GET ' + uri);
        return fetch(uri,
            {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
            })
            .then(response => response.json());
    },

    async postObject(url, params, body) {
        let uri = this.host + url + new URLSearchParams(params);
        console.log(`POST ${uri} ${body}`);

        return fetch(uri,
            {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: body
            })
            .then(response => response.json());
    },

    async deleteObject(url, params) {
        let uri = this.host + url + new URLSearchParams(params);
        console.log('DELETE ' + uri);

        return fetch(uri,
            {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
            })
            .then(response => response.json())
    },

    async updateObject(url, params, body) {
        let uri = this.host + url + new URLSearchParams(params);
        console.log(`PUT ${uri} ${body}`);
        return fetch(uri,
            {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8'
                },
                body: body
            })
            .then(response => response.json())
    },

    surveys: {
        async getAll() {
            return api.getObject('/surveys/items', {})
        },
        async delete(surveyId) {
            return api.deleteObject('/surveys/delete?', {'surveyId': surveyId})
        },
        async create(survey) {
            return api.postObject('/surveys/crate', {}, survey)
        },
        async update(survey) {
            return api.updateObject('/surveys/update', {}, survey)
        }
    },

    questions: {
        async getQuestions(pageNumber, pageSize) {
            return questionsData;
        },
        deleteQuestion(question_id) {

        },
        clearQuestion(question_id) {

        },
        openQuestion(question_id) {

        },
        closeQuestion(question_id) {

        }
    },

    answers: {
        async getAnswers(pageNumber, pageSize) {
            return answersData;
        },

        deleteAnswer(survey_id) {

        }
    },


    async getQuestionnaire(pageNumber, pageSize) {
        return questionnaireData;
    }
};
