package org.example.genderize.dto;


import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GenderizeResponse {

//    "name": "<name>",
//            "gender": "male",
//            "probability": 0.99,
//            "sample_size": 1234,
//            "is_confident": true,


    private final String name;

    private final String gender;

    private final Double probability;

    private final Integer sampleSize;

    private final Boolean isConfident;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    private final LocalDateTime processedAt;


    public GenderizeResponse(String name, String gender, Double probability, Integer sampleSize
    ) {
        this.name = name;
        this.gender = gender;
        this.probability = probability;
        this.sampleSize = sampleSize;
        this.isConfident = (probability != null && probability >= .7) && (sampleSize != null && sampleSize >= 100);
        this.processedAt = LocalDateTime.now();
    }
}
