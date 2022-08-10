package com.brian.gendraapi.controller;

import com.brian.gendraapi.model.Suggestion;
import com.brian.gendraapi.service.SuggestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/gendra")
public class SuggestionController {

    private SuggestionService suggestionService;

    public SuggestionController(SuggestionService suggestionService) {
        this.suggestionService = suggestionService;
    }

    @GetMapping("/suggestions")
    public ResponseEntity<List<Suggestion>> searchSuggestedCities(@RequestParam String q, @RequestParam double latitude, @RequestParam double longitude) {
//        return "hola " + q + latitude;
        try {
            List<Suggestion> suggestionList = suggestionService.searchCities(q, latitude, longitude);
            return new ResponseEntity<List<Suggestion>>(suggestionList, HttpStatus.OK);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            List<Suggestion> emptyList = Collections.emptyList();
            return new ResponseEntity<List<Suggestion>>(emptyList, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
