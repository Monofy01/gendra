package com.brian.gendraapi.model;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@RequiredArgsConstructor
@Data
public class City implements Serializable {

    private String query;
    private double latitude;
    private double longitude;

}
