# Book Store Management System

This project is a web application designed to manage a book store. It allows users to register, list, edit, and delete books, as well as manage a personal list of books. The application is built using **Spring Boot** for the backend and **Thymeleaf** for the frontend.

## Project Structure

### 1. **Controllers**
- **`BookController`**: Handles CRUD operations for available books.
- **`MyBookListController`**: Manages the user's personal list of books.

### 2. **Services**
- **`BookService`**: Provides business logic for book management.
- **`MyBookListService`**: Provides business logic for managing the user's personal book list.

### 3. **Repositories**
- **`BookRepository`**: Interface for book data persistence.
- **`MyBookRepository`**: Interface for personal book list data persistence.

### 4. **Entities**
- **`BookEntity`**: Represents a book in the database.
- **`MyBookListEntity`**: Represents a book in the user's personal list.

### 5. **Views (HTML Templates)**
- **`bookRegister.html`**: Form to register a new book.
- **`bookList.html`**: View to list all available books.
- **`myBooks.html`**: View to list books in the user's personal collection.
- **`bookEdit.html`**: Form to edit an existing book.

### 6. **Main Configuration**
- **`BookStoreApplication`**: Main Spring Boot configuration class.

## How to Run the Project

### Prerequisites
- Java 17 or higher
- Maven
- MySQL (or another compatible database system)

### Clone the Repository

```bash
git clone https://github.com/MiguelAntonioRS/BookStore-with-Spring-Boot.git
```

### Database Setup

Create a MySQL database named bookstore.

Configure the database credentials in the file src/main/resources/application.properties:

    spring.datasource.url=jdbc:mysql://localhost:3306/bookstore
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    spring.jpa.hibernate.ddl-auto=update

### Build and Run the Application

   1. Build the project:
      ```bash
      mvn clean install
      ```
   2. Run the application: 
      ```bash
      mvn spring-boot:run
      ```   

The application will be accessible at http://localhost:8080
