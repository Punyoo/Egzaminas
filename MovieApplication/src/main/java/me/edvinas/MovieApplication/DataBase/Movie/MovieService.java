package me.edvinas.MovieApplication.DataBase.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovieService {
    private final MovieDAO movieDAO;

    @Autowired
    public MovieService(MovieDAO movieDAO) {
        this.movieDAO = movieDAO;
    }

    public Mono<Movie> createMovie(Movie movie) {
        return movieDAO.create(movie);
    }

    public Mono<Movie> findMovieById(String id) {
        return movieDAO.findById(id);
    }

    public Flux<Movie> findAllMovies() {
        return movieDAO.findAll();
    }

    public Mono<Movie> updateMovie(Movie movie) {
        return movieDAO.update(movie);
    }

    public Mono<Void> deleteMovieById(String id) {
        return movieDAO.deleteById(id);
    }
}
