package com.brian.gendraapi;

import com.brian.gendraapi.repository.SuggestionRepositoryImpl;
import com.brian.gendraapi.service.SuggestionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileNotFoundException;

@SpringBootApplication
public class GendraApiApplication {

    public static void main(String[] args) {


        SpringApplication.run(GendraApiApplication.class, args);
//
//        SuggestionRepositoryImpl repo = new SuggestionRepositoryImpl();
//
//        SuggestionService test = new SuggestionService(repo);
//        try {
//            test.searchCities("Londo", 43.70011, -79.4163);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }


}
