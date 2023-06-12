package me.edvinas.MovieApplication.DataBase.User;

import reactor.core.publisher.Mono;

public interface UserDAO {
    Mono<User> createUser(User user);
    Mono<User> findUserByUsername(String username);
}
