package com.gestionContact.apirest_gestionContact.service;

import com.gestionContact.apirest_gestionContact.model.ApplicationUser;
import com.gestionContact.apirest_gestionContact.model.Role;
import com.gestionContact.apirest_gestionContact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In the user details");
        /*if (!username.equals("fab")) throw new UsernameNotFoundException("Not fab");

        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1, "USER"));

        return new ApplicationUser(1, "fab", encoder.encode("123"), roles);*/
        return  userRepository.findByUsername(username).orElseThrow(()-> new RuntimeException( "user not found"));
    }
}
