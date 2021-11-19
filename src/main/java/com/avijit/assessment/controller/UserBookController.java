package com.avijit.assessment.controller;

import com.avijit.assessment.domain.IssueBookRequest;
import com.avijit.assessment.domain.UsersBookResponse;
import com.avijit.assessment.service.UserBookHistoryService;
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
@RequestMapping("/api/user-book")
public class UserBookController {


    private final UserBookHistoryService userBookHistoryService;

    public UserBookController(UserBookHistoryService userBookHistoryService) {
        this.userBookHistoryService = userBookHistoryService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Void> issueBook(@RequestBody IssueBookRequest request) {
        userBookHistoryService.issueBook(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/user/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<List<UsersBookResponse>> getIssuedBookOfUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userBookHistoryService.getBooksThatUserHolding(userId));
    }


    @GetMapping(value = "/book/{bookId}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public ResponseEntity<List<UsersBookResponse>> getAllUserHoldingBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(userBookHistoryService.getBooksThatUserHolding(bookId));
    }
}
