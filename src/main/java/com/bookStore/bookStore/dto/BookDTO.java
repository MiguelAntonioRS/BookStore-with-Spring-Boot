package com.bookStore.bookStore.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Schema(description = "Book Data Transfer Object")
public class BookDTO {

    @Schema(description = "Unique identifier of the book", example = "1")
    private Integer id;

    @NotBlank(message = "Book name is required")
    @Schema(description = "Name of the book", example = "Clean Code")
    private String name;

    @NotBlank(message = "Author is required")
    @Schema(description = "Author of the book", example = "Robert C. Martin")
    private String author;

    @Positive(message = "Price must be positive")
    @Schema(description = "Price of the book", example = "29.99")
    private Double price;

    public BookDTO() {}

    public BookDTO(Integer id, String name, String author, Double price) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}