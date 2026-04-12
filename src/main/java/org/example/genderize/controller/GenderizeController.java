package org.example.genderize.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.example.genderize.dto.GenderizeResponse;
import org.example.genderize.service.GenderizeService;
import org.example.genderize.util.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class GenderizeController {

    private final GenderizeService service;


    public GenderizeController(GenderizeService service) {
        this.service = service;
    }


    @GetMapping("/api/classify")
    public ResponseEntity<ApiResponse<GenderizeResponse>> classify(@RequestParam @NotBlank(message = "Name parameter is required") String name) {

        var obj = service.classify(name);
        return ResponseEntity.ok(ApiResponse.success(obj));
    }
}
