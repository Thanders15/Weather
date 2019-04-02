package pl.adampodoluch.weather.models;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WeatherService {

    private Map<String, String> getUriParams (String cityName){
        Map<String, String > urlVars = new HashMap<>();
        urlVars.put("q", cityName);
        urlVars.put("appid2", "ea8fe9c636aa34399ffd7d71ad2d0f6a");
        urlVars.put("units", "metric");


        return urlVars;
    }
    public WeatherDto getWeather(String cityname){
        return getRestTemplate().getForObject("http://api.openweathermap.org/data/2.5/weather?q={q}&units={units}&appid={appid2}",
                WeatherDto.class,
                getUriParams(cityname));
    }
    public WeatherForecastDto getForecast(String cityname){
        return getRestTemplate().getForObject("http://api.openweathermap.org/data/2.5/forecast?q={q}&units={units}&appid={appid2}",
                WeatherForecastDto.class,
                getUriParams(cityname));
    }

    public double getAverageFromWeatherForecast(String cityName){
        double sum = 0;
        List<WeatherDto> weatherDtos = getForecast(cityName).getList();

        for(WeatherDto weatherDto : weatherDtos){
            sum += weatherDto.getMainData().getTemp();
        }
        return sum / weatherDtos.size();
    }



    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}



//Bean daje to że metoda zwraca tylko raz nie ważne ile razy ją wywołamy
//getForObject zamienia wynik url na obiekt weatherDto