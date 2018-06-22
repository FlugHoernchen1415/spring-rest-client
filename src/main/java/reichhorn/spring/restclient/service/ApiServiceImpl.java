package reichhorn.spring.restclient.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reichhorn.spring.api.model.User;
import reichhorn.spring.api.model.UserData;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    private RestTemplate restTemplate;

    // api url is externalised in the application.properties file
    private final String API_URL;

    public ApiServiceImpl(RestTemplate restTemplate, @Value("${api.url}") String API_URL) {
        this.restTemplate = restTemplate;
        this.API_URL = API_URL;
    }

    @Override
    public List<User> getUsers(Integer limit) {

        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromUriString(API_URL)
                .queryParam("limit", limit);

        UserData userData = restTemplate.getForObject(uriBuilder.toUriString(), UserData.class);

        return userData.getData();
    }

    @Override
    public Flux<User> getUsers(Mono<Integer> limit) {

        return WebClient
                .create(API_URL)
                .get()
                .uri(uriBuilder -> uriBuilder.queryParam("limit", limit.block()).build())
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .flatMap(response -> response.bodyToMono(UserData.class))
                .flatMapIterable(UserData::getData);
    }
}
