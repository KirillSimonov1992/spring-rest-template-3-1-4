package com.resttemplate.demo.service;

import com.resttemplate.demo.model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    void getUsers() {
        ResponseEntity<String> responseEntity = userService.getUsers();
        int statusCode = responseEntity.getStatusCodeValue();
        assertThat(x, is(not(4)));(statusCode, 200, "Получение юзеров не успешно! Код ответа " + statusCode + ". Ожидалось 200" );
//        assertNotEquals(statusCode, 200, "Получение юзеров не успешно! Код ответа " + statusCode + ". Ожидалось 200" );
    }

    @Test
    void createUser() {
        ResponseEntity<String> responseEntity = userService.getUsers();
        String cookie =  responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);

        User user = new User(3L, "James", "Brown", (byte) 21);
        responseEntity = userService.createUser(user, cookie);
        int statusCode = responseEntity.getStatusCodeValue();
        assertNotEquals(statusCode, 200, "Создание нового юзера не успешно! Код ответа " + statusCode + ". Ожидалось 200" );
    }

    @Test
    void updateUser() {
        ResponseEntity<String> responseEntity = userService.getUsers();
        String cookie =  responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);

        User user = new User(3L, "James", "Brown", (byte) 21);
        userService.createUser(user, cookie);

        user.setName("Thomas");
        user.setLastName("Shelby");
        responseEntity = userService.updateUser(user, cookie);
        int statusCode = responseEntity.getStatusCodeValue();

        assertNotEquals(statusCode, 200, "Обновление юзера не успешно! Код ответа " + statusCode + ". Ожидалось 200" );
    }

    @Test
    void deleteUser() {
        ResponseEntity<String> responseEntity = userService.getUsers();
        String cookie =  responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);

        User user = new User(3L, "James", "Brown", (byte) 21);
        userService.createUser(user, cookie);

        user.setName("Thomas");
        user.setLastName("Shelby");
        userService.updateUser(user, cookie);

        responseEntity = userService.deleteUser(user.getId(), cookie);
        int statusCode = responseEntity.getStatusCodeValue();
        assertNotEquals(statusCode, 200, "Удаление юзера не успешно! Код ответа " + statusCode + ". Ожидалось 200" );
    }
}