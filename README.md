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
- **Multi-language Support:** The app is available in both English and another local South African language (e.g. Zulu or Afrikaans).

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

- The app notifies users of when a new transaction has been added.
- These notifications are handled via Firebase Cloud Messaging (FCM).

## API Endpoints

- Live environment - https://crud-a-pi.vercel.app/
  
The following are the key REST API endpoints:

- **POST** `/add-items`: Create a new transaction.
- **GET** `/get-items`: Fetch all transactions for the authenticated user.
- **PUT** `/find-items/:id`: Update a specific transaction.
- **DELETE** `/delete-item/:id`: Delete a transaction.

## Future Enhancements

- **Budgeting Tools:** Provides insightful budgeting tips.
- **Data Visualization:** Provide charts and graphs to help users visualize their spending trends.
- **More Languages:** Expand multi-language support to include more South African languages like Zulu and Sotho.

## User Interface:

![one](https://github.com/user-attachments/assets/151ceeba-613d-44aa-9352-867047b669dd)

![two](https://github.com/user-attachments/assets/787115e2-11e7-4b4e-ba68-5a1dfee6a5f4)

![three](https://github.com/user-attachments/assets/b7cc240c-6340-4ba3-ba2d-8f63c5dad869)

![four](https://github.com/user-attachments/assets/fa110153-8d6b-4543-a668-cf637f7d3fae)

![five](https://github.com/user-attachments/assets/2c294c34-4d47-4e02-bd2d-07685695749c)

![six](https://github.com/user-attachments/assets/5a3d12d9-b4d5-4761-a2e2-d3a46b0d883b)

![seven](https://github.com/user-attachments/assets/c4ba04a7-9e8a-4c08-a342-23970f25549c)

![eight](https://github.com/user-attachments/assets/1a30b73c-50e9-4b4c-b0f1-5b9952c68a66)

## Release/v1 - (POE - New Features)

- Completed transactions page and functinality (showing the income/expense amount).
- Added real time push notifications, when new transaction is made, push notification is sent to clients subscribed.
- Added biometric authentication (fingerprint login).
- Added multi-language feature.
- Added budgeting tips functionality and UI.
- Added sign out feature in settings
