package com.brian.gendraapi.repository;

import com.brian.gendraapi.model.Suggestion;

import java.io.FileNotFoundException;
import java.util.List;

public interface SuggestionRepository {

//    Optional<City> searchCities(String q, double latitude, double longitude) throws FileNotFoundException;
    List<Suggestion> searchCities(String q, double latitude, double longitude) throws FileNotFoundException;

}
