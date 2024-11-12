plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlinx.serialization)
}

val javaVersion = JavaVersion.VERSION_17

android {
    namespace = "org.matthias.fetchtakehome"
    compileSdk = 35

    defaultConfig {
        applicationId = "org.matthias.fetchtakehome"
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
    compileOptions {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }
    kotlinOptions {
        jvmTarget = javaVersion.toString()
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    platform(libs.compose.bom).let {
        implementation(it)
        androidTestImplementation(it)
    }
    implementation(libs.bundles.compose)

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.compose)

    // Serialization and network dependencies
    implementation(libs.serialization)
    implementation(libs.bundles.ktor.client)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.coroutines.test)

    androidTestImplementation(libs.bundles.android.test)

    debugImplementation(libs.bundles.debug)
}