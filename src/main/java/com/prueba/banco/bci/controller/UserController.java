package com.prueba.banco.bci.controller;

import com.prueba.banco.bci.dto.request.RequestUser;
import com.prueba.banco.bci.dto.response.ResponseUserGenerated;
import com.prueba.banco.bci.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


@RestController
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @PostMapping("/prueba/registro")
     public ResponseEntity<?> registrarUsuario(@RequestBody RequestUser users) throws Exception {

        log.info("String Ingresado {}",users);
        try {
            ResponseUserGenerated response = userService.registerUser(users);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("mensaje", e.getMessage()));
        }

    }

}
