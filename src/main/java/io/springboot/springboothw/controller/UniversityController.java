package io.springboot.springboothw.controller;

import io.springboot.springboothw.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @GetMapping("/search")
    public Map<String, List<String>> getUniversitiesByCountries(@RequestParam(required = false) List<String> country) {
        if (country == null || country.isEmpty()) {
            return universityService.getAllUniversities();
        }
        return universityService.getUniversitiesByCountries(country);
    }
}
