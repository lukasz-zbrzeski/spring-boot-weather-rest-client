package com.example.springbootwarsawweatherapp.controller;

import com.example.springbootwarsawweatherapp.api.WeatherApi;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class WarsawWeatherController {
    private URL url;
    private InputStreamReader reader;

    @GetMapping("/weather")
    public WeatherApi warsawWeather() {
        try {
            url = new URL("http://api.openweathermap.org/data/2.5/weather?q=Warsaw,pl/forecast?id=524901&APPID=9f1c9d5001a81c74d30f19a75218c891");
            reader = new InputStreamReader(url.openStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        WeatherApi weatherApi = new Gson().fromJson(reader, WeatherApi.class);

        return weatherApi;
    }
}
