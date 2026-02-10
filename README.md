# Modular Hexagonal Inventory System 🚀

This project is a production-grade blueprint for building Java applications using **Modular Hexagonal Architecture**.
It is designed to be framework-agnostic, highly testable, and scalable.

## ✨ Features

* **Product management** — Register and list products.
* **Inventory movements** — Inbound (add stock) and outbound (remove stock) with reasons.
* **Stock balance** — Get current stock per product.
* **Modular hexagonal layout** — Core domain, data adapters, web adapters, and bootstrap kept strictly decoupled.

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

**Prerequisites:** JDK 21.

1. Clone the repository.
2. Run the application:
   * **Linux / macOS:** `./gradlew :app-main:bootRun`
   * **Windows:** `.\gradlew.bat :app-main:bootRun`
3. Base URL: `http://localhost:8080`

### API Endpoints

| Resource     | Method | Path                              | Description                |
|-------------|--------|-----------------------------------|----------------------------|
| Products    | `POST` | `/api/v1/products`                | Register a product         |
| Products    | `GET`  | `/api/v1/products`                | List all products          |
| Products    | `GET`  | `/api/v1/products/{productId}`    | Get product by ID          |
| Inventory   | `POST` | `/api/v1/inventories/inbound`     | Add stock (inbound)        |
| Inventory   | `POST` | `/api/v1/inventories/outbound`    | Remove stock (outbound)    |
| Inventory   | `GET`  | `/api/v1/inventories/{productId}` | Get stock balance for product |

---

## 👨‍💻 Developer Information
* **Name:** Ferney Estupiñán Barón
* **Role:** Senior Full Stack Engineer
* **Email:** [ferney.estupinanb@gmail.com]
* **GitHub:** [github.com/ferneybaron](https://github.com/ferneybaron)

## 📄 License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.