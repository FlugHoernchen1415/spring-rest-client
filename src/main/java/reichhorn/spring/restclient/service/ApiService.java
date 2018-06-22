package reichhorn.spring.restclient.service;

import reichhorn.spring.api.model.User;

import java.util.List;

public interface ApiService {

    List<User> getUsers(Integer limit);
}
