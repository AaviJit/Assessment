package com.avijit.assessment.mapper;

import com.avijit.assessment.domain.UserDto;
import com.avijit.assessment.reposiroty.RoleRepository;
import com.avijit.assessment.reposiroty.UserRepository;
import com.avijit.assessment.schema.BaseEntity;
import com.avijit.assessment.schema.Role;
import com.avijit.assessment.schema.User;
import com.avijit.assessment.util.BaseService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৭/১১/২১
 */
@Service
public class UserMapper extends BaseService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserMapper(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User domainToEntity(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElse(new User());
        user.setUsername(userDto.getUserName())
                .setFirstName(userDto.getFirstName())
                .setLastName(userDto.getLastName())
                .setEmail(userDto.getEmail())
                .setPhone(userDto.getPhone())
                .setMaximumBookAccess(userDto.getMaximumBookAccess())
                .setPassword(Objects.isNull(user.getPassword()) ?
                        passwordEncoder.encode(userDto.getPassword()) : user.getPassword());
        Set<Role> roleSet = userDto.getRoles().stream()
                .map(r -> roleRepository.findById(r).get()).collect(Collectors.toSet());
        user.setRoles(roleSet);
        return user;
    }

    public UserDto entityToDomain(User user) {
        UserDto userDto = new UserDto()
                .setId(user.getId())
                .setUserName(user.getUsername())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setEmail(user.getEmail())
                .setPhone(user.getPhone())
                .setMaximumBookAccess(user.getMaximumBookAccess())
                .setRoles(user.getRoles().stream().map(BaseEntity::getId).collect(Collectors.toSet()));
        return userDto;
    }
}
