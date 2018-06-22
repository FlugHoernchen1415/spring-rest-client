package reichhorn.spring.restclient.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reichhorn.spring.api.model.User;
import reichhorn.spring.api.model.UserData;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    private RestTemplate restTemplate;

    public ApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getUsers(Integer limit) {

        UserData userData = restTemplate.getForObject("http://apifaketory.com/api/user?limit=" + limit, UserData.class);

        return userData.getData();
    }
}
