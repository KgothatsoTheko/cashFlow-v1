# CashFlow Application

An Android application designed to help users track their income, expenses, and savings goals with ease. The app integrates a RESTful API to handle backend operations and allows users to manage their financial data securely and efficiently. The database used is MongoDB.

## Purpose

This app provides a user-friendly platform to monitor and control personal finances. Users can track their daily transactions, set savings goals, and get real-time reminders to help them manage everyday finances. The app ensures data security with SSO authentication, biometric authentication, and supports a range of customization options, including language, currency, and notifications.

## Features

- **Single Sign-On (SSO):** Users can log in using their preferred social media accounts (Google, Facebook, etc.) for a smooth authentication experience.
- **Biometric Authentication:** Protect sensitive financial data by enabling fingerprint or face recognition login.
- **User Settings:**
  - Customize currency for financial tracking.
  - Change the language of the app, supporting English and another South African language like Xhosa or Afrikaans.
  - Notification preferences for alerts and reminders.
- **REST API Integration:** CRUD operations are supported via a Node.js backend with MongoDB storage. Users can:
  - **Create:** Add new transactions (income/expense).
  - **Read:** View transaction history and generate reports.
  - **Update:** Edit existing transactions or update savings goals.
  - **Delete:** Remove outdated or incorrect financial records.
- **Offline Mode:** Users can continue to add transactions even without internet connectivity. The app will automatically sync data with the server when reconnected.
- **Real-time Notifications:** Receive alerts about low balances, upcoming bills, or savings milestones.
- **Multi-language Support:** The app is available in both English and another local South African language (e.g. Xhosa or Afrikaans).

## Tech Stack

### Backend

- **Node.js:** Handles API requests and serves data to the Android app.
- **MongoDB:** Stores user financial data and transaction history.

### Android

- **Kotlin:** Main programming language for building the app.
- **Retrofit:** HTTP client for making network requests to the backend REST API.
- **Room Database:** Used for local storage and offline data persistence.
- **Jetpack Navigation Component:** For handling fragment navigation.
- **Firebase Authentication:** Manages user authentication through SSO.

### Development Tools

- **Postman:** Used for testing and debugging API endpoints during development.
- **Android Studio:** IDE for Android development.
- **MongoDB Atlas:** Cloud-based storage for the app's backend data.

## How It Works

### User Registration and Authentication:

- Users sign in via their social media account using Firebase Authentication.
- Biometric authentication can be enabled for subsequent logins to secure access.

### Managing Financial Data:

- Users add income or expenses by filling in relevant details (amount, date, category, description).
- Data is saved both locally for offline access and synced with the backend when connected.

### Customizing Settings:

- Users can set their preferred currency for transactions.
- Language settings can be switched between English and a local South African language.
- Notifications are configurable to remind users about financial goals or upcoming bills.

### Data Synchronization:

- In offline mode, transactions are stored locally in the Room database.
- When an internet connection is restored, the app syncs data with the server to keep the user’s financial records updated.

### Real-time Notifications:

- The app notifies users of important events such as low balances or bills due soon.
- These notifications are handled via Firebase Cloud Messaging (FCM).

## API Endpoints

The following are the key REST API endpoints:

- **POST** `/add-items`: Create a new transaction.
- **GET** `/get-items`: Fetch all transactions for the authenticated user.
- **PUT** `/find-items/:id`: Update a specific transaction.
- **DELETE** `/delete-item/:id`: Delete a transaction.

## Future Enhancements

- **Budgeting Tools:** Add features for monthly budgeting and automated savings suggestions.
- **Data Visualization:** Provide charts and graphs to help users visualize their spending trends.
- **More Languages:** Expand multi-language support to include more South African languages like Zulu and Sotho.

## User Interface:

![one](https://github.com/user-attachments/assets/dc076962-1d95-4c3d-98ec-43b1091e77c8)

![two](https://github.com/user-attachments/assets/6d05e73a-7ec9-4258-adc0-055a660f0f76)

![three](https://github.com/user-attachments/assets/786e87d2-3aa9-474c-90d4-defe3208a123)

![four](https://github.com/user-attachments/assets/cb4acdf1-38f9-44fa-880f-d7150970238c)

![five](https://github.com/user-attachments/assets/cd983f58-7569-404a-a84e-31cef5a7d04f)

![six](https://github.com/user-attachments/assets/3b006239-67f6-43a7-b43b-cd02596ee0f2)
