package com.mir00r.movieinfoservices.models;

import java.util.List;

/**
 * @author mir00r on 1/4/22
 * @project IntelliJ IDEA
 */
public class MovieItems {
    private List<MovieSummary> movieSummaries;

    public List<MovieSummary> getMovieSummaries() {
        return movieSummaries;
    }

    public void setMovieSummaries(List<MovieSummary> movieSummaries) {
        this.movieSummaries = movieSummaries;
    }
}
