# 🛒 E-Commerce App

A **full-stack e-commerce application** built with **Spring Boot (backend)** and **React.js (frontend)**.  
This app supports user registration, secure JWT-based login, product management, cart functionality, order placement, admin dashboard, and more.

---

## 🚀 Features

### 👤 Authentication
- 🔐 JWT-based login for **User** and **Admin**
- 🛂 Role-based access control (`USER`, `ADMIN`)
- 🔒 Protected and secure API routes

### 🛍️ Product Management
- ➕ Add / ✏️ Update / ❌ Delete products (**Admin only**)
- 🔎 Search & filter by **category** or **product name**
- 🖼️ Product image, description, and price display

### 🛒 Cart
- ➕ Add, ➖ Remove, and 🔁 Update product quantity
- 💵 View total price of products in cart

### 📦 Order Management
- 🧾 Place order from cart
- 🗓️ View **order history** (User)
- 🗑️ Admin can delete **any user's** order

### 📝 Reviews
- ✍️ Add, 📝 Update, ❌ Delete product reviews
- 📋 View all reviews of a product

### 📫 Address Book
- 📍 Add / Update / Delete multiple addresses
- 🏠 Use address while placing an order

---

## ⚙️ Tech Stack

### 🧠 Backend
- 💻 Spring Boot
- 🔐 Spring Security + JWT
- 🗃️ JPA (Hibernate)
- 🛢️ MySQL or H2 (for testing)

### 🌐 Frontend
- ⚛️ React.js (with Vite)
- 🌍 Axios (for API calls)
- 🧭 React Router DOM

---

## 📁 Project Structure

### 📦 Backend (`Spring Boot`)

src/
├── controller/ # REST Controllers
├── model/ # JPA Entities
├── repository/ # JPA Repositories
├── service/ # Business Logic
├── security/ # JWT Filter & Config
└── main/



---

## 🛠️ Setup Instructions

### 📌 Backend Setup

```bash
# 1. Clone the repo
git clone https://github.com/your-username/ecommerce-app.git
cd ecommerce-app/backend

# 2. Configure application.properties
# Set DB config, JWT secret, and other properties

# 3. Run Spring Boot app
./mvnw spring-boot:run


# Server Port
server.port=8080

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/Ecomdb
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root            # Replace with your MySQL username
spring.datasource.password=root            # Replace with your MySQL password
