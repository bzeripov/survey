package zada4a.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zada4a.demo.entities.Question;
import zada4a.demo.repositories.QuestionRepository;
import zada4a.demo.entities.Answer;

import java.util.List;

// Ваш сервис вопроса
// QuestionService.java
@Service
public class QuestionService {
    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    public void addQuestion(Question question) {
        questionRepository.save(question);
    }
}

