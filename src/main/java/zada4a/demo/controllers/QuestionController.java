package zada4a.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zada4a.demo.entities.Answer;
import zada4a.demo.entities.Question;
import zada4a.demo.entities.Survey;
import zada4a.demo.repositories.AnswerRepository;
import zada4a.demo.repositories.QuestionRepository;
import zada4a.demo.repositories.SurveyRepository;
import zada4a.demo.services.QuestionService;

// QuestionController.java
// QuestionController.java
@Controller
@RequestMapping("/questions")
public class QuestionController {
    private final QuestionService questionService;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/add")
    public String showAddQuestionForm(Model model) {
        model.addAttribute("question", new Question());
        return "addQuestion";
    }

    @GetMapping("/surveys/{id}")
    public String surveyDetails(@PathVariable("id")Long id, Model model) {
        model.addAttribute("survey", surveyRepository.getById(id));
        return "details";
    }

}


