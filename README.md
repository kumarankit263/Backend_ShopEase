E-Commerce App
A full-stack e-commerce application built with Spring Boot (backend) and React.js (frontend). It supports user registration, JWT-based login, product browsing, cart management, orders, admin dashboard, and more.
🚀 Features
👤 Authentication
User & Admin login using JWT

Role-based access (USER, ADMIN)

Protected API routes

🛍️ Product Management
Add/update/delete products (Admin)

Search and filter by category/name

Product image and description

🛒 Cart
Add/remove/update quantity

View total cart value

📦 Order Management
Place orders from cart

View order history by user

Admin: delete any user’s order

📝 Reviews
Add, update, delete product reviews

View all reviews per product

📫 Address Book
Add/update/delete multiple addresses

Use address while placing an order

⚙️ Tech Stack
🧠 Backend
Spring Boot

Spring Security + JWT

JPA (Hibernate)

MySQL or H2 DB

🌐 Frontend
React.js (with Vite)

Axios for API calls

React Router DOM

📁 Project Structure
Backend (Spring Boot)

src/
├── model/           # JPA Entities
├── controller/      # REST Controllers
├── service/         # Service Layer
├── repository/      # JPA Repositories
├── security/        # JWT Filters and Config
└── main/

🛠️ Setup Instructions
Backend
Clone the repo and navigate to backend folder

Configure your application.properties with DB credentials

Run using IntelliJ or:

./mvnw spring-boot:run
