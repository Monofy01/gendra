package com.brian.gendraapi.repository;

import com.brian.gendraapi.model.City;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CityRepositoryImpl implements CityRepository{

    @Override
    public Optional<City> searchCities(String q, double latitude, double longitude) {

        // READ TSV
        String url = "/home/monofy/Documents/Projects/gendra/gendraAPI/src/main/resources/static/cities_canada-usa.tsv";
        List<String> countries = new ArrayList<>();




        return Optional.empty();
    }
}
