package me.edvinas.MovieApplication.DataBase.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class UserDAOImpl implements UserDAO {
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    public UserDAOImpl(ReactiveMongoTemplate reactiveMongoTemplate) {
        this.reactiveMongoTemplate = reactiveMongoTemplate;
    }

    @Override
    public Mono<User> createUser(User user) {
        return reactiveMongoTemplate.insert(user);
    }

    @Override
    public Mono<User> findUserByUsername(String username) {
        return reactiveMongoTemplate.findOne(Query.query(Criteria.where("username").is(username)), User.class);
    }
}
