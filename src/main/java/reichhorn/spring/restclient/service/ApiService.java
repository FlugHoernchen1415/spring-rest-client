package reichhorn.spring.restclient.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reichhorn.spring.api.model.User;

import java.util.List;

public interface ApiService {

    List<User> getUsers(Integer limit);

    Flux<User> getUsers(Mono<Integer> limit);
}
