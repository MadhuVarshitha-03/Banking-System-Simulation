# Banking System Simulation

A Java-based Banking System that supports account management, deposits,
withdrawals, and fund transfers with MySQL persistence. The system is
designed using OOP principles, JDBC, multithreading, and input validation
to simulate real-world banking operations.

---

## 🔧 Technologies Used
- Java (Core Java, OOP)
- JDBC (MySQL Connector/J)
- MySQL
- Multithreading (ExecutorService)
- Swing (GUI)
- Eclipse IDE

---

## ✨ Features
- Create and manage bank accounts
- Deposit and withdraw money
- Fund transfer between accounts
- Concurrent transaction processing using thread pools
- Menu-driven console interface
- Swing-based graphical user interface
- Input validation and account existence checks
- Custom exception handling for insufficient balance
- Persistent storage using MySQL

---

## 🗂️ Project Structure
BankingSystem/
│
├── src/
│ ├── db/
│ │ └── DBConnection.java
│ ├── model/
│ │ └── Account.java
│ ├── exception/
│ │ └── InsufficientBalanceException.java
│ ├── service/
│ │ └── BankService.java
│ ├── task/
│ │ └── TransactionTask.java
│ ├── ui/
│ │ ├── ConsoleMenu.java
│ │ └── BankingGUI.java
│ ├── Main.java
│
├── lib/
│ └── mysql-connector-j-9.5.0.jar
│
└── README.md

yaml
Copy code

---

## 🛠️ Database Setup

```sql
CREATE DATABASE banking_system;
USE banking_system;

CREATE TABLE accounts (
    account_no INT PRIMARY KEY,
    holder_name VARCHAR(100),
    balance DOUBLE
);
▶️ How to Run
Console Application
Run Main.java

Use the menu to deposit, withdraw, or transfer funds

GUI Application
Update Main.java to launch BankingGUI

Run the project as a Java Application

🧠 Key Learning Outcomes
Practical use of JDBC with MySQL

Thread-safe concurrent programming

Exception handling and validation

Clean layered architecture

Real-world backend system design

📌 Future Enhancements
User authentication

Admin dashboard

Transaction history

REST API integration
