package com.brian.gendraapi.service;

import com.brian.gendraapi.model.Suggestion;
import com.brian.gendraapi.repository.SuggestionRepository;
import com.brian.gendraapi.repository.SuggestionRepositoryImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class SuggestionServiceTest {

    @Mock
    private SuggestionRepositoryImpl suggestionRepository;

    @InjectMocks
    private SuggestionService suggestionService;


    private Suggestion suggestion;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        suggestion = new Suggestion();
        suggestion.setName("London, 08, CA");
        suggestion.setLatitude(42.98339);
        suggestion.setLongitude(-81.8);
        suggestion.setScore(0.99928);
    }

    @Test
    void searchCities() throws FileNotFoundException {
        when(suggestionService.searchCities("London", 1000, -81.8)).thenReturn(Arrays.asList(suggestion));
        assertNotNull(suggestionService.searchCities("London", 42.98339, -81.8));
    }
}