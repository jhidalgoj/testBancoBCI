package com.prueba.banco.bci.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseUserGenerated {

    @Id
    @GeneratedValue(generator = "UUID")
   // @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
   // @GenericGenerator(name = "uuid", strategy = "uuid2")
    private UUID id;
    private LocalDateTime created;
    private LocalDateTime modified;
    private LocalDateTime lastLogin;
    private String token;
    private boolean isActive;
}
