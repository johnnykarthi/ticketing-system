# Ticketing System with Multi-Level Approval: Setup & Usage Documentation

## Table of Contents

1. Introduction
2. Prerequisites
3. Setup Instructions
   1. Clone the Repository
   2. Configure the Database (MongoDB)
   3. Application Properties Configuration
   4. Build and Run the Application
4. API Endpoints
   1. User Management
   2. Ticket Management
   3. Approval Workflow
5. Testing with Postman
6. Project Structure
7. Conclusion

---

## 1. Introduction

This is a Ticketing System with a multi-level approval workflow, designed for teams to submit and approve ticket requests. The application supports various user roles such as submitter, approver, and admin, providing role-based access to manage tickets and approvals.

### Key Features:
- **Ticket Submission**: Users can submit tickets with details like title, description, and urgency.
- **Approval Workflow**: Three levels of approval (initial, senior, admin).
- **REST API**: CRUD operations on tickets, approval process, and notifications.

---

## 2. Prerequisites

Ensure you have the following installed before proceeding:

- **Java 17** or higher
- **Maven** (for project building)
- **MongoDB** (for ticket data storage)
- **Postman** (for API testing)
- **Git** (for cloning the repository)

---

## 3. Setup Instructions

### 3.1 Clone the Repository

Clone the repository to your local machine using the following command:

```bash
git clone <repository-url>
cd ticketing-system
```

### 3.2 Configure the Database (MongoDB)

Ensure MongoDB is installed and running locally or on a server. By default, the application connects to a local MongoDB instance.

### 3.3 Application Properties Configuration

Modify the `application.properties` file to match your MongoDB setup.

```properties
# MongoDB Configuration
spring.data.mongodb.host=localhost
spring.data.mongodb.port=27017
spring.data.mongodb.database=ticketing_system_db
 
# Other configurations
server.port=9090
```

### 3.4 Build and Run the Application

Build and run the application using Maven:

```bash
mvn clean install
mvn spring-boot:run
```

The application will start on [http://localhost:9090](http://localhost:9090).
 
---

## 4. API Endpoints

### 4.1 User Management

| Method | Endpoint                | Description                   |
|--------|-------------------------|-------------------------------|
| GET    | `/api/users`            | List all users                |
| GET    | `/api/users/{userId}`   | Get user by Id                |
| POST   | `/api/users`            | Create new user               |
| PUT    | `/api/users`            | Update a user                 |
| DELETE  | `/api/users/{userId}`  | Delete a user                 |


**Roles:** `user`, `approver`, `admin`

### 4.2 Ticket Management

| Method | Endpoint               | Description                          |
|--------|------------------------|--------------------------------------|
| POST   | `/api/tickets`          | Submit a new ticket                  |
| GET    | `/api/tickets/{ticketId}`     | Get details of a specific ticket     |
| GET    | `/api/tickets`          | List all tickets (role-restricted)   |
| PUT    | `/api/tickets/{ticketId}`     | Update an existing ticket            |
| DELETE | `/api/tickets/{ticketId}`     | Delete a ticket                      |

### 4.3 Approval Workflow

| Method | Endpoint                          | Description                                        |
|--------|----------------------------------- |-------------------------------------------------- |
| PUT    | `/api/tickets/{ticketId}/approve`        | Approve the ticket at the approval level  |
| PUT    | `/api/tickets/{ticketId}/reject`         | Reject the ticket at the approval level   |
| GET    | `/api/tickets/approvalLevel/{approvalLevel}`   | Get tickets pending approval for the specific level|
| GET    | `/api/tickets//user/{userId}`   | Get tickets submitted by the specific user|


---

## 5. Testing with Postman

### 5.1 Import the Postman Collection

1. Download the provided Postman collection from the Git repository.
2. Open Postman and import the collection to access all predefined API requests.

### 5.2 Use Case

- First, create a users and roles like `user`,`approver`,`admin` in using `/api/users` .
- And set levels `0`- user `1`- Desginated `2`- Senior `3`- admin.

### 5.3 Submitting and Approving Tickets

- Submit a new ticket via `/api/tickets`.
- Use `/api/tickets/{ticketId}/approve` to approve tickets at the respective levels.

---

## 6. Project Structure

```
ticketing-system/
│
├── src/main/java/com/ticketing/
│   ├── config/                 # Configuration files
│   ├── controller/             # REST Controllers
│   ├── model/                  # Data models (User, Ticket)
│   ├── repository/             # MongoDB Repositories
│   ├── service/                # Service layer
│   └── util/                   # Utility functions
│
├── src/main/resources/
│ └── application.properties  # Application configuration
│
├── pom.xml                     # Maven dependencies
├── postman_collection.json     # Postman Collection
└── README.md                   # Project documentation
```
 
---

## 7. Conclusion

This documentation provides detailed steps to set up, configure, and use the Ticketing System with a multi-level approval workflow. The provided Postman collection helps with testing the API endpoints. Ensure you configure your MongoDB instance and adjust the application properties as needed.