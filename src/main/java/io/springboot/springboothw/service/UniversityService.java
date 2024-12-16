package io.springboot.springboothw.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;

@Service
public interface UniversityService {
    Map<String, List<String>> getAllUniversities();
    Map<String, List<String>> getUniversitiesByCountry(String universityName);
    Map<String, List<String>> getUniversitiesByCountries(List<String> countries);
}
