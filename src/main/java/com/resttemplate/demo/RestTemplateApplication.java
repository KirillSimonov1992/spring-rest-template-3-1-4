package com.resttemplate.demo;

import com.resttemplate.demo.model.User;
import com.resttemplate.demo.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

@SpringBootApplication()
public class RestTemplateApplication {


    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(RestTemplateApplication.class, args);

        UserService userService = applicationContext.getBean("userServiceImpl", UserService.class);
        StringBuffer sb = new StringBuffer("");

        User user = new User(3L, "James", "Brown", (byte) 21);

        ResponseEntity<String> responseEntity = userService.getUsers();
        String cookie =  responseEntity.getHeaders()
                                         .getFirst(HttpHeaders.SET_COOKIE);


        responseEntity = userService.createUser(user, cookie);
        sb.append(responseEntity.getBody());
        System.out.println(sb);

        user.setName("Thomas");
        user.setLastName("Shelby");

        responseEntity = userService.updateUser(user, cookie);
        sb.append(responseEntity.getBody());
        System.out.println(sb);

        responseEntity = userService.deleteUser(user.getId(), cookie);
        sb.append(responseEntity.getBody());
        System.out.println(sb);
        System.out.println(sb.length());
    }

}
