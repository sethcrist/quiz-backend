package com.example.ezquizbackend.service;

import com.example.ezquizbackend.model.Quiz;
import com.example.ezquizbackend.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


/**
 * Service class for managing quizzes.
 * Provides operation to manage quiz data including retrieval, creation and deletion of quiz entities.
 */
@Service
public class QuizService {
    private final QuizRepository quizRepository;

    /**
     * Constructs a new quizService with the given quizrepository
     *
     * @param quizRepository the repository used for quiz data operations, automatically injected by spring
     */
    @Autowired
    // annotation is used on the constructor to tell spring to automatically inject an instance of QuizRepository when creating an instance of QuizService
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    /**
     * Retrieves all quizzes stored in the database
     *
     * @return a list of all quizzes, if none exist returns an empty list
     */
    public List<Quiz> getAllQuizzes() {
        return quizRepository.findAll();
    }

    /**
     * retrieves a quiz by its ID.
     *
     * @param id the ID of the quiz to retrieve
     * @return an Optional containing the quiz if found, or an empty Optional if not found
     * Optional = container object that may or may not contain a non-null value
     */
    public Quiz getQuizById(Long id) {
        return quizRepository.findById(id).orElse(null);
    }

    /**
     * Saves a quize to the database. Used for creating a new quiz or updating an existing quiz
     *
     * @param quiz to be saved
     * @return the saved quiz with any modification
     */
    public Quiz saveQuiz(Quiz quiz) {
        return quizRepository.save(quiz);
    }

    /**
     * deletes a quiz from the database by its ID
     *
     * @param id the ID of the quiz to be deleted
     */
    public void deleteQuiz(Long id) {
        quizRepository.deleteById(id);
    }
}
