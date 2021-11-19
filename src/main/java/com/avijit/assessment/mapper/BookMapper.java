package com.avijit.assessment.mapper;

import com.avijit.assessment.domain.BookDto;
import com.avijit.assessment.schema.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public BookDto entityToDomain(Book book) {
        return new BookDto()
                .setId(book.getId())
                .setName(book.getName())
                .setTotalStock(book.getTotalStock());
    }
}
