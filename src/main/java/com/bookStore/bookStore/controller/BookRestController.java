package com.bookStore.bookStore.controller;

import com.bookStore.bookStore.dto.ApiResponse;
import com.bookStore.bookStore.dto.BookDTO;
import com.bookStore.bookStore.entity.BookEntity;
import com.bookStore.bookStore.exception.ResourceNotFoundException;
import com.bookStore.bookStore.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/books")
@CrossOrigin(origins = "*")
@Tag(name = "Book Management", description = "APIs for managing books in the bookstore")
public class BookRestController {

    private final BookService bookService;

    public BookRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @Operation(summary = "Get all books", description = "Retrieves a list of all books in the store")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Successfully retrieved",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = BookDTO.class)))
    })
    @GetMapping
    public ResponseEntity<ApiResponse<List<BookDTO>>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBook().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(books));
    }

    @Operation(summary = "Get book by ID", description = "Retrieves a specific book by its ID")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Book found"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Book not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookDTO>> getBookById(
            @Parameter(description = "ID of the book to retrieve") @PathVariable int id) {
        BookEntity book = bookService.getBookById(id);
        return ResponseEntity.ok(ApiResponse.success(convertToDTO(book)));
    }

    @Operation(summary = "Create a new book", description = "Adds a new book to the store")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "201", description = "Book created successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<ApiResponse<BookDTO>> createBook(@Valid @RequestBody BookDTO bookDTO) {
        BookEntity book = convertToEntity(bookDTO);
        bookService.save(book);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Book created successfully", convertToDTO(book)));
    }

    @Operation(summary = "Update a book", description = "Updates an existing book by ID")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Book updated successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Book not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BookDTO>> updateBook(
            @Parameter(description = "ID of the book to update") @PathVariable int id,
            @Valid @RequestBody BookDTO bookDTO) {
        if (bookService.getBookById(id) == null) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        BookEntity book = convertToEntity(bookDTO);
        book.setId(id);
        bookService.save(book);
        return ResponseEntity.ok(ApiResponse.success("Book updated successfully", convertToDTO(book)));
    }

    @Operation(summary = "Delete a book", description = "Removes a book from the store by ID")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Book deleted successfully"),
        @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "404", description = "Book not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(
            @Parameter(description = "ID of the book to delete") @PathVariable int id) {
        bookService.deleteById(id);
        return ResponseEntity.ok(ApiResponse.success("Book deleted successfully", null));
    }

    @Operation(summary = "Search books by author", description = "Finds books by author name")
    @GetMapping("/search/author")
    public ResponseEntity<ApiResponse<List<BookDTO>>> searchByAuthor(
            @Parameter(description = "Author name to search") @RequestParam String author) {
        List<BookDTO> books = bookService.getAllBook().stream()
                .filter(b -> b.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(books));
    }

    @Operation(summary = "Search books by name", description = "Finds books by name/title")
    @GetMapping("/search/name")
    public ResponseEntity<ApiResponse<List<BookDTO>>> searchByName(
            @Parameter(description = "Book name to search") @RequestParam String name) {
        List<BookDTO> books = bookService.getAllBook().stream()
                .filter(b -> b.getName().toLowerCase().contains(name.toLowerCase()))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success(books));
    }

    private BookDTO convertToDTO(BookEntity book) {
        return new BookDTO(book.getId(), book.getName(), book.getAuthor(), book.getPrice());
    }

    private BookEntity convertToEntity(BookDTO bookDTO) {
        return new BookEntity(bookDTO.getId(), bookDTO.getName(), bookDTO.getAuthor(), 
                bookDTO.getPrice() != null ? String.valueOf(bookDTO.getPrice()) : "0");
    }
}