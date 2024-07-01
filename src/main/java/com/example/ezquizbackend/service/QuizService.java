package com.example.ezquizbackend.service;

import com.example.ezquizbackend.model.Quiz;
import com.example.ezquizbackend.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
// annotation marks the class as a service component in springs component scanning
public class QuizService {
    private final QuizRepository quizRepository;
    // holds a reference to the repository responsible for database operation related to quizzes

    @Autowired
    // annotation is used on the constructor to tell spring to automatically inject an instance of QuizRepository when creating an instance of QuizService
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
        // method retrieves all quiz records from the database by calling 'findAll()' on the quizRepository
    }

    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id).orElse(null);
        // retrieves a specific quiz by it ID, by calling findById(id) on the quizRepository, returns quiz if found or null if not
    }

    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
        // method saves / updates the quiz to the database
    }

    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
        // method deletes the quiz by id
    }
}
