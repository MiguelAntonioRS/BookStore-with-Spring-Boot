package com.bookStore.bookStore.controller;

import com.bookStore.bookStore.dto.BookDTO;
import com.bookStore.bookStore.entity.BookEntity;
import com.bookStore.bookStore.exception.GlobalExceptionHandler;
import com.bookStore.bookStore.exception.ResourceNotFoundException;
import com.bookStore.bookStore.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BookRestControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookRestController bookRestController;

    private ObjectMapper objectMapper;
    private BookEntity testBook;
    private BookDTO testBookDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookRestController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
        objectMapper = new ObjectMapper();
        testBook = new BookEntity(1, "Clean Code", "Robert C. Martin", 29.99);
        testBookDTO = new BookDTO(1, "Clean Code", "Robert C. Martin", 29.99);
    }

    @Test
    @DisplayName("GET /api/v1/books - Should return all books")
    void getAllBooks_Success() throws Exception {
        List<BookEntity> books = Arrays.asList(testBook);
        when(bookService.getAllBook()).thenReturn(books);

        mockMvc.perform(get("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data").isArray());
    }

    @Test
    @DisplayName("GET /api/v1/books/{id} - Should return book by ID")
    void getBookById_Success() throws Exception {
        when(bookService.getBookById(1)).thenReturn(testBook);

        mockMvc.perform(get("/api/v1/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("Clean Code"));
    }

    @Test
    @DisplayName("GET /api/v1/books/{id} - Should return 404 when book not found")
    void getBookById_NotFound() throws Exception {
        when(bookService.getBookById(999)).thenThrow(new ResourceNotFoundException("Book not found"));

        mockMvc.perform(get("/api/v1/books/999")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.success").value(false));
    }

    @Test
    @DisplayName("POST /api/v1/books - Should create new book")
    void createBook_Success() throws Exception {
        when(bookService.save(any(BookEntity.class))).thenReturn(testBook);

        mockMvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBookDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.data.name").value("Clean Code"));
    }

    @Test
    @DisplayName("POST /api/v1/books - Should return 400 for invalid input")
    void createBook_ValidationError() throws Exception {
        BookDTO invalidDTO = new BookDTO();

        mockMvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("DELETE /api/v1/books/{id} - Should delete book")
    void deleteBook_Success() throws Exception {
        mockMvc.perform(delete("/api/v1/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").value(true));
    }
}