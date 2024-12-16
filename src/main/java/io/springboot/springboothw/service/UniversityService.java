package io.springboot.springboothw.service;

import io.springboot.springboothw.exception.NotValidCountryException;
import io.springboot.springboothw.pojo.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class UniversityService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String API_URL = "http://universities.hipolabs.com/search";

    public Map<String, List<University>> getAllUniversities() {
        University[] universities = restTemplate.getForObject(API_URL, University[].class);
        return Arrays.stream(universities)
                .collect(Collectors.groupingBy(University::getCountry, Collectors.toList()));
    }

    public Map<String, List<University>> getUniversitiesByCountries(List<String> countries) {
        List<CompletableFuture<Map<String, List<University>>>> futures = countries.stream()
            .map(country -> CompletableFuture.supplyAsync(() -> getUniversitiesByCountry(country)))
            .collect(Collectors.toList());
        Map<String, List<University>> universitiesByCountries = new HashMap<>();
        futures.forEach(future -> {
            try {
                universitiesByCountries.putAll(future.join());
            } catch (Exception e) {
                throw new NotValidCountryException("Error fetching data for one or more countries " + e.getMessage());
            }
        });
        return universitiesByCountries;
    }

    private Map<String, List<University>> getUniversitiesByCountry(String country) {
        String url = API_URL + "?country=" + country;
        try {
            University[] universities = restTemplate.getForObject(url, University[].class);
            Map<String, List<University>> universitiesByCountries = new HashMap<>();
            universitiesByCountries.put(country, Arrays.asList(universities));
            return universitiesByCountries;
        } catch (Exception e) {
            throw new NotValidCountryException(country + " is not a valid country");
        }
    }
}
