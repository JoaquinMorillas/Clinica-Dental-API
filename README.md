# 🦷 Clinica-Dental-API
A simple RESTful API for managing a dental clinic, built with Spring Boot. The application provides endpoints for user registration, authentication, and management of patients, dentists, and appointments. It uses JWT for securing endpoints and H2 for in-memory data storage.

# 📦 Tech Stack
Java 17+

Spring Boot 3.x

Spring Security (with JWT)

Spring Data JPA (Hibernate)

H2 In-Memory Database

OpenAPI / Swagger for API documentation

Thymeleaf (basic setup for potential future frontend)

# 🚀 Getting Started
Prerequisites
Java 17+

Maven 3.x

Clone the Repository
bash
Copiar
Editar
git clone https://github.com/yourusername/Clinica-Dental-API.git
cd Clinica-Dental-API
Run Locally
bash
Copiar
Editar
./mvnw spring-boot:run
The application will be accessible at http://localhost:8080.

# 🛠️ API Documentation
Swagger UI is available at:

bash
Copiar
Editar
http://localhost:8080/swagger-ui/index.html
You can explore and test all available endpoints directly from the browser.

# 🔐 Authentication
This API uses JWT for securing endpoints.

1. Register a New User
POST /auth/register

json
Copiar
Editar
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "password": "password123"
}
2. Login
POST /auth/login

Returns a JWT token:

json
Copiar
Editar
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6Ikp..."
}
3. Use Token
Add the token in the Authorization header:

makefile
Copiar
Editar
Authorization: Bearer <your_token>
# 🧪 Sample Endpoints
Method	Endpoint	Description
GET	/patient/{id}	Get patient by ID
POST	/patient/save	Create new patient
DELETE	/patient/{id}	Delete patient by ID
GET	/dentist/{id}	Get dentist by ID
POST	/appointment/save	Book an appointment
GET	/appointment/{id}	Get appointment by ID

(🔐 Some routes require JWT authentication.)

# ⚙️ H2 Console
Accessible at:

bash
Copiar
Editar
http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb

Username: sa

Password: (leave empty)

# ✍️ Author
Joaquín Morillas - https://github.com/JoaquinMorillas
