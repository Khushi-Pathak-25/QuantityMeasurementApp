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

## 🧰 Tech Stack

- **Java 17+** — Core language and application development  
- **Maven** — Build automation and dependency management  
- **JUnit 5** — Unit testing framework supporting TDD workflow  

---

## ▶️ Build & Run

### 🔹 Build the Project

```bash
mvn clean install
```

### 🔹 Run Tests

```bash
mvn test
```

Successful execution will display:

```
BUILD SUCCESS
```

---

## 📂 Project Structure

```
QuantityMeasurementApp
│
├── src
│   ├── main
│   │   └── java
│   │       └── com
│   │           └── apps
│   │               └── quantitymeasurement
│   │                   ├── QuantityMeasurementApp.java
│   │                   └── Length.java
│   │
│   └── test
│       └── java
│           └── com
│               └── apps
│                   └── quantitymeasurementapp
│                       └── QuantityMeasurementAppTest.java
│
├── pom.xml
└── README.md
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
