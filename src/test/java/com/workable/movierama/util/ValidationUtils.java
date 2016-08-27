package com.workable.movierama.util;

import static org.testng.Assert.assertNotNull;
import static org.testng.AssertJUnit.fail;

import com.workable.movierama.model.Movie;

public class ValidationUtils {
    public static final String INTEGER_PATTERN = "\\d+";
    public static final String YEAR_PATTERN = "\\s*\\d{4}.*";

    public static boolean isInteger(String numberStr) {
        if (numberStr.matches(INTEGER_PATTERN)) {
            return true;
        }
        return false;
    }

    public static boolean isYear(String yearStr) {
        if (yearStr.matches(ValidationUtils.YEAR_PATTERN)) {
            return true;
        }
        return false;
    }

    public static void validateMovie(Movie movie) {
        try {
            assertNotNull(movie.getTitle(), "Title in movie " + movie + " was null.");
            assertNotNull(movie.getDescription(), "Description in movie " + movie + " was null.");
            assertNotNull(movie.getReview(), "Review in movie " + movie + " was null.");
            assertNotNull(movie.getStarringActorsStr(), "Starring Actors in movie " + movie + " were null.");
            if (!ValidationUtils.isInteger(movie.getReview())) {
                throw new AssertionError("Reviews' field is not an integer.");
            }
            if (!ValidationUtils.isYear(movie.getStarringActorsStr())) {
                throw new AssertionError("Year is not shown in element " + movie.getStarringActorsStr());
            }
            if (movie.getActors().size() > 5) {
                throw new AssertionError("Starring Actors are more than 5 in element " + movie.getStarringActorsStr());
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}
