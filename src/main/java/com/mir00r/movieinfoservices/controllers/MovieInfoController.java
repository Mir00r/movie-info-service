package com.mir00r.movieinfoservices.controllers;

import com.mir00r.movieinfoservices.models.Movie;
import com.mir00r.movieinfoservices.models.MovieItems;
import com.mir00r.movieinfoservices.models.MovieSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mir00r on 1/4/22
 * @project IntelliJ IDEA
 */
@RestController
@RequestMapping("/movies")
public class MovieInfoController {

    @Value("${api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    private String API_URL = "https://imdb-api.com/en/API/Top250Movies/";

    @RequestMapping("/{movieId}")
    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
        MovieSummary movieSummary = restTemplate.getForObject(API_URL + "?api_key=" + apiKey, MovieSummary.class);
        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
    }

    @GetMapping("")
    public List<MovieSummary> getMovies() {
        MovieItems movieItems = restTemplate.getForObject(API_URL + apiKey, MovieItems.class);
        if (movieItems == null) return new ArrayList<>();
        return movieItems.getMovieSummaries().stream()
                .map(item -> new MovieSummary(item.getId(), item.getRank(), item.getTitle(),
                        item.getFullTitle(), item.getYear(), item.getImage(), item.getCrew(),
                        item.getImDbRating(), item.getImDbRatingCount(), item.getOverview()))
                .collect(Collectors.toList());
    }

}
