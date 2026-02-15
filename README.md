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

### Persistence Adapter Selection

The persistence adapter is selected by `app.persistence.type`:

* `jpa` (default)
* `jdbc`
* `mongo`
* `elasticsearch`

The project uses only environment profiles:

* `develop`
* `release`
* `prod`

Datasource vendor is environment-driven (no vendor profile needed):

* `DATABASE_URL`
* `DATABASE_DRIVER`
* `DATABASE_USERNAME`
* `DATABASE_PASSWORD`
* Mongo URI (when `APP_PERSISTENCE_TYPE=mongo`) with precedence:
  * `SPRING_DATA_MONGODB_URI`
  * `MONGO_URI`
  * `MONGO_USERNAME` + `MONGO_PASSWORD` + `MONGO_HOST` + `MONGO_PORT` + `MONGO_DATABASE` + `MONGO_AUTH_DB`
* Elasticsearch (when `APP_PERSISTENCE_TYPE=elasticsearch`):
  * `ELASTICSEARCH_URIS`
  * `ELASTICSEARCH_USERNAME`
  * `ELASTICSEARCH_PASSWORD`

Adapter type is environment-driven:

* `APP_PERSISTENCE_TYPE=jpa|jdbc|mongo`

Examples:

* Develop + H2 + JPA:
  `./gradlew :app-main:bootRun --args='--spring.profiles.active=develop'`
* Release + PostgreSQL + JPA:
  `DATABASE_URL='jdbc:postgresql://localhost:5432/inventorydb?currentSchema=public&ssl=false' DATABASE_DRIVER='org.postgresql.Driver' DATABASE_USERNAME='postgres' DATABASE_PASSWORD='my-secret-pw' APP_PERSISTENCE_TYPE='jpa' ./gradlew :app-main:bootRun --args='--spring.profiles.active=release'`
* Prod + MySQL + JDBC adapter:
  `DATABASE_URL='jdbc:mysql://localhost:3306/inventorydb?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC' DATABASE_DRIVER='com.mysql.cj.jdbc.Driver' DATABASE_USERNAME='root' DATABASE_PASSWORD='rootpassword' APP_PERSISTENCE_TYPE='jdbc' ./gradlew :app-main:bootRun --args='--spring.profiles.active=prod'`
* Prod + Mongo adapter:
  `SPRING_DATA_MONGODB_URI='mongodb://root:my-secret-pw@localhost:27017/inventorydb?authSource=admin' APP_PERSISTENCE_TYPE='mongo' ./gradlew :app-main:bootRun --args='--spring.profiles.active=prod'`
* Prod + Elasticsearch adapter:
  `ELASTICSEARCH_URIS='http://localhost:9200' APP_PERSISTENCE_TYPE='elasticsearch' ./gradlew :app-main:bootRun --args='--spring.profiles.active=prod'`

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
