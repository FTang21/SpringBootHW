package io.springboot.springboothw.controller;

import io.springboot.springboothw.pojo.University;
import io.springboot.springboothw.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UniversityController {

    private final UniversityService universityService;

    @Autowired
    private UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    };

    @GetMapping("/search")
    public ResponseEntity<Map<String, List<String>>> getUniversitiesByCountries(@RequestParam(required = false) List<String> country) {
        if (country == null || country.isEmpty()) {
            return new ResponseEntity<>(
                    universityService.getAllUniversities(),
                    HttpStatus.OK
            );
        }
        return new ResponseEntity<>(
                universityService.getUniversitiesByCountries(country),
                HttpStatus.OK
        );
    }
}
