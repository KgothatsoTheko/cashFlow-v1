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

### GitHub Workflow: Generate APK & AAB
This GitHub Actions workflow automates the process of building APK (Android Package) and AAB (Android App Bundle) files for the cashFlow Android application. It builds and stores these artifacts on GitHub whenever changes are pushed to any release/** branch. Additionally, the workflow can be triggered manually from the Actions tab.

## Workflow Configuration
- **Workflow Name: Generate APK & AAB (Upload - Create Artifact To GitHub Action)
- **Environment Variables:
main_project_module: The main module name (app) of the project.
playstore_name: The identifier name for the app on the Play Store (cashFlow).
-Triggers
The workflow is triggered on:

- **Push Events: When changes are pushed to branches matching the release/** pattern (e.g., release/v1, release/v2).
- **Manual Trigger: It can be run manually from the Actions tab for ad-hoc builds.

## How to Download the Latest APK for the CashFlow App

Every time we release a new version of the CashFlow app, a build process is triggered to automatically generate and store the APK file on GitHub. Here’s how you, as an end user, can download the latest APK:

1. **Go to the GitHub Repository**: Navigate to the main repository page where the app's code and releases are hosted.

2. **Select the "Actions" Tab**: In the repository menu, click on the **Actions** tab. This is where all automated workflows, including the APK generation, are stored.

3. **Find the Latest Workflow Run**: Look for the latest workflow run labeled **"Generate APK & AAB"** on a `release/*` branch. This will indicate a recent release or update.

4. **Open the Workflow Details**: Click on the workflow run to view more details.

5. **Download the APK Artifact**:
   - Scroll down to the **Artifacts** section within the workflow details.
   - Look for the file labeled something like: `[Date] - cashFlow - [version] - APK(s) debug/release generated`.
   - Click on the artifact link to download the APK file directly to your device.

6. **Install the APK**: Once downloaded, open the APK file on your Android device and follow the prompts to install the latest version of CashFlow.

This process ensures that you have access to the latest, verified version of the app directly from our secure GitHub repository.


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
