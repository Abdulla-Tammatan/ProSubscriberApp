plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.prosubscriberapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.prosubscriberapp"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// Define your version variables
val exposedLibraryVersion: String by project
val h2LibraryVersion: String by project

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation ("io.ktor:ktor-server-netty:2.3.7")
    implementation ("io.ktor:ktor-serialization:2.3.7")
    implementation ("mysql:mysql-connector-java:8.0.23")
    implementation("io.ktor:ktor-jackson:1.6.5")
    implementation ("io.ktor:ktor-server-core:2.3.7")
    implementation ("io.ktor:ktor-server-netty:2.3.7")
    implementation ("io.ktor:ktor-gson:2.3.7")
    implementation ("io.ktor:ktor-server-core:2.3.7")
    implementation ("io.ktor:ktor-server-netty:2.3.7")
    implementation ("io.ktor:ktor-locations:2.3.7")
    implementation ("io.ktor:ktor-features:2.3.7")
    implementation("com.google.apis:google-api-services-gmail:v1-rev110-1.31.0")
    implementation("com.google.api-client:google-api-client:1.31.0")
    implementation("com.google.oauth-client:google-oauth-client-jetty:1.31.0")
    implementation("com.google.apis:google-api-services-gmail:v1-rev110-1.31.0")
    implementation ("io.ktor:ktor-gson:1.7.4")
    implementation ("javax.sql:javax.sql-api:2.0.1")
    implementation ("org.jetbrains.exposed:exposed:0.33.2")
    implementation ("org.jetbrains.exposed:exposed-java-time:0.36.2")
    implementation("org.jetbrains.exposed:exposed-java-time:0.36.2")
    implementation("com.google.firebase:proto-lite-well-known-types:18.0.0")
    implementation ("mysql:mysql-connector-java:8.0.23")
    implementation ("org.jetbrains.exposed:exposed-core:0.41.1")
    implementation ("org.jetbrains.exposed:exposed-dao:0.41.1")
    implementation ("org.jetbrains.exposed:exposed-jdbc:0.41.1")
    implementation ("org.jetbrains.exposed:exposed:0.33.2")
    implementation ("org.jetbrains.exposed:exposed-java-time:0.36.2")
    implementation("org.jetbrains.exposed:exposed-java-time:0.36.2")
    implementation ("org.jetbrains.exposed:exposed-java-time:0.36.2")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.9.10")
    implementation("org.jetbrains.exposed:exposed-core:0.41.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.41.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.41.1")
    implementation("org.jetbrains.exposed:exposed-java-time:0.36.2")
    implementation("org.jetbrains.exposed:exposed-core:0.41.1")
    implementation("org.jetbrains.exposed:exposed-dao:0.41.1")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.41.1")
    implementation("mysql:mysql-connector-java:8.0.23")
    implementation("com.h2database:h2:2.1.214")
    implementation("com.jakewharton.threetenabp:threetenabp:1.3.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}