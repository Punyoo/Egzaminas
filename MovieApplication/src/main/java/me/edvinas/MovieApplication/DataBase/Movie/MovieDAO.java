package me.edvinas.MovieApplication.DataBase.Movie;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MovieDAO {
    Mono<Movie> create(Movie movie);
    Mono<Movie> findById(String id);
    Flux<Movie> findAll();
    Mono<Movie> update(Movie movie);
    Mono<Void> deleteById(String id);
}
