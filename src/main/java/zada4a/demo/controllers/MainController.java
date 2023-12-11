package zada4a.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import zada4a.demo.entities.Answer;
import zada4a.demo.entities.Question;
import zada4a.demo.entities.Survey;
import zada4a.demo.entities.Users;
import zada4a.demo.repositories.AnswerRepository;
import zada4a.demo.repositories.QuestionRepository;
import zada4a.demo.repositories.SurveyRepository;
import zada4a.demo.services.QuestionService;
import zada4a.demo.services.impl.UserServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    UserServiceImpl userService;

    @Autowired
    private SurveyRepository surveyRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;


    @GetMapping("/surveys/{id}")
    public String surveyDetails(@PathVariable("id")Long id, Model model){
        model.addAttribute("survey", surveyRepository.getById(id));
        return "details";
    }

    @GetMapping("/addSurvey")
    public String addSurvey(){
        return "addSurvey";
    }

    @GetMapping("/addanswer")
    public String addAnswer(Model model){
        model.addAttribute("questions", questionRepository.findAll());
        return "addAnswer";
    }

    @PostMapping("/addAnswer")
    @PreAuthorize("isAuthenticated()")
    public String answerNew(@RequestParam("question")Long questionId, @RequestBody List<Answer> answers){
        answers.forEach(a -> a.setQuestion(questionRepository.getById(questionId)));
        answerRepository.saveAll(answers);
        return "redirect:/addanswer";
    }

    @PostMapping("/addQuestion")
    @PreAuthorize("isAuthenticated()")
    public String questionNew(@RequestParam("survey")Long surveyId,
                              @RequestParam("question")String question){
        Question question1 = new Question();
        question1.setTitle(question);
        question1.setSurvey(surveyRepository.getById(surveyId));
        questionRepository.save(question1);
        return "redirect:/addquestion";
    }

    @PostMapping("/addSurvey")
    @PreAuthorize("isAuthenticated()")
    public String processSurvey(@RequestParam String surveyTitle,
                                @RequestParam String surveyDescription) {
        Survey survey = new Survey();
        survey.setTitle(surveyTitle);
        survey.setDescription(surveyDescription);
        survey.setAuthor(getUser());
        surveyRepository.save(survey);
        return "redirect:/addquestion";
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("currentUser", getUser());
        return "index";
    }


    @GetMapping("/addquestion")
    public String addQuestion(Model model) {
        model.addAttribute("surveys", surveyRepository.findAll());
        return "addquestion";
    }

    @ModelAttribute
    private void addUser(Model model){
        model.addAttribute("currentUser", getUser());
    }

    public Users getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() &&
                !(authentication instanceof AnonymousAuthenticationToken)) {
            return (Users) authentication.getPrincipal();
        }
        return null;
    }

}
