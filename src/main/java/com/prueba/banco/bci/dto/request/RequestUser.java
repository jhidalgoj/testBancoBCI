package com.prueba.banco.bci.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="USUARIO")
public class RequestUser {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    private String name;

   // @Column(unique = true)
    private String email;

    private String password;

   // @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<RequestPhone> phones;

}
