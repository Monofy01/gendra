package com.brian.gendraapi.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@RequiredArgsConstructor
@Data
public class City {

    private String query;
    private double latitude;
    private double longitude;

}
