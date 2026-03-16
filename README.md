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

## 🧰 Tech Stack

- **Java 17+** — core language and application development
- **Maven** — build automation and dependency management
- **JUnit 5.10.0 (Jupiter)** — unit and integration testing framework used across all test layers
- **Mockito 4.8.1** — mocking framework for isolated layer testing
- **H2 2.2.224** — embedded in-memory/file database for development and testing
- **SLF4J + Logback** — structured logging facade and implementation
- **HikariCP 5.1.0** — connection pool library (included as dependency reference)
- **JDBC** — Java Database Connectivity API for relational database access

---

## ▶️ Build & Run

- Clean and compile:

  ```
  mvn clean compile
  ```

- Run the application:

  ```
  mvn exec:java
  ```

- Run all tests:

  ```
  mvn clean test
  ```

- Run only integration tests:

  ```
  mvn test -Dtest=QuantityMeasurementIntegrationTest
  ```

- Run only database repository tests:

  ```
  mvn test -Dtest=QuantityMeasurementDatabaseRepositoryTest
  ```

- Build a fat JAR (includes all dependencies):

  ```
  mvn clean package
  ```

- Run the fat JAR directly:

  ```
  java -jar target/quantity-measurement-app-fat.jar
  ```

### ⚙️ Configuration

The application is configured via `src/main/resources/application.properties`:

```properties
# Switch between "database" (H2/MySQL) and "cache" (in-memory)
repository.type=database

# Environment: development | testing | production
app.env=development

# H2 embedded database (default — no external setup needed)
db.url=jdbc:h2:./quantitymeasurementdb;AUTO_SERVER=TRUE
db.username=sa
db.password=
db.driver=org.h2.Driver
db.pool-size=5
```

To switch to **MySQL** in future use cases, uncomment the MySQL block in `application.properties` and the `mysql-connector-java` dependency in `pom.xml`.

## 📂 Project Structure

```
📦 QuantityMeasurementApp
  │
  ├── 📁 src
  │   ├── 📁 main
  │   │   ├── 📁 java
  │   │   │   └── 📁 com
  │   │   │       └── 📁 app
  │   │   │           └── 📁 quantitymeasurement
  │   │   │               ├── 📁 controller
  │   │   │               │   └── 📄 QuantityMeasurementController.java
  │   │   │               │
  │   │   │               ├── 📁 entity
  │   │   │               │   ├── 📄 Quantity.java
  │   │   │               │   ├── 📄 QuantityDTO.java
  │   │   │               │   ├── 📄 QuantityModel.java
  │   │   │               │   └── 📄 QuantityMeasurementEntity.java
  │   │   │               │
  │   │   │               ├── 📁 exception
  │   │   │               │   ├── 📄 QuantityMeasurementException.java
  │   │   │               │   └── 📄 DatabaseException.java          ← NEW (UC16)
  │   │   │               │
  │   │   │               ├── 📁 repository
  │   │   │               │   ├── 📄 IQuantityMeasurementRepository.java
  │   │   │               │   ├── 📄 QuantityMeasurementCacheRepository.java
  │   │   │               │   └── 📄 QuantityMeasurementDatabaseRepository.java  ← NEW (UC16)
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
  │   │   │               ├── 📁 util                                ← NEW (UC16)
  │   │   │               │   ├── 📄 ApplicationConfig.java          ← NEW (UC16)
  │   │   │               │   └── 📄 ConnectionPool.java             ← NEW (UC16)
  │   │   │               │
  │   │   │               └── 📄 QuantityMeasurementApp.java
  │   │   │
  │   │   └── 📁 resources
  │   │       ├── 📄 application.properties                          ← NEW (UC16)
  │   │       └── 📁 db
  │   │           └── 📄 schema.sql                                  ← NEW (UC16)
  │   │
  │   └── 📁 test
  │       ├── 📁 java
  │       │   └── 📁 com
  │       │       └── 📁 app
  │       │           └── 📁 quantitymeasurement
  │       │               ├── 📁 controller
  │       │               │   └── 📄 QuantityMeasurementControllerTest.java
  │       │               │
  │       │               ├── 📁 entity
  │       │               │   ├── 📄 QuantityDTOTest.java
  │       │               │   └── 📄 QuantityMeasurementEntityTest.java
  │       │               │
  │       │               ├── 📁 exception
  │       │               │   └── 📄 QuantityMeasurementExceptionTest.java
  │       │               │
  │       │               ├── 📁 integrationTests                    ← NEW (UC16)
  │       │               │   └── 📄 QuantityMeasurementIntegrationTest.java
  │       │               │
  │       │               ├── 📁 model
  │       │               │   ├── 📄 QuantityArithmeticTest.java
  │       │               │   ├── 📄 QuantityConversionTest.java
  │       │               │   ├── 📄 QuantityEqualityTest.java
  │       │               │   └── 📄 QuantityModelTest.java
  │       │               │
  │       │               ├── 📁 repository
  │       │               │   ├── 📄 QuantityMeasurementCacheRepositoryTest.java
  │       │               │   └── 📄 QuantityMeasurementDatabaseRepositoryTest.java  ← NEW (UC16)
  │       │               │
  │       │               ├── 📁 service
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
  │           └── 📄 application.properties                          ← NEW (UC16)
  │
  ├── ⚙️ pom.xml
  ├── 🚫 .gitignore
  └── 📘 README.md
```

---

## ⚙️ Development Approach

This project follows the **Test-Driven Development (TDD)** cycle:

1. Write failing test cases
2. Implement minimal code to pass tests
3. Refactor safely while keeping tests green
4. Commit incrementally per Use Case

This ensures:
- High reliability
- Clean design
- Safe refactoring
- Incremental evolution

---

## 👨‍💻 Author

**Khushi Pathak**  

---

<div align="center">
✨ Developed incrementally using Test-Driven Development and continuous refactoring.
</div>
