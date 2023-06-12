package me.edvinas.MovieApplication.DataBase.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class MovieDAOImpl implements MovieDAO {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    public MovieDAOImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<Movie> create(Movie movie) {
        return reactiveMongoTemplate.insert(movie);
    }

    @Override
    public Mono<Movie> findById(String id) {
        return reactiveMongoTemplate.findById(id, Movie.class);
    }

    @Override
    public Flux<Movie> findAll() {
        return reactiveMongoTemplate.findAll(Movie.class);
    }

    @Override
    public Mono<Movie> update(Movie movie) {
        return reactiveMongoTemplate.save(movie);
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return reactiveMongoTemplate.remove(Query.query(Criteria.where("_id").is(id)), Movie.class)
                .then();
    }
}
