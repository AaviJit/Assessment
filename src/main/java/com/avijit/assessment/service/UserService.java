package com.avijit.assessment.service;

import com.avijit.assessment.domain.UserDto;
import com.avijit.assessment.exception.ResourceNotFoundException;
import com.avijit.assessment.mapper.UserMapper;
import com.avijit.assessment.reposiroty.UserRepository;
import com.avijit.assessment.schema.User;
import com.avijit.assessment.util.BaseService;
import com.avijit.assessment.util.LogWriter;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৭/১১/২১
 */
@Service
@Slf4j
public class UserService extends BaseService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void createUser(UserDto userDto) {
        User user = userMapper.domainToEntity(userDto);
        userRepository.save(user);
    }

    public void updateUser(UserDto dto) {
        User user = userMapper.domainToEntity(dto);
        userRepository.save(user);
    }

    public UserDto getUserById(Long id){
        LogWriter.printRequestHeader("service");
        return userRepository.findById(id)
                .map(userMapper::entityToDomain)
                .orElseThrow(() -> new ResourceNotFoundException(getMessage("user.not.found")));
    }

    public List<UserDto> getAllUser() {
        return userRepository.findAll().stream().map(userMapper::entityToDomain).collect(Collectors.toList());
    }
}
