# 📘 Book Store Management System

A simple and intuitive web application built with **Spring Boot** and **Thymeleaf** for managing a bookstore. Users can register, view, edit, and delete books, as well as maintain a personal list of favorite or owned books.

---

## 🧩 Features

- ✅ **CRUD operations** for books.
- 📚 Manage a **personal book list**.
- 🛠️ Built using **Spring Boot** (backend) and **Thymeleaf** (frontend).
- 🗄️ Supports **MySQL** or any compatible database.
- 💡 Easy to set up and run locally.

--- 

## 🏗️ Project Architecture

### 🔧 Controllers
- `BookController`: Handles requests for managing available books.
- `MyBookListController`: Manages the user's personal book list.

### 🧠 Services
- `BookService`: Business logic for book operations.
- `MyBookListService`: Business logic for personal book list operations.

### 🗃️ Repositories
- `BookRepository`: Interface for interacting with the books table.
- `MyBookRepository`: Interface for interacting with the personal book list table.

### 📦 Entities
- `BookEntity`: Represents a book in the database.
- `MyBookListEntity`: Represents a book in the user’s personal collection.

### 🖼️ Views (HTML Templates)
- `bookRegister.html`: Form to add a new book.
- `bookList.html`: Displays all available books.
- `myBooks.html`: Displays the user's personal book list.
- `bookEdit.html`: Form to edit an existing book.

### ⚙️ Configuration
- `BookStoreApplication.java`: Main Spring Boot configuration class.

---

## 🚀 How to Run the Project

### Prerequisites
- Java 17 or higher
- Maven
- MySQL (or another compatible database)

### Clone the Repository
```bash
git clone https://github.com/MiguelAntonioRS/BookStore-with-Spring-Boot.git 
cd BookStore-with-Spring-Boot
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
