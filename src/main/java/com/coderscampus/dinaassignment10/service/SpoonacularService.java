package com.coderscampus.dinaassignment10.service;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


@Service
public class SpoonacularService {
	
	@Value("${spoonacular.urls.base}")
	private String urlsBase;

	@Value("${spoonacular.urls.mealplan}")
	private String urlsMealplan;

	@Value("${apiKey}")
	private String apiKey;

	public <T> ResponseEntity<?> getSpoonacularResponse(String timeFrame, Optional<String> numCalories,
			Optional<String> diet, Optional<String> exclusions, Class<T> responseClass) {

		RestTemplate rt = new RestTemplate();

		URI uri = createUri(timeFrame, numCalories, diet, exclusions);

		return rt.getForEntity(uri, responseClass);

	}
	
	public URI createUri(String timeFrame, Optional<String> numCalories, Optional<String> diet,
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
