package com.example.ezquizbackend;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EzQuizBackendApplication {

    public static void main(String[] args) {
        // Load .env file
        Dotenv dotenv = Dotenv.load();
        System.out.println("Database URL: " + dotenv.get("DATABASE_URL")); // Ensure this prints the correct value

        // Start the Spring application
        SpringApplication.run(EzQuizBackendApplication.class, args);
    }
}
