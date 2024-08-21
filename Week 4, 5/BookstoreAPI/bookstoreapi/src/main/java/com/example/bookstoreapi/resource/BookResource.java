package com.example.bookstoreapi.resource;

import com.example.bookstoreapi.dto.BookDTO;
import org.springframework.hateoas.RepresentationModel;

public class BookResource extends RepresentationModel<BookResource> {
    private BookDTO bookDTO;

    public BookResource(BookDTO bookDTO) {
        this.bookDTO = bookDTO;
    }

    public BookDTO getBookDTO() {
        return bookDTO;
    }
}
