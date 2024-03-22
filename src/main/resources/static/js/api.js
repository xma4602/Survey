export const api = {
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
            .then(response => {
                console.log(response);
                return response
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
            .then(response => {
                console.log(response);
                return response
            })
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
            .then(response => {
                console.log(response);
                return response
            })
    },

    async putObject(url, params, body) {
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
            .then(response => {
                console.log(response);
                return response
            })
    },

    surveys: {
        async getAll(params) {
            return api.getObject('/surveys/items?', params)
        },
        async delete(surveyId) {
            return api.deleteObject('/surveys/delete?', {'surveyId': surveyId})
        },
        async create(survey) {
            return api.postObject('/surveys/create', {}, survey)
        },
        async update(survey) {
            return api.putObject('/surveys/update', {}, survey)
        }
    },

    questions: {
        async getAll(params) {
            return api.getObject('/questions/items?', params)
        },
        async delete(questionId) {
            return api.deleteObject('/questions/delete?', {'questionId': questionId})
        },
        async create(question) {
            return api.postObject('/questions/create', {}, question)
        },
        async update(question) {
            return api.putObject('/questions/update', {}, question)
        },
        async open(questionId) {
            return api.putObject('/questions/open?', {'questionId': questionId})
        },
        async close(questionId) {
            return api.putObject('/questions/close?', {'questionId': questionId})
        },
        async clear(questionId) {
            return api.putObject('/questions/clear?', {'questionId': questionId})
        }

    },

    answers: {
        async getAll(params) {
            return api.getObject('/answers/items?', params)
        },
        async delete(answerId) {
            return api.deleteObject('/answers/delete?', {'answerId': answerId})
        },
        async create(answer) {
            return api.postObject('/answers/create', {}, answer)
        },
        async update(answer) {
            return api.putObject('/answers/update', {}, answer)
        }
    },

    questionnaire: {
        async get(surveyId) {
            return api.getObject('/questionnaires/items?', {'surveyId': surveyId})
        },
        async submit(surveyId, answers) {
            return api.postObject('/questionnaires/submit?', {'surveyId': surveyId}, answers)
        },
    }
};
