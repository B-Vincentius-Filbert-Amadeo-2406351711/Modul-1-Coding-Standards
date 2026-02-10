# Modul-1-Coding-Standards

## Reflection 1

I have applied meaningful naming conventions (e.g., `ProductService`, `ProductController`) to ensure the code is self-documenting. I also utilized the Single Responsibility Principle by separating concerns into Model, View, Controller, Service, and Repository layers. The `ProductRepository` handles data access, `ProductService` handles business logic, and `ProductController` handles HTTP requests. For security purposes, I implemented UUID generation for product IDs to ensure uniqueness and prevent predictable resource identifiers. I used `th:object` and `th:field` in Thymeleaf for form binding which can help in preventing some injection attacks by properly escaping output.

One mistake I encountered was the lack of ID generation for new products, which caused `NullPointerException` during edit/delete operations. I improved this by adding UUID generation in the `create` method of `ProductRepository` to ensure every product has a unique identifier upon creation. I also noticed that I am using a simple `ArrayList` which is not thread-safe; for a real application, a database or a thread-safe collection should be used. Additionally, input validation decorators (e.g., `@NotNull`, `@Size`) should be added to the `Product` model to ensure data integrity.

## Reflection 2

Writing unit tests gives me confidence that my code works as expected and helps protect against regressions. The number of unit tests in a class should be sufficient to cover all distinct paths and edge cases, but there isn't a fixed "magic number." 100% code coverage does *not* guarantee bug-free code; it only means all lines were executed. Logic errors could still exist even if the code is covered.

If we create a new functional test suite with the same setup procedures and instance variables, we would violate the **DRY (Don't Repeat Yourself)** principle. This code duplication reduces code quality because:
1.  Maintenance - If the setup logic changes (e.g., base URL or port configuration), we must update it in multiple places.
2.  Readability - The core logic of the test is cluttered with repetitive setup code.

For suggestion, we can just create a base test class (e.g., `BaseFunctionalTest`) that handles the common setup (server port, base URL, WebDriver configuration). Concrete test classes can then extend this base class and focus solely on specific test scenarios.
