package pl.adampodoluch.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.adampodoluch.weather.models.WeatherService;

@Controller
public class MainController {

     private final WeatherService weatherService;

     @Autowired
     public  MainController(WeatherService weatherService) {
         this.weatherService = weatherService;
     }

    @GetMapping("/")
    public String weather(){
        return "weather";
    }

    @PostMapping("/")
    public String weatherapp (@RequestParam ("city") String city, Model model){
        model.addAttribute("weather", weatherService.getWeather(city));
        model.addAttribute("averageTempForNext5days", weatherService.getAverageFromWeatherForecast(city));

        return "weather";
    }
}
