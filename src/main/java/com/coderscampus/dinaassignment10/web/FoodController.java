package com.coderscampus.dinaassignment10.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderscampus.dinaassignment10.dto.DayResponse;
import com.coderscampus.dinaassignment10.dto.WeekResponse;
import com.coderscampus.dinaassignment10.service.SpoonacularService;

@RestController
public class FoodController {

	@Autowired
	private SpoonacularService spoonacularService;

	@GetMapping("mealplanner/week")
	public ResponseEntity<?> getWeekMeals(Optional<String> numCalories, Optional<String> diet,
			Optional<String> exclusions) {

		return spoonacularService.getSpoonacularResponse("week", numCalories, diet, exclusions, WeekResponse.class);
	}

	@GetMapping("mealplanner/day")
	public ResponseEntity<?> getDayMeals(Optional<String> numCalories, Optional<String> diet,
			Optional<String> exclusions) {

		return spoonacularService.getSpoonacularResponse("day", numCalories, diet, exclusions, DayResponse.class);
	}

}
