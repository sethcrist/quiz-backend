package com.example.ezquizbackend.repository;

import com.example.ezquizbackend.model.Quiz;
import  org.springframework.data.jpa.repository.JpaRepository;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
}
// java interface can be accessed by any class in any package.