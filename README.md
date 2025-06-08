# 🐞 Bug Management System Backend

A scalable and extensible Bug Tracking System inspired by Jira/Bugzilla. This backend application is built using **Spring Boot** and **MongoDB** and supports multi-project management, role-based access control, bug reporting, and more.

---

## 📌 Features

- 🔐 **JWT Authentication & Authorization**
- 👥 **Role-based Access Control (RBAC)** per project
- 🧩 **Project > Module > Task > Subtask** hierarchy
- 🐛 **Bug Reporting, Assignment & Status Tracking**
- 💬 **Comment System** on bugs
- 📎 **Attachment Support** (via URL references)
- 📣 **Real-time Notifications** (planned)
- 🧾 **Audit Logs** (planned)
- 📊 **Dashboards & Reporting** (planned)
- 📱 **Mobile-Ready Frontend** (SPA, handled separately)

---

## 🧱 Tech Stack

| Layer         | Technology          |
|---------------|---------------------|
| Language      | Java 17+            |
| Framework     | Spring Boot         |
| Database      | MongoDB             |
| Auth          | JWT                 |
| Build Tool    | Maven               |
| Logging       | SLF4J + Logback     |
| API Docs      | Swagger/OpenAPI     |

---


---

## 🔐 Authentication & Roles

- Users login using JWT
- Roles per project: `SuperAdmin`, `ProjectManager`, `Developer`, `QA`, `Client`, `Management`
- Secure endpoints with `@PreAuthorize` and custom security filters

---

## 🚀 Getting Started

### Prerequisites

- Java 17+
- MongoDB (local or Atlas)
- Maven

### Run Locally

```bash
# Clone the repository
git clone https://github.com/your-username/bug-tracker-backend.git
cd bug-tracker-backend

# Build and run the app
mvn spring-boot:run
