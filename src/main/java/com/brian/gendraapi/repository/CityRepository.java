package com.brian.gendraapi.repository;

import com.brian.gendraapi.model.City;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository {

    Optional<City> searchCities(String q, double latitude, double longitude);

}
