package com.prueba.banco.bci.service;

import com.prueba.banco.bci.dto.request.RequestUser;
import com.prueba.banco.bci.dto.response.ResponseUserGenerated;
import com.prueba.banco.bci.repository.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Component
public class UserService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Value("${regex.email}")
    private String emailRegex;

    @Value("${regex.password}")
    private String passwordRegex;

    @Value("${jwt.secret}")
    private String jwtSecret;


    public ResponseUserGenerated registerUser(RequestUser user) throws Exception {

        log.info("emailRegex {}", emailRegex);
        log.info("passwordRegex {}", passwordRegex);
        log.info("jwtSecret {}", jwtSecret);
        log.info("user.getEmail() {}", user.getEmail());

        ResponseUserGenerated userGenerated = new ResponseUserGenerated();
        //Response Servicio

       if(!userRepository.findByEmail(user.getEmail().toString())){
           throw new Exception("El correo ya registrado");
       }

        Boolean validaCorreo = validaEmail(user.getEmail());
        log.info("validaCorreo {}", validaCorreo);
      /*  if (!user.getPassword().matches(passwordRegex)) {
            throw new Exception("Formato de contraseña inválido");
        }*/

        userGenerated.setId(UUID.randomUUID());
        userGenerated.setCreated(LocalDateTime.now());
        userGenerated.setModified(LocalDateTime.now());
        userGenerated.setLastLogin(LocalDateTime.now());
        userGenerated.setActive(true);
        userGenerated.setToken(generateJwtToken(user.getEmail()));

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userGenerated).getBody();
    }


    private String generateJwtToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 1 día
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .compact();
    }



    public static Boolean validaEmail (String email) {
        Pattern pattern = Pattern.compile("^([0-9a-zA-Z]+[-._+&])*[0-9a-zA-Z]+@([-0-9a-zA-Z]+[.])+[a-zA-Z]{2,6}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
