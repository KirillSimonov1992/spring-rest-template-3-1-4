package com.resttemplate.demo.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.resttemplate.demo.model.User;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    private static final String URL_USERS = "http://91.241.64.178:7081/api/users";

    @Override
    public ResponseEntity<String> getUsers() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        // HttpEntity<String>: To get result as String.
        HttpEntity<String> requestBody = new HttpEntity<>(headers);

        // Send request with GET method, and Headers.
        ResponseEntity<String> response = new RestTemplate().exchange(URL_USERS,
                HttpMethod.GET, requestBody, String.class);

        return response;
    }

    @Override
    public ResponseEntity<String> createUser(User user, String cookie) {
        HttpHeaders headers = new HttpHeaders();
        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", cookie);

        // Data attached to the request.
        HttpEntity<String> requestBody = new HttpEntity<>(parseStringInJson(user), headers);

        // Send request with POST method.
        return new RestTemplate().postForEntity(URL_USERS, requestBody, String.class);
    }

    @Override
    public ResponseEntity<String> updateUser(User user, String cookie) {

        HttpHeaders headers = new HttpHeaders();

        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", cookie);

        // Data attached to the request.
        HttpEntity<String> requestBody = new HttpEntity<>(parseStringInJson(user), headers);

        // Send request with PUT method.
        return new RestTemplate().exchange(URL_USERS,
                HttpMethod.PUT, requestBody, String.class);
    }

    @Override
    public ResponseEntity<String> deleteUser(Long id, String cookie) {
        HttpHeaders headers = new HttpHeaders();

        // Request to return JSON format
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", cookie);

        String url = URL_USERS + "/" + id;

        // Send request with DELETE method.
        return new RestTemplate().exchange(url, HttpMethod.DELETE,
                new HttpEntity<>("", headers), String.class);
    }

    private String parseStringInJson(User user) {
        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }


}
