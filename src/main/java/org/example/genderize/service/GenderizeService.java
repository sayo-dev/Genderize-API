package org.example.genderize.service;

import org.example.genderize.dto.GenderizeResponse;
import org.example.genderize.exception.CustomBadRequestException;
import org.example.genderize.model.GenderizeRaw;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class GenderizeService {

    private final RestTemplate restTemplate;

    @Value("${genderlize.api.base-url}")
    private String BASE_URL;

    public GenderizeService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GenderizeResponse classify(String name) {

        URI uri = UriComponentsBuilder.fromUriString(BASE_URL)
                .queryParam("name", name)
                .build().toUri();

        GenderizeRaw rawResponse = restTemplate.getForObject(uri, GenderizeRaw.class);

        if (rawResponse == null || rawResponse.getGender() == null) {
            throw new CustomBadRequestException("No prediction available for the provided name");
        }

        return new GenderizeResponse(
                rawResponse.getName(),
                rawResponse.getGender(),
                rawResponse.getProbability(),
                rawResponse.getCount()
        );

    }


}
