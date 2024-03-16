//import * as mock from "./mock.js";
//export
const api = {
    host: "http://localhost:63343",

    async getSurveys(pageNumber, pageSize) {
        return surveysData;
    },

    async getQuestions(pageNumber, pageSize) {
        return questionsData;
    },

    async getAnswers(pageNumber, pageSize) {
        return answersData;
    },

    async getQuestionnaire(pageNumber, pageSize) {
        return questionnaireData;
    }
};
