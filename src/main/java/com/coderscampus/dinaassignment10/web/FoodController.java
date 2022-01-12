package com.coderscampus.dinaassignment10.web;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.coderscampus.dinaassignment10.dto.DayResponse;
import com.coderscampus.dinaassignment10.dto.WeekResponse;


@RestController
public class FoodController {

	@Value("${spoonacular.urls.base}")
	private String urlsBase;

	@Value("${spoonacular.urls.mealplan}")
	private String urlsMealplan;

	@Value("${apiKey}")
	private String apiKey;

	@GetMapping("mealplanner/week")
	public ResponseEntity<WeekResponse> getWeekMeals(Optional<String> numCalories, Optional<String> diet,
			Optional<String> exclusions) {

		RestTemplate rt = new RestTemplate();

		URI uri = createUri("week", numCalories, diet, exclusions);

		ResponseEntity<WeekResponse> response = rt.getForEntity(uri, WeekResponse.class);

		return response;
	}

	@GetMapping("mealplanner/day")
	public ResponseEntity<DayResponse> getDayMeals(Optional<String> numCalories, Optional<String> diet,
			Optional<String> exclusions) {

		RestTemplate rt = new RestTemplate();

		URI uri = createUri("day", numCalories, diet, exclusions);

		ResponseEntity<DayResponse> response = rt.getForEntity(uri, DayResponse.class);

		return response;
	}

	private URI createUri(String timeFrame, Optional<String> numCalories, Optional<String> diet,
			Optional<String> exclusions) {
		URI uri = UriComponentsBuilder.fromHttpUrl(urlsBase)
				                      .path(urlsMealplan)
				                      .queryParam("timeFrame", timeFrame)
				                      .queryParamIfPresent("targetCalories", numCalories)
				                      .queryParamIfPresent("diet", diet)
				                      .queryParamIfPresent("exclude", exclusions)
				                      .queryParam("apiKey", apiKey)
				                      .build()
				                      .toUri();
		return uri;
	}

}
