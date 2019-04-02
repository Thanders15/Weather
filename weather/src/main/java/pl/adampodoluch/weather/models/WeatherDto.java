package pl.adampodoluch.weather.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDto {
    @JsonProperty("main")
    private WeatherMain mainData;


    @JsonProperty("clouds")
    private WeatherClouds cloudData;
    @Data
    public class WeatherMain {
        private double temp;
        private int humidity;
        private int pressure;
    }
    @Data
    public class WeatherClouds {
        @JsonProperty("all")
        private int clouds;
    }

}
