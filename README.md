# Sistema de Gestión de Libros

Este proyecto es una aplicación web para gestionar una tienda de libros. Permite registrar, listar, editar y eliminar libros, así como gestionar una lista personal de libros del usuario. La aplicación está construida utilizando Spring Boot para el backend y Thymeleaf para el frontend. 

## Estructura del Proyecto 

### 1. **Controladores**
- **`BookController`**: Maneja las operaciones CRUD para los libros disponibles.
- **`MyBookListController`**: Gestiona la lista de libros personales del usuario.

### 2. **Servicios**
- **`BookService`**: Proporciona la lógica de negocio para los libros.
- **`MyBookListService`**: Proporciona la lógica de negocio para la lista de libros del usuario.

### 3. **Repositorios**
- **`BookRepository`**: Interfaz para la persistencia de datos de libros.
- **`MyBookRepository`**: Interfaz para la persistencia de datos de la lista de libros del usuario.

### 4. **Entidades**
- **`BookEntity`**: Representa un libro en la base de datos.
- **`MyBookListEntity`**: Representa un libro en la lista personal del usuario.

### 5. **Vistas**
- **`bookRegister.html`**: Formulario para registrar un nuevo libro.
- **`bookList.html`**: Vista para listar todos los libros disponibles.
- **`myBooks.html`**: Vista para listar los libros en la lista personal del usuario.
- **`bookEdit.html`**: Formulario para editar un libro existente.

### 6. **Configuración Principal**
- **`BookStoreApplication`**: Configuración principal de la aplicación Spring Boot.

## Instrucciones para Ejecutar el Proyecto

### Requisitos Previos
- Java 17 o superior
- Maven
- MySQL (u otro sistema de gestión de bases de datos compatible)

### Clonar el Repositorio

```bash
git clone https://github.com/MiguelAntonioRS/BookStore-with-Spring-Boot.git
```
### Configuración de la Base de Datos

    Crea una base de datos en MySQL llamada bookstore.
    Configura el archivo src/main/resources/application.properties con tus credenciales de base de datos:

    spring.datasource.url=jdbc:mysql://localhost:3306/bookstore
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    spring.jpa.hibernate.ddl-auto=update

### Construir y Ejecutar la Aplicación

   1. Construir el proyecto:
      ```bash
      mvn clean install
      ```
   2. Ejecutar la aplicación:
      ```bash
      mvn spring-boot:run
      ```   
