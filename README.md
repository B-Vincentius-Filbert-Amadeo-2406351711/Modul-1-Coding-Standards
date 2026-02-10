# Modul-1-Coding-Standards

## Reflection 1

### Clean Code Principles
I have applied meaningful naming conventions (e.g., `ProductService`, `ProductController`) to ensure the code is self-documenting. I also utilized the Single Responsibility Principle by separating concerns into Model, View, Controller, Service, and Repository layers. The `ProductRepository` handles data access, `ProductService` handles business logic, and `ProductController` handles HTTP requests.

### Secure Coding Practices
I implemented UUID generation for product IDs to ensure uniqueness and prevent predictable resource identifiers. I used `th:object` and `th:field` in Thymeleaf for form binding which can help in preventing some injection attacks by properly escaping output.

### Mistakes and Improvements
One mistake I encountered was the lack of ID generation for new products, which caused `NullPointerException` during edit/delete operations. I improved this by adding UUID generation in the `create` method of `ProductRepository` to ensure every product has a unique identifier upon creation. I also noticed that I am using a simple `ArrayList` which is not thread-safe; for a real application, a database or a thread-safe collection should be used. Additionally, input validation (e.g., `@NotNull`, `@Size`) should be added to the `Product` model to ensure data integrity.
