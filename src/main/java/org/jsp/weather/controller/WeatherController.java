package org.jsp.weather.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
@CrossOrigin
public class WeatherController {

	private String apiKey = "506c08ea473e6f0b54c864bd2707aa17";

	@GetMapping("weather/{city}")
	@ResponseBody
	public Result getWeather(@PathVariable String city) {
		RestTemplate restTemplate = new RestTemplate();
		String url = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

		WeatherResponse response = restTemplate.getForObject(url, WeatherResponse.class);

		return new Result(city, response.getMain().getTemp(), response.getMain().getHumidity(),
				response.getWeather().get(0).getMain(), response.getWeather().get(0).getDescription());
	}

	private static class WeatherResponse {
		private String name;
		private Main main;
		private List<Weather> weather;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Main getMain() {
			return main;
		}

		public void setMain(Main main) {
			this.main = main;
		}

		public List<Weather> getWeather() {
			return weather;
		}

		public void setWeathers(List<Weather> weather) {
			this.weather = weather;
		}

	}

	private static class Main {
		private double temp;
		private double humidity;

		public double getHumidity() {
			return humidity;
		}

		public void setHumidity(double humidity) {
			this.humidity = humidity;
		}

		public double getTemp() {
			return temp;
		}

		public void setTemp(double temp) {
			this.temp = temp - 273.19;
		}
	}

	private static class Weather {
		private String main;
		private String description;

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public String getMain() {
			return main;
		}

		public void setMain(String main) {
			this.main = main;
		}

	}

	private static class Result {
		private String city;
		private double temp;
		private double humidity;
		private String condition;
		private String description;

		public Result(String city, double temp, double humidity, String condition, String description) {
			this.city = city;
			this.temp = temp;
			this.humidity = humidity;
			this.condition = condition;
			this.description = description;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public double getTemp() {
			return temp;
		}

		public void setTemp(double temp) {
			this.temp = temp;
		}

		public double getHumidity() {
			return humidity;
		}

		public void setHumidity(double humidity) {
			this.humidity = humidity;
		}

		public String getCondition() {
			return condition;
		}

		public void setCondition(String condition) {
			this.condition = condition;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

	}

}
