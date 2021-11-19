package com.avijit.assessment.service;

import com.avijit.assessment.domain.BookDto;
import com.avijit.assessment.exception.ResourceNotFoundException;
import com.avijit.assessment.reposiroty.BookRepository;
import com.avijit.assessment.schema.Book;
import com.avijit.assessment.util.BaseService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * Responsibility:
 *
 * @author Avijit Barua
 * @since ১৭/১১/২১
 */
@Service
public class BookService extends BaseService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void createNewBook(BookDto bookDto) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDto, book, "id");
        bookRepository.save(book);
    }

    public BookDto findById(Long bookId) {
        return bookRepository.findById(bookId).map(entity -> {
            BookDto domain = new BookDto();
            BeanUtils.copyProperties(entity, domain);
            return domain;
        }).orElseThrow(() -> new ResourceNotFoundException(getMessage("book.not.found")));
    }
}
