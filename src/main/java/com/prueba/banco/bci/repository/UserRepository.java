package com.prueba.banco.bci.repository;

import com.prueba.banco.bci.dto.request.RequestUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<RequestUser, UUID> {
    boolean findByEmail(String email);
}
