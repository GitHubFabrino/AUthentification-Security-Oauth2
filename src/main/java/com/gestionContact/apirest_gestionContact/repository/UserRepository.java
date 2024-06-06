package com.gestionContact.apirest_gestionContact.repository;

import com.gestionContact.apirest_gestionContact.model.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <ApplicationUser, Integer>{

    Optional<ApplicationUser> findByUsername(String username);
}
