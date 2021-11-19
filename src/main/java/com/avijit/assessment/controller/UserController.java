package com.avijit.assessment.controller;

import com.avijit.assessment.domain.UserDto;
import com.avijit.assessment.service.UserService;
import com.avijit.assessment.util.LogWriter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৮/১১/২১
 */
@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        userService.createUser(userDto);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        LogWriter.printRequestHeader("controller");
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<UserDto>> getAllUser() {
        return ResponseEntity.ok(userService.getAllUser());
    }
}
