plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.google.gms.google-services")
}

android {
    namespace = "za.co.varsitycollege.st10092141.cashflow_v1"
    compileSdk = 34

    defaultConfig {
        applicationId = "za.co.varsitycollege.st10092141.cashflow_v1"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    // enable view binding
    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    // ViewModel
    implementation (libs.androidx.lifecycle.lifecycle.viewmodel.ktx)  // Use the latest version

    // Other necessary AndroidX dependencies
    implementation (libs.androidx.fragment.ktx)

    implementation (libs.androidx.lifecycle.runtime.ktx)
    // Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    // Coroutines for asynchronous operations
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.coroutines.android)
    // Optional: OkHttp Logging Interceptor for debugging
    implementation(libs.logging.interceptor)
    implementation (libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.recyclerview)

    // Import the BoM for the Firebase platform
    implementation(platform(libs.firebase.bom))
    implementation (libs.play.services.auth)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.preference)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}