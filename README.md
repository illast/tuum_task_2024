## Technologies

- Java 21
- Spring Boot 3.2.5
- PostgreSQL

## Installation

- Clone the repository.
- Wait for Gradle to install dependencies.
- In the terminal enter `docker-compose up -d`.
- Run src/main/java/com/example/demo/**DemoApplication.java**.

## Endpoints

- **GET /api/accounts/{accountId}**: Returns the account object.
- **POST /api/accounts**: Creates a bank account.

- **GET /api/transactions/account/{accountId}**: Returns a list of transactions.
- **POST /api/transactions**: Creates a transaction.
