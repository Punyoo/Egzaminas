package me.edvinas.MovieApplication.DataBase.Movie;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @GetMapping("/")
    public Flux<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @GetMapping("/director/{director}")
    public Flux<Movie> getMoviesByDirector(@PathVariable String director) {
        return movieRepository.findByDirector(director);
    }
}
