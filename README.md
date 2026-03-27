# 📏 QuantityMeasurementApp

> A Java application developed using **Test-Driven Development (TDD)** to incrementally design and refine a quantity measurement system.  
> The project emphasizes clean object-oriented design, value-based equality, and continuous refactoring to build a flexible and maintainable domain model.

---

## 📖 Overview

**QuantityMeasurementApp** is a modular Java project structured around incremental Use Cases.  
Each Use Case enhances the domain model while preserving behaviour through comprehensive unit testing.
The system progressively evolves toward a scalable and extensible quantity measurement model.

---

## ✅ Implemented Use Cases

### 🧩 UC1 – Feet Equality

Introduces value-based comparison for `Feet` measurements.

**Features:**
- Creation of a `Feet` class
- Overriding the `equals()` method
- Validation of:
  - Same values are equal
  - Different values are not equal
  - Null comparison returns false
  - Type mismatch returns false
  - Reference equality works correctly
- Fully tested using **JUnit 5**

---

### 🧩 UC2 – Inches Equality

Extends value-based equality comparison to `Inches` measurements.

**Features:**
- Creation of an independent `Inches` class
- Overriding the `equals()` method
- Ensures consistent equality behaviour
- Maintains independent unit validation
- Covered with dedicated unit test cases
  
---

### 🧩 UC3 – Generic Length

Refactors unit-specific classes into a unified `Length` abstraction using a `LengthUnit` enum.

**Features:**
- Introduction of a generalized `Length` class
- Implementation of `LengthUnit` enum (e.g., FEET, INCHES)
- Elimination of duplicated logic (DRY principle)
- Enables cross-unit equality comparison
- Cleaner and extensible domain design
- Fully validated with unit tests
  
---

### 🧩 **UC4 – Extended Unit Support :**

**Features:**
- Adds Yards and Centimeters to the `LengthUnit` enum with appropriate conversion factors.
- Demonstrates scalability of the generic design by enabling seamless cross-unit equality without introducing new classes.

---

### 🧩 **UC5 – Unit-to-Unit Conversion :**

**Features:**
- Introduces explicit conversion operations between supported length units using centralized enum conversion factors.
- Extends the `Length` API to convert measurements across units while preserving mathematical equivalence and precision.
  
---

### 🧩 **UC6 – Length Addition Operation :**

**Features:**
- Introduces addition between length measurements with automatic unit normalization and conversion.
- Returns a new immutable `Length` result expressed in the unit of the first operand while preserving mathematical accuracy.

---

### 🧩 UC7 – Addition with Target Unit Specification

Enhances the addition operation by allowing the result to be returned in a **specified target unit**, increasing flexibility and usability.

**Features:**
- Introduces overloaded method:
  ```java
  add(Length that, LengthUnit targetUnit)
- Allows result in any supported unit (Feet, Inches, Yards, Centimeters)
- Maintains internal base-unit normalization for mathematical correctness
- Ensures precision using controlled rounding
- Validates:
   - Null operand
   - Null target unit
- Fully covered with JUnit test cases
- Backward compatible with UC6 behavior

---

### 🧩 UC8 – Standalone Unit Refactor

Refactors the measurement system by extracting `LengthUnit` into a standalone enum and assigning it full responsibility for unit conversion logic, improving architectural clarity and scalability.

**Features:**

- Extracts `LengthUnit` into a separate file
- Delegates conversion responsibility to the unit itself:
  ```java
  public double toBaseUnit(double value)
  public double fromBaseUnit(double baseValue)
  Removes conversion logic from the Length class
- Improves separation of concerns (Single Responsibility Principle)
- Reduces coupling between value and unit
- Maintains internal base-unit normalization (inches) for mathematical consistency
- Ensures precision using controlled rounding
- Fully backward compatible with UC1–UC7 behavior
- All existing JUnit test cases pass successfully

---

### 🧩 UC9 – Weight Measurement Support

Introduces a new measurement category for weight by implementing a dedicated Weight class and WeightUnit enum. This extends the system beyond length measurements while preserving architectural stability and strict domain separation.

**Features:**

- Introduces a separate Weight class for weight measurements
- Implements a WeightUnit enum supporting:
  - Kilograms (kg)
  - Grams (g)
  - Pounds (lb)
- Enables equality comparison across different weight units using base-unit normalization

---

### 🧩 UC10 – Generic Quantity Architecture

**Features:**
- Introduces a generic `Quantity<U extends IMeasurable>` model enabling multiple measurement categories through a shared abstraction.
- Eliminates category-specific duplication by unifying equality, conversion, and addition logic into a single scalable architecture.

---

### 🧩 UC11 – Volume Measurement Support 

**Features:**
- Adds a new measurement category using `VolumeUnit` (Litre, Millilitre, Gallon) implemented through the generic `Quantity<U>` architecture.
- Validates that new measurement types integrate without modifying existing quantity logic, proving true multi-category scalability.

---

### 🧩 UC12 – Subtraction and Division Operations 

**Features:**
- Introduces subtraction between quantities with automatic cross-unit normalization while preserving immutability.
- Adds division support producing a dimensionless ratio, enabling comparative analysis across measurements of the same category.

---

### 🧩 UC13 – Centralized Arithmetic Logic (DRY Refactor)

**Features:**
- Refactors addition, subtraction, and division to use a centralized arithmetic helper, eliminating duplicated validation and conversion logic.
- Improves maintainability and scalability while preserving all existing behaviour and public APIs.

---

### 🧩 UC14 – Temperature Measurement (Selective Arithmetic Support)

**Features:**
- Introduces temperature measurements using `TemperatureUnit` integrated into the generic `Quantity<U>` architecture.
- Supports equality comparison and unit conversion across Celsius, Fahrenheit, and Kelvin using non-linear conversion formulas.
- Refactors `IMeasurable` with default capability validation to allow category-specific operation support.
- Prevents unsupported arithmetic operations (addition, subtraction, division) through explicit validation and meaningful exceptions.
- Demonstrates Interface Segregation and capability-based design while preserving backward compatibility for length, weight, and volume.

---

### 🧩 UC15 – N-Tier Architecture Refactoring 

**Features:**
- Refactors the Quantity Measurement Application from a monolithic design into a structured **N-Tier architecture**.
- Introduces layered separation including **Controller, Service, Repository, Model, Entity, DTO, Interfaces, and Units** packages.
- Moves business logic into the **Service layer**, while the **Controller layer** manages application interaction and orchestration.
- Adds a **Repository layer with a cache-based storage implementation** to record measurement operations.
- Standardizes data flow using **QuantityDTO for external transfer**, **QuantityModel for internal processing**, and **QuantityMeasurementEntity for persistence**.
- Improves **modularity, testability, maintainability, and extensibility**, preparing the system for future integration with **REST APIs or database storage**.

---

### 🧩 UC16 – Database Integration with JDBC for Quantity Measurement Persistence 

**Features:**
- Extends the N-Tier architecture established in UC15 with **persistent relational database storage** using **JDBC (Java Database Connectivity)**.
- Introduces `QuantityMeasurementDatabaseRepository` as a full JDBC-based replacement for the in-memory `QuantityMeasurementCacheRepository`, enabling long-term data persistence across application restarts.
- Adds `ApplicationConfig` utility class that loads all database configuration from `application.properties`, supporting environment-specific settings for **development, testing, and production**.
- Introduces `ConnectionPool` utility class that manages a pool of reusable JDBC connections for efficient resource usage, eliminating the overhead of opening and closing connections on every operation.
- Extends `IQuantityMeasurementRepository` interface with four new methods: `getMeasurementsByOperation()`, `getMeasurementsByType()`, `getTotalCount()`, and `deleteAll()` — enabling filtering, reporting, and test isolation.
- Adds `DatabaseException` to the custom exception hierarchy, with static factory methods (`connectionFailed`, `queryFailed`, `transactionFailed`) for structured, meaningful database error handling.
- Adopts **parameterized SQL queries** (`PreparedStatement`) throughout the database repository to prevent SQL injection attacks.
- Migrates all `System.out.println` logging to **Java's built-in `java.util.logging` (JUL)** framework via SLF4J and Logback for structured, configurable output across all layers.
- Reorganizes packages from `com.apps.quantitymeasurement.*` to `com.app.quantitymeasurement.*` with clear layer-based sub-packages: `controller`, `service`, `repository`, `entity`, `exception`, `unit`, and `util`.
- Uses **H2 embedded database** by default (zero external setup required) with the ability to switch to MySQL or PostgreSQL by updating `application.properties` and uncommenting the relevant `pom.xml` dependency.
- Adds `schema.sql` under `src/main/resources/db/` defining the `quantity_measurement_entity` table and an audit `quantity_measurement_history` table with proper indexes for query performance.
- Repository type is fully **configurable at runtime** via the `repository.type` property (`database` or `cache`) — no code changes needed to switch persistence strategies.
- Adds integration tests (`QuantityMeasurementIntegrationTest`) and unit tests for each layer — repository, service, and controller — using H2 in-memory database for fast, isolated test execution.
- Implements `closeResources()` and `deleteAllMeasurements()` methods on `QuantityMeasurementApp` for graceful shutdown and test state management.
- Demonstrates enterprise-level practices including **connection pooling, transaction awareness, resource cleanup with try-finally, separation of configuration from code**, and **environment-specific database profiles**.

---

### 🧩 UC17 – Spring Boot Integration with REST Services and JPA Persistence :                ← NEW (UC17)

**Features:**
- Migrates the entire application from a standalone JDBC-based design to a **Spring Boot REST service** while preserving all domain models and business logic from UC1–UC16.
- Introduces `QuantityMeasurementApplication` as the **Spring Boot entry point** with `@SpringBootApplication` and `@OpenAPIDefinition` for application metadata.
- **Replaces manual JDBC repositories** (`QuantityMeasurementDatabaseRepository`, `QuantityMeasurementCacheRepository`, `ApplicationConfig`, `ConnectionPool`) with **Spring Data JPA** — `QuantityMeasurementRepository` extending `JpaRepository<QuantityMeasurementEntity, Long>`.
- `QuantityMeasurementRepository` defines derived-query methods: `findByOperation`, `findByThisMeasurementType`, `findByCreatedAtAfter`, `countByOperationAndErrorFalse`, `findByErrorTrue`, and a custom `@Query` method `findSuccessfulByOperation`.
- **Refactors the package layout** — introduces three distinct packages: `entity` for JPA-mapped database classes (`QuantityMeasurementEntity`), `dto` for API request/response objects (`QuantityDTO`, `QuantityInputDTO`, `QuantityMeasurementDTO`), and `model` for pure domain/business objects (`Quantity`, `QuantityModel`, `OperationType`).
- **Refactors `QuantityDTO`** to include Bean Validation annotations (`@Data`, `@NotNull`, `@NotEmpty`, `@Pattern`, `@AssertTrue`) enforcing input integrity at the API boundary.
- Introduces **`QuantityMeasurementDTO`** as a structured API response object with static factory methods: `fromEntity()`, `toEntity()`, `fromEntityList()`, and `toEntityList()` using the Java Stream API for efficient collection mapping.
- Adds **`QuantityInputDTO`** to encapsulate the two-operand input structure accepted by all POST endpoints.
- Introduces **`OperationType` enum** with constants `ADD`, `SUBTRACT`, `MULTIPLY`, `DIVIDE`, `COMPARE`, and `CONVERT` for type-safe operation representation throughout the application.
- **Exposes RESTful API endpoints** through `QuantityMeasurementController` using `@RestController` and `@RequestMapping("/api/v1/quantities")`:
- `POST /compare`, `/convert`, `/add`, `/subtract`, `/divide` — accept `QuantityInputDTO`, return `QuantityMeasurementDTO`.
- `GET /history/operation/{operation}`, `/history/type/{measurementType}`, `/history/errored` — return `List<QuantityMeasurementDTO>`.
- `GET /count/{operation}` — returns operation count.
- Adds **Swagger/OpenAPI annotations** (`@Operation`, `@Tag`, `@Parameter`) on all controller methods to generate interactive API documentation.
- Implements **centralized exception handling** via `GlobalExceptionHandler` (`@ControllerAdvice`) with handlers for `MethodArgumentNotValidException`, `QuantityMeasurementException`, and general `Exception` — returning structured JSON error responses with timestamp, status, error type, message, and path.
- **Removes `DatabaseException`** — exception handling is now managed declaratively through `GlobalExceptionHandler` and Spring's exception translation layer.
- Adds **`SecurityConfig`** in a dedicated `config` package preparing the system for Spring Security integration; currently permits all requests for development and testing.
- Supports **environment-based configuration** through `application.properties` (H2, development) and `application-prod.properties` (MySQL, production), replacing the custom `ApplicationConfig` and manual property loading from UC16.
- **HikariCP** is used as the default connection pool (auto-configured by Spring Boot), replacing the manual `ConnectionPool` implementation from UC16.
- **Schema is managed by JPA auto-DDL** (`spring.jpa.hibernate.ddl-auto=create-drop` in dev), replacing the explicit `schema.sql` from UC16.
- Adds **Spring Boot Actuator** for monitoring via `/actuator/health`, `/actuator/info`, and `/actuator/metrics`.
- Adds comprehensive **Spring Boot testing**:
   - `QuantityMeasurementControllerTest` — controller unit tests using `@WebMvcTest` and `MockMvc`.
   - `QuantityMeasurementApplicationTests` — full-stack integration tests using `@SpringBootTest` and `TestRestTemplate`.
   - `QuantityMeasurementServiceIntegrationTest` — service-layer integration tests using `@SpringBootTest`.
   - `QuantityMeasurementRepositoryTest` — Spring Data JPA repository tests.
- Demonstrates migration from **JDBC-based persistence (UC16)** to a modern **Spring Boot + JPA enterprise architecture** while maintaining the original measurement logic and full test coverage.

---

### 🧰 Tech Stack

- **Java 17+** — core language
- **Maven** — build and dependency management

#### 🚀 Backend Framework
- **Spring Boot 3.2.2** — application framework with auto-configuration
- **Spring Web (spring-boot-starter-web)** — REST APIs (Spring MVC + embedded Tomcat)
- **Spring Data JPA (spring-boot-starter-data-jpa)** — ORM abstraction (Hibernate)
- **Spring Security (spring-boot-starter-security)** — authentication and endpoint security
- **Spring Boot Validation (spring-boot-starter-validation)** — Bean Validation for request validation
- **Spring Boot Actuator** — monitoring endpoints (`/actuator/health`, `/metrics`, etc.)

#### 📄 API Documentation
- **Swagger / OpenAPI (springdoc-openapi-starter-webmvc-ui)** — interactive API docs

#### 🗄️ Database
- **H2 Database** — in-memory DB for development/testing
- **MySQL Connector/J** — optional production database support

#### ⚙️ Utilities
- **Lombok** — reduces boilerplate (getters/setters, constructors, etc.)
- **HikariCP** — auto-configured connection pool (Spring Boot default)

#### 🧪 Testing
- **Spring Boot Test (JUnit 5, Mockito, MockMvc)** — unit, integration, and controller testing
- **Spring Security Test** — testing secured endpoints

### ▶️ Build / Run

- Clean and compile the project:

```
mvn clean compile
```

- Run the Spring Boot application:

```
mvn spring-boot:run
```

- Run all tests:

```
mvn clean test
```

- Run specific test class:

```
mvn test -Dtest=QuantityMeasurementServiceIntegrationTest
```

- Build executable JAR:

```
mvn clean package
```

- Run the packaged application:

```
java -jar target/quantity-measurement-app-0.0.1-SNAPSHOT.jar
```

Once the application starts:

- **API Base URL**

```
http://localhost:8080/api/v1/quantities
```

- **Swagger API Documentation**

```
http://localhost:8080/swagger-ui.html
```

- **H2 Database Console**

```
http://localhost:8080/h2-console
```

- **Spring Boot Actuator Endpoints**

```
http://localhost:8080/actuator/health

http://localhost:8080/actuator/info

http://localhost:8080/actuator/metrics
```

### ⚙️ Configuration

The application is configured via `src/main/resources/application.properties` (development) and `application-prod.properties` (production):

```properties
# Application name and active profile
spring.application.name=quantity-measurement-app
spring.profiles.active=dev

# H2 In-Memory Database (Development)
spring.datasource.url=jdbc:h2:mem:quantitymeasurementdb;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Spring Data JPA / Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# H2 Console (Development only)
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Swagger / OpenAPI
springdoc.api-docs.path=/v3/api-docs
springdoc.swagger-ui.path=/swagger-ui.html
springdoc.swagger-ui.enabled=true

# Actuator
management.endpoints.web.exposure.include=health,info,metrics
management.endpoint.health.show-details=always
```

To switch to **MySQL in production**, activate the prod profile:

```
java -jar target/quantity-measurement-app-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

And update the credentials in `application-prod.properties`.

### 📂 Project Structure

```
  📦 QuantityMeasurementApp
  │
  ├── 📁 src
  │   ├── 📁 main
  │   │   ├── 📁 java
  │   │   │   └── 📁 com
  │   │   │       └── 📁 app
  │   │   │           └── 📁 quantitymeasurement
  │   │   │               ├── 📁 config                              ← NEW (UC17)
  │   │   │               │   └── 📄 SecurityConfig.java             ← NEW (UC17)
  │   │   │               │
  │   │   │               ├── 📁 controller
  │   │   │               │   └── 📄 QuantityMeasurementController.java
  │   │   │               │
  │   │   │               ├── 📁 exception
  │   │   │               │   ├── 📄 GlobalExceptionHandler.java     ← NEW (UC17)
  │   │   │               │   └── 📄 QuantityMeasurementException.java
  │   │   │               │
  │   │   │               ├── 📁 dto                                 ← NEW (UC17 refactor) API request/response objects
  │   │   │               │   ├── 📄 QuantityDTO.java
  │   │   │               │   ├── 📄 QuantityInputDTO.java           ← NEW (UC17)
  │   │   │               │   └── 📄 QuantityMeasurementDTO.java     ← NEW (UC17)
  │   │   │               │
  │   │   │               ├── 📁 entity                              ← NEW (UC17 refactor) JPA database-mapped classes
  │   │   │               │   └── 📄 QuantityMeasurementEntity.java
  │   │   │               │
  │   │   │               ├── 📁 model                               ← NEW (UC17 refactor) domain/business objects
  │   │   │               │   ├── 📄 OperationType.java              ← NEW (UC17)
  │   │   │               │   ├── 📄 Quantity.java
  │   │   │               │   └── 📄 QuantityModel.java
  │   │   │               │
  │   │   │               ├── 📁 repository
  │   │   │               │   └── 📄 QuantityMeasurementRepository.java  ← NEW (UC17) replaces JDBC repos
  │   │   │               │
  │   │   │               ├── 📁 service
  │   │   │               │   ├── 📄 IQuantityMeasurementService.java
  │   │   │               │   └── 📄 QuantityMeasurementServiceImpl.java
  │   │   │               │
  │   │   │               ├── 📁 unit
  │   │   │               │   ├── 📄 IMeasurable.java
  │   │   │               │   ├── 📄 SupportsArithmetic.java
  │   │   │               │   ├── 📄 LengthUnit.java
  │   │   │               │   ├── 📄 WeightUnit.java
  │   │   │               │   ├── 📄 VolumeUnit.java
  │   │   │               │   └── 📄 TemperatureUnit.java
  │   │   │               │
  │   │   │               └── 📄 QuantityMeasurementApplication.java ← NEW (UC17) replaces QuantityMeasurementApp.java
  │   │   │
  │   │   └── 📁 resources
  │   │       ├── 📄 application.properties                          ← UPDATED (UC17)
  │   │       └── 📄 application-prod.properties                     ← NEW (UC17)
  │   │
  │   └── 📁 test
  │       ├── 📁 java
  │       │   └── 📁 com
  │       │       └── 📁 app
  │       │           └── 📁 quantitymeasurement
  │       │               ├── 📁 controller
  │       │               │   └── 📄 QuantityMeasurementControllerTest.java
  │       │               │
  │       │               ├── 📁 exception
  │       │               │   └── 📄 QuantityMeasurementExceptionTest.java
  │       │               │
  │       │               ├── 📁 integrationTests
  │       │               │   └── 📄 QuantityMeasurementApplicationTests.java  ← UPDATED (UC17)
  │       │               │
  │       │               ├── 📁 dto
  │       │               │   └── 📄 QuantityDTOTest.java
  │       │               │
  │       │               ├── 📁 entity
  │       │               │   └── 📄 QuantityMeasurementEntityTest.java
  │       │               │
  │       │               ├── 📁 model
  │       │               │   ├── 📄 QuantityArithmeticTest.java
  │       │               │   ├── 📄 QuantityConversionTest.java
  │       │               │   ├── 📄 QuantityEqualityTest.java
  │       │               │   └── 📄 QuantityModelTest.java
  │       │               │
  │       │               ├── 📁 repository
  │       │               │   └── 📄 QuantityMeasurementRepositoryTest.java    ← NEW (UC17)
  │       │               │
  │       │               ├── 📁 service
  │       │               │   ├── 📄 QuantityMeasurementServiceIntegrationTest.java  ← NEW (UC17)
  │       │               │   └── 📄 QuantityMeasurementServiceTest.java
  │       │               │
  │       │               └── 📁 unit
  │       │                   ├── 📄 IMeasurableTest.java
  │       │                   ├── 📄 LengthUnitTest.java
  │       │                   ├── 📄 WeightUnitTest.java
  │       │                   ├── 📄 VolumeUnitTest.java
  │       │                   └── 📄 TemperatureUnitTest.java
  │       │
  │       └── 📁 resources
  │           └── 📄 application.properties
  │
  ├── ⚙️ pom.xml
  ├── 🚫 .gitignore
  └── 📘 README.md
```

> **Note on UC16 → UC17 replacements:** The following UC16 classes have been intentionally removed as their responsibilities are now handled by Spring Boot:
> - `QuantityMeasurementDatabaseRepository` & `QuantityMeasurementCacheRepository` → replaced by `QuantityMeasurementRepository` (Spring Data JPA)
> - `ApplicationConfig` & `ConnectionPool` → replaced by Spring Boot auto-configuration and HikariCP
> - `DatabaseException` → replaced by `GlobalExceptionHandler` and Spring's exception translation
> - `schema.sql` → replaced by JPA auto-DDL (`spring.jpa.hibernate.ddl-auto=create-drop`)
> - `QuantityMeasurementApp.java` → replaced by `QuantityMeasurementApplication.java`

### ⚙️ Development Approach

> This project follows an incremental **Test-Driven Development (TDD)** workflow:

- Tests are written first to define expected behaviour.
- Implementation code is developed to satisfy the tests.
- Each Use Case introduces new functionality in small, controlled steps.
- Existing behaviour is preserved through continuous refactoring.
- Design evolves toward clean, maintainable, and well-tested software.
- Later use cases introduce capability-based behavior where different measurement categories support different operations safely.

---

## 👨‍💻 Author

**Khushi Pathak**  

---

<div align="center">
✨ Developed incrementally using Test-Driven Development and continuous refactoring.
</div>
