# Java ATM Simulator

This is a simple **console-based ATM simulator** written in Java. It allows a user to perform basic banking operations such as checking balance, depositing money, withdrawing cash, transferring funds, changing PIN, and viewing transaction history. It also demonstrates basic exception handling and object-oriented programming concepts.

## 🔧 Features

- ✅ PIN Authentication
- 💰 Balance Inquiry
- 💵 Deposit and Withdraw Funds
- 🔁 Transfer Money to Another Account
- 🔐 Change Account PIN
- 📜 View Transaction History
- ⚠️ Custom Exception Handling (`IncorrectInputException`)

## 📂 Project Structure

ATM-Simulator/  
├── src/  
│ ├── ATM.java // Main application class with user interface and menu  
│ ├── BankAccount.java // Class representing a bank account and its operations  
│ ├── Transaction.java // Class representing a single transaction (type and amount)  
│ └── IncorrectInputException.java // Custom exception for invalid menu input  
├── README.md // This file  
└── .gitignore // Optional Git ignore file  

## Notes
The program uses a dummy recipient account for fund transfers.

PIN change is immediate and affects only the current session.

Transaction history only persists during runtime (no file/database storage).

## 🛠 Future Improvements
✅ Store multiple user accounts using a HashMap

💾 Save transaction history using file or database

🖼️ Add a GUI using JavaFX or Swing

🔒 Lock account after 3 failed login attempts

⏳ Add timestamps to transaction records
