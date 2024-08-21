package com.example.bookstoreapi.controller;

import com.example.bookstoreapi.dto.BookDTO;
import com.example.bookstoreapi.exception.BookNotFoundException;
import com.example.bookstoreapi.mapper.BookMapper;
import com.example.bookstoreapi.model.Book;
import com.example.bookstoreapi.resource.BookResource;
import com.example.bookstoreapi.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/books")
@Validated
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @Operation(summary = "Get all books", responses = {
            @ApiResponse(responseCode = "200", description = "List of books")
    })
    @GetMapping
    public ResponseEntity<List<BookResource>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        List<BookResource> bookResources = books.stream()
                .map(book -> {
                    BookDTO dto = bookMapper.toDTO(book);
                    BookResource resource = new BookResource(dto);
                    resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(dto.getId())).withSelfRel());
                    return resource;
                }).toList();
        return ResponseEntity.ok(bookResources);
    }

    @Operation(summary = "Get a specific book by ID", responses = {
            @ApiResponse(responseCode = "200", description = "Book found"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<BookResource> getBookById(@PathVariable("id") Long id) {
        Book book = bookService.getBookById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
        BookDTO dto = bookMapper.toDTO(book);
        BookResource resource = new BookResource(dto);
        resource.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(BookController.class).getBookById(id)).withSelfRel());
        return ResponseEntity.ok(resource);
    }

    @Operation(summary = "Create a new book", responses = {
            @ApiResponse(responseCode = "201", description = "Book created"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        book = bookService.createBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookMapper.toDTO(book));
    }

    @Operation(summary = "Update a book", responses = {
            @ApiResponse(responseCode = "200", description = "Book updated"),
            @ApiResponse(responseCode = "404", description = "Book not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable("id") Long id, @Valid @RequestBody BookDTO updatedBookDTO) {
        Book updatedBook = bookMapper.toEntity(updatedBookDTO);
        updatedBook.setId(id);
        Book book = bookService.updateBook(updatedBook)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
        return ResponseEntity.ok(bookMapper.toDTO(book));
    }

    @Operation(summary = "Delete a book", responses = {
            @ApiResponse(responseCode = "204", description = "Book deleted"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") Long id) {
        if (bookService.deleteBook(id)) {
            return ResponseEntity.noContent().build();
        } else {
            throw new BookNotFoundException("Book not found");
        }
    }

    @Operation(summary = "Search books by title and author", responses = {
            @ApiResponse(responseCode = "200", description = "Filtered list of books")
    })
    @GetMapping("/search")
    public ResponseEntity<List<BookDTO>> searchBooks(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "author", required = false) String author) {

        List<Book> books = bookService.searchBooks(title, author);
        List<BookDTO> bookDTOs = books.stream()
                .map(bookMapper::toDTO)
                .toList();
        return ResponseEntity.ok(bookDTOs);
    }
}
