package com.brian.gendraapi.service;

import com.brian.gendraapi.repository.SuggestionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public class SuggestionService {

    private SuggestionRepositoryImpl suggestionRepository;

    @Autowired
    public SuggestionService(SuggestionRepositoryImpl suggestionRepository) {
        this.suggestionRepository = suggestionRepository;
    }

    public void searchCities(String q, double latitude, double longitude) throws FileNotFoundException {
        suggestionRepository.searchCities(q, latitude, longitude);
    }
}
