package com.example.springbootwarsawweatherapp.controller;

import com.example.springbootwarsawweatherapp.model.WeatherApi;

import com.vaadin.flow.component.html.Label;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/weather")
public class WarsawWeatherController {

    public ResponseEntity<WeatherApi> connectToApi() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<WeatherApi> exchange = restTemplate.exchange(
                "http://api.openweathermap.org/data/2.5/weather?q=Warsaw,pl/forecast?id=524901&APPID=9f1c9d5001a81c74d30f19a75218c891",
                HttpMethod.GET,
                HttpEntity.EMPTY,
                WeatherApi.class
        );

        return exchange;
    }

    @GetMapping("/json")
    public WeatherApi weatherJson() {
        return connectToApi().getBody();
    }

    @GetMapping("/now")
    public String weatherNow() {

        Double tempC = connectToApi().getBody().getMain().getTemp() - 273.15;

        Label label = new Label(
                connectToApi().getBody().getName()
                        + ", " + connectToApi().getBody().getSys().getCountry()
                        + "\nTemperature: " + tempC.intValue() + "°C"
                        + "\nPressure: " + connectToApi().getBody().getMain().getPressure() + " hPa"
                        + "\n\nWind:"
                        + "\n     Speed: " + connectToApi().getBody().getWind().getSpeed() + " m/s"
                        + "\n     Degree: " + connectToApi().getBody().getWind().getDeg()
                        + "\n\nLocation of city: "
                        + "\n         Longitude: " + connectToApi().getBody().getCoord().getLon()
                        + "\n         Latitude: " + connectToApi().getBody().getCoord().getLat()
        );


        return label.getText();
    }

}
