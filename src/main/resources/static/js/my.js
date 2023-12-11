$(document).ready(function () {
    var questionCounter = 1;

    function addQuestion() {
        var questionsContainer = $("#questionsContainer");

        var questionDiv = $("<div/>", {
            class: "form-group question",
            id: "question" + questionCounter
        });

        var questionLabel = $("<label/>", {
            text: "Question " + questionCounter + ":"
        });

        var questionInput = $("<input/>", {
            type: "text",
            class: "form-control ml-2",
            name: "questions[" + (questionCounter - 1) + "].text",
            placeholder: "Enter your question",
            required: true
        });

        var answersContainer = $("<div/>", {
            class: "ml-2 mt-2",
            id: "answersContainer" + questionCounter
        });

        var addAnswerBtn = $("<button/>", {
            type: "button",
            class: "btn btn-secondary",
            text: "Add Answer",
            click: function () {
                addAnswer(answersContainer, questionCounter);
            }
        });

        questionDiv.append(questionLabel);
        questionDiv.append(questionInput);
        questionDiv.append(answersContainer);
        questionDiv.append(addAnswerBtn);

        questionsContainer.append(questionDiv);

        questionCounter++;
    }

    function addAnswer(answersContainer, questionCounter) {
        var answerDiv = $("<div/>", {
            class: "form-group answer",
        });

        var answerLabel = $("<label/>", {
            text: "Answer for Question " + questionCounter + ":"
        });

        var answerInput = $("<input/>", {
            type: "text",
            class: "form-control ml-2",
            name: "questions[" + (questionCounter - 1) + "].answers[" + answersContainer.children().length + "].text",
            placeholder: "Enter answer text",
            required: true
        });

        var correctCheckbox = $("<input/>", {
            type: "checkbox",
            class: "ml-2",
            name: "questions[" + (questionCounter - 1) + "].answers[" + answersContainer.children().length + "].correct",
        });

        var correctLabel = $("<label/>", {
            text: "Correct"
        });

        answerDiv.append(answerLabel);
        answerDiv.append(answerInput);
        answerDiv.append(correctCheckbox);
        answerDiv.append(correctLabel);

        answersContainer.append(answerDiv);
    }

    $("#addQuestionBtn").click(addQuestion);
});
