package com.gestionContact.apirest_gestionContact.service;

import com.gestionContact.apirest_gestionContact.dto.LoginResponseDto;
import com.gestionContact.apirest_gestionContact.model.ApplicationUser;
import com.gestionContact.apirest_gestionContact.model.Role;
import com.gestionContact.apirest_gestionContact.repository.RoleRepository;
import com.gestionContact.apirest_gestionContact.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public ApplicationUser registerUser(String username , String password){

        String encodePassword = passwordEncoder.encode(password);
        Role userRole = roleRepository.findByAuthority("USER").get();
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);
        return userRepository.save(new ApplicationUser(0, username ,encodePassword,authorities ));
    }

    public LoginResponseDto loginUser(String username , String password){
        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username , password)
            );

            String token = tokenService.generateJwt(auth);

            return new LoginResponseDto(userRepository.findByUsername(username).get() , token);
        }catch (AuthenticationException e){
            return new LoginResponseDto(null , " ");
        }
    }
}
