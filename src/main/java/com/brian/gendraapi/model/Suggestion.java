package com.brian.gendraapi.model;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@Data
@AllArgsConstructor
@ToString
public class Suggestion implements Serializable  {
    private String name;
    private double latitude;
    private double longitude;
    private double score;
}
