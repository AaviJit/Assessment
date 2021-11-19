package com.avijit.assessment.service;

import com.avijit.assessment.domain.BookDto;
import com.avijit.assessment.domain.IssueBookRequest;
import com.avijit.assessment.domain.UserDto;
import com.avijit.assessment.domain.UsersBookResponse;
import com.avijit.assessment.exception.ResourceNotFoundException;
import com.avijit.assessment.mapper.BookMapper;
import com.avijit.assessment.reposiroty.BookRepository;
import com.avijit.assessment.reposiroty.UserBookHistoryRepository;
import com.avijit.assessment.reposiroty.UserRepository;
import com.avijit.assessment.schema.Book;
import com.avijit.assessment.schema.User;
import com.avijit.assessment.schema.UserBookHistory;
import com.avijit.assessment.util.BaseService;
import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৭/১১/২১
 */
@Service
@Slf4j
public class UserBookHistoryService extends BaseService {

    private final UserBookHistoryRepository userBookHistoryRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final UserService userService;
    private final BookMapper bookMapper;
    private final BookService bookService;
    private final MailSenderService mailSenderService;

    public UserBookHistoryService(UserBookHistoryRepository userBookHistoryRepository,
                                  UserRepository userRepository,
                                  BookRepository bookRepository, UserService userService, BookMapper bookMapper, BookService bookService, MailSenderService mailSenderService) {
        this.userBookHistoryRepository = userBookHistoryRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.userService = userService;
        this.bookMapper = bookMapper;
        this.bookService = bookService;
        this.mailSenderService = mailSenderService;
    }

    public void issueBook(IssueBookRequest issueBookRequest) {
        User user = userRepository.findById(issueBookRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(getMessage("user.not.found")));

        Book book = bookRepository.findById(issueBookRequest.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException(getMessage("book.not.found")));

        if (userBookHistoryRepository.findByUserIdAndHasReturned(issueBookRequest.getUserId(), false).size() >= user.getMaximumBookAccess())
            throw new RuntimeException(getMessage("user.cannot.issue.book"));

        if (userBookHistoryRepository.findByBookIdAndHasReturned(issueBookRequest.getBookId(), false).size() >= book.getTotalStock())
            throw new RuntimeException(getMessage("book.stock.out"));

        UserBookHistory history = new UserBookHistory()
                .setBookId(issueBookRequest.getBookId())
                .setUserId(issueBookRequest.getUserId())
                .setIssueTime(OffsetDateTime.now())
                .setHasReturned(false);
        userBookHistoryRepository.save(history);
        try {
            mailSenderService.sendBookIssueMail(book, user);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public List<UsersBookResponse> getBooksThatUserHolding(Long userId) {
        List<UsersBookResponse> usersBooks = new ArrayList<>();
        UserDto user = userService.getUserById(userId);
        List<UserBookHistory> bookHistories = userBookHistoryRepository.findByBookIdAndHasReturned(userId, false);
        for (UserBookHistory history : bookHistories) {
            BookDto book = bookRepository.findById(history.getBookId()).map(bookMapper::entityToDomain).orElse(null);
            if (Objects.nonNull(book)) {
                usersBooks.add(new UsersBookResponse(user, book, history.getIssueTime()));
            }
        }
        return usersBooks;
    }


    public List<UsersBookResponse> getUserThatHoldingBook(Long bookId) throws NotFoundException {
        List<UsersBookResponse> usersBooks = new ArrayList<>();
        BookDto book = bookService.findById(bookId);
        List<UserBookHistory> bookHistories = userBookHistoryRepository.findByBookIdAndHasReturned(bookId, false);
        for (UserBookHistory history : bookHistories) {
            try {
                UserDto user = userService.getUserById(history.getUserId());
                usersBooks.add(new UsersBookResponse(user, book, history.getIssueTime()));
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        }
        return usersBooks;
    }
}
