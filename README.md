# 📏 QuantityMeasurementApp

> A Java application developed using **Test-Driven Development (TDD)** to incrementally design and refine a quantity measurement system.  
> The project emphasizes clean object-oriented design, value-based equality, and continuous refactoring.

---

## 📖 Overview

**QuantityMeasurementApp** is a modular Java project structured around incremental Use Cases.  
Each Use Case enhances the domain model while preserving behavior through comprehensive unit testing.

The current implementation establishes **value-based equality** for measurement units, starting with Feet.

---

## ✅ Implemented Use Case

### 🔹 UC1 – Feet Equality

This use case introduces value-based comparison for Feet measurements.

### ✔ Features Implemented

- Creation of a `Feet` measurement object
- Overriding the `equals()` method
- Validation of:
  - Same values are equal
  - Different values are not equal
  - Null comparison returns false
  - Type mismatch returns false
  - Reference equality works correctly
- Fully tested using **JUnit 5**

---

## 🧰 Tech Stack

- **Java 17+**
- **Maven**
- **JUnit 5**

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
│   │                   └── QuantityMeasurementApp.java
│   │
│   └── test
│       └── java
│           └── com
│               └── apps
│                   └── quantitymeasurement
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
