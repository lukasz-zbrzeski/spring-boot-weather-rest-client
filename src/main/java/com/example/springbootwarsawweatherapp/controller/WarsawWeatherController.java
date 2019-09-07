package com.example.springbootwarsawweatherapp.controller;

import com.example.springbootwarsawweatherapp.model.WeatherApi;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class WarsawWeatherController {

    @GetMapping("/weather")
    public WeatherApi warsawWeather() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WeatherApi> exchange = restTemplate.exchange(
                "http://api.openweathermap.org/data/2.5/weather?q=Warsaw,pl/forecast?id=524901&APPID=9f1c9d5001a81c74d30f19a75218c891",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                WeatherApi.class
        );

        return exchange.getBody();
    }
}
