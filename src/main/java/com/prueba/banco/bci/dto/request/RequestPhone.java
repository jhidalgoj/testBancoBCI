package com.prueba.banco.bci.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="PHONE")
public class RequestPhone {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id_phone;

    private String number;
    private String citycode;
    private String contrycode;
}
