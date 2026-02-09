# Modular Hexagonal Inventory System 🚀

This project is a production-grade blueprint for building Java applications using **Modular Hexagonal Architecture**. 
It is designed to be framework-agnostic, highly testable, and scalable.

## 🏗️ Architectural Overview

The project is divided into four strictly decoupled Gradle modules:

* **`core`**: The "Brain." Contains pure Java business logic, domain models, and ports. No framework dependencies.
* **`data`**: The "Persistence Adapter." Implements repository ports using Spring Data JPA.
* **`web`**: The "API Adapter." Handles REST controllers and DTOs.
* **`app-main`**: The "Bootstrap." Glues the modules together and runs the Spring Boot application.

## 🛠️ Tech Stack

* **Java 21**
* **Spring Boot 3.5.10**
* **Gradle (Multi-Module)**
* **Lombok & MapStruct**
* **H2 Database**

## 🚀 How to Run

1. Clone the repository.
2. Run `./gradlew :app-main:bootRun`.
3. Access the API at: `http://localhost:8080/api/v1/products`.

---

## 👨‍💻 Developer Information
* **Name:** Ferney Estupiñán Barón
* **Role:** Senior Full Stack Engineer
* **Email:** [ferney.estupinanb@gmail.com]
* **GitHub:** [github.com/ferneybaron](https://github.com/ferneybaron)

## 📄 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.