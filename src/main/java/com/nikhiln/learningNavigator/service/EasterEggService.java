package com.nikhiln.learningNavigator.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EasterEggService {

    @Autowired
    private RestTemplate restTemplate;

    public String getRandomFactAboutNumber(int number) {
        String url = "http://numbersapi.com/" + number;

        return restTemplate.getForObject(url, String.class);
    }
    
}
