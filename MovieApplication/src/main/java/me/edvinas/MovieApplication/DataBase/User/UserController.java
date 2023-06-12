package me.edvinas.MovieApplication.DataBase.User;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/register")
    public Mono<User> registerUser(@RequestBody User user) {
        return userRepository.findByUsername(user.getUsername())
                .hasElement()
                .flatMap(usernameExists -> {
                    if (usernameExists) {
                        return Mono.error(new RuntimeException("Username already exists"));
                    } else {
                        return userRepository.save(user);
                    }
                });
    }

    @PostMapping("/login")
    public Mono<User> loginUser(@RequestBody User user) {
        return userRepository.findByUsername(user.getUsername())
                .switchIfEmpty(Mono.error(new RuntimeException("User not found")))
                .flatMap(existingUser -> {
                    if (existingUser.getPassword().equals(user.getPassword())) {
                        return Mono.just(existingUser);
                    } else {
                        return Mono.error(new RuntimeException("Invalid password"));
                    }
                });
    }
}
