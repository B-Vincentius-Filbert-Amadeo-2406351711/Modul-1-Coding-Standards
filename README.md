# Modul-1-Coding-Standards
> anonymous-simonette-kyno-a90d43c4.koyeb.app/

## Reflection 1

I have applied meaningful naming conventions (e.g., `ProductService`, `ProductController`) to ensure the code is self-documenting. I also utilized the Single Responsibility Principle by separating concerns into Model, View, Controller, Service, and Repository layers. The `ProductRepository` handles data access, `ProductService` handles business logic, and `ProductController` handles HTTP requests. For security purposes, I implemented UUID generation for product IDs to ensure uniqueness and prevent predictable resource identifiers. I used `th:object` and `th:field` in Thymeleaf for form binding which can help in preventing some injection attacks by properly escaping output.

One mistake I encountered was the lack of ID generation for new products, which caused `NullPointerException` during edit/delete operations. I improved this by adding UUID generation in the `create` method of `ProductRepository` to ensure every product has a unique identifier upon creation. I also noticed that I am using a simple `ArrayList` which is not thread-safe; for a real application, a database or a thread-safe collection should be used. Additionally, input validation decorators (e.g., `@NotNull`, `@Size`) should be added to the `Product` model to ensure data integrity.

## Reflection 2

Writing unit tests gives me confidence that my code works as expected and helps protect against regressions. The number of unit tests in a class should be sufficient to cover all distinct paths and edge cases, but there isn't a fixed "magic number." 100% code coverage does *not* guarantee bug-free code; it only means all lines were executed. Logic errors could still exist even if the code is covered.

If we create a new functional test suite with the same setup procedures and instance variables, we would violate the **DRY (Don't Repeat Yourself)** principle. This code duplication reduces code quality because:
1.  Maintenance - If the setup logic changes (e.g., base URL or port configuration), we must update it in multiple places.
2.  Readability - The core logic of the test is cluttered with repetitive setup code.

For suggestion, we can just create a base test class (e.g., `BaseFunctionalTest`) that handles the common setup (server port, base URL, WebDriver configuration). Concrete test classes can then extend this base class and focus solely on specific test scenarios.

## Reflection 3

During this exercise, I fixed two Sonar code quality issues:
1. In `src/main/java/id/ac/ui/cs/advprog/eshop/controller/ProductController.java`, I replaced `System.out.println(...)` with an SLF4J logger (`Logger` and `LoggerFactory`), then used structured logging in the edit flow.
2. In `src/main/java/id/ac/ui/cs/advprog/eshop/service/ProductServiceImpl.java`, I removed an unnecessary temporary variable in `findById` and directly returned the expression.

My strategy was to apply behavior-preserving refactors that match each rule intent exactly. I kept each fix small and independent, then validated with `./gradlew test` so quality improvements did not introduce regressions. I also fixed Sonar coverage reporting by generating JaCoCo XML and passing its path to Sonar (`sonar.coverage.jacoco.xmlReportPaths`), because otherwise Sonar could show `0%` even when tests actually run.

The current setup already meets Continuous Integration well. The repository runs automated tests on pushes and pull requests through `.github/workflows/ci.yml`, and runs code-quality/security analysis through `.github/workflows/sonarcloud.yml` and `.github/workflows/scorecard.yml`. This provides frequent, automated feedback on build health and quality status.

For Continuous Deployment, I have already set up the automatic deployment too. I deployed this project to Koyeb, which will then fetch info about push to my main branch periodically and automating the build and deploy process automatically. That specific pipeline is not visible on Github Actions because Koyeb is pull based, not push based. Link to deployment is available at the top of this README.

## Reflection 4

### 1) Explain what principles you apply to your project

I applied SRP by separating `CarController`, `CarService`, and `CarRepository` responsibilities. Controller handles web flow, service handles application logic, and repository handles persistence details.

I applied OCP by introducing `CarFilter` and `findByFilter` in `CarService`. New filtering behavior can be added by creating new filter classes without changing service core flow.

I applied LSP through repository abstraction (`CarRepository`) with interchangeable implementation (`InMemoryCarRepository`) while keeping behavior contract consistent.

I applied ISP by splitting service contracts into `CarCommandService` and `CarQueryService`, then composing them in `CarService`.

I applied DIP by making `CarServiceImpl` depend on `CarRepository` abstraction instead of concrete storage class.

### 2) Explain the advantages of applying SOLID principles to your project with examples

The code becomes easier to extend. For example, adding a new car search criterion only needs a new class implementing `CarFilter`, not changes in controller or repository code.

The code becomes easier to test and maintain. `CarServiceImpl` can be tested with mocked `CarRepository` because dependencies are abstracted.

The code becomes safer for future changes. Splitting command and query interfaces prevents clients from depending on methods they do not need.

### 3) Explain the disadvantages of not applying SOLID principles to your project with examples

Without SRP, a controller that also handles business and storage logic becomes hard to change because one edit can break multiple concerns.

Without OCP and DIP, every new requirement can force editing existing classes directly, increasing regression risk. For example, adding new filtering rules would require modifying service internals repeatedly.

Without ISP and LSP, modules become tightly coupled to bulky contracts or non-substitutable implementations, making refactoring and swapping implementations much harder.
