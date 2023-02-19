package lordgarrish.movie.api.controller;

import lordgarrish.movie.api.annotation.LogMovieAdding;
import lordgarrish.movie.api.annotation.LogReadingAttempt;
import lordgarrish.movie.api.model.Movie;
import lordgarrish.movie.api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@RestController
@RequestMapping(path="/movie-api", produces="application/json")
@Validated
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @LogReadingAttempt
    @GetMapping
    public Page<Movie> getAllMovies(@RequestParam(required = false) String title,
                                    @RequestParam(required = false) String type,
                                    @RequestParam(required = false) Integer year,
                                    @PageableDefault Pageable pageable) {

        return movieService.getAllByParams(title, type, year, pageable);
    }

    @LogMovieAdding
    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public List<Movie> addMovies(@Valid @RequestBody List<Movie> movies) {
        return movieService.add(movies);
    }
}
