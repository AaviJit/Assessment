package com.avijit.assessment.util;

import com.avijit.assessment.reposiroty.RoleRepository;
import com.avijit.assessment.reposiroty.UserRepository;
import com.avijit.assessment.schema.Role;
import com.avijit.assessment.schema.User;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (roleRepository.findByName("USER").isEmpty())
            roleRepository.save(new Role().setName("USER"));

        if (roleRepository.findByName("ADMIN").isEmpty())
            roleRepository.save(new Role().setName("ADMIN"));


        if (userRepository.findByUsername("admin").isEmpty()) {
            User user = new User();
            user.setUsername("admin")
                    .setFirstName("Avijit")
                    .setLastName("Barua")
                    .setEmail("avijit_barua@hotmail.com")
                    .setPassword(passwordEncoder.encode("admin"));
            Role roleEntity = roleRepository.findByName("ADMIN").get();
            Set<Role> roles = new HashSet<>();
            roles.add(roleEntity);
            user.setRoles(roles);
            userRepository.save(user);
        }


        if (userRepository.findByUsername("user").isEmpty()) {
            User user = new User();
            user.setUsername("user")
                    .setFirstName("Avijit")
                    .setLastName("Barua")
                    .setEmail("avijit_barua@hotmail.com")
                    .setPassword(passwordEncoder.encode("user"));
            Role roleEntity = roleRepository.findByName("USER").get();
            Set<Role> roles = new HashSet<>();
            roles.add(roleEntity);
            user.setRoles(roles);
            userRepository.save(user);
        }
    }

}