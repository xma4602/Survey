//import * as mock from "./answers_mock.js";
//export
const api = {
    host: "http://localhost:63342",

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
    },
    deleteSurvey(survey_id) {

    },
    deleteQuestion(question_id) {

    },
    clearQuestion(question_id) {
        
    },
    openQuestion(question_id) {
        
    },
    closeQuestion(question_id) {
        
    },
    deleteAnswer(survey_id) {

    }
};
