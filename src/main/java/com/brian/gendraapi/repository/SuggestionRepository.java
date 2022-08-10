package com.brian.gendraapi.repository;

import java.io.FileNotFoundException;

public interface SuggestionRepository {

//    Optional<City> searchCities(String q, double latitude, double longitude) throws FileNotFoundException;
    void searchCities(String q, double latitude, double longitude) throws FileNotFoundException;

}
