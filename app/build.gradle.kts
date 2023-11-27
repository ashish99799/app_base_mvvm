plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-parcelize")
    id("kotlin-kapt")
}

android {
    namespace = "com.base.app.testing"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        applicationId = "com.base.app.testing"
        minSdk = 24
        targetSdk = 34
        versionCode = 2
        versionName = "1.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    // Add .jar and .aar files in project.
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))

    // Project Library
    implementation(project(mapOf("path" to ":app_base")))

    // AndroidX => AppCompat | Core | Support | Constraint Layout
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.work:work-runtime:2.8.1")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // AndroidX => Activity | Fragment
    implementation("androidx.activity:activity-ktx:1.8.1")
    implementation("androidx.fragment:fragment-ktx:1.6.2")

    // AndroidX => Navigation Component
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")

    // AndroidX => Lifecycle for LiveData & ViewModel
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")

    // KotlinX => Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.1")

    // AndroidX => Material Support dependencies
    implementation("com.google.android.material:material:1.10.0")

    // Multi Dex
    implementation("com.android.support:multidex:1.0.3")

    // Anko Kotlin
    implementation("org.jetbrains.anko:anko-commons:0.10.8")

    // Shared Preferences
    implementation("com.chibatching.kotpref:kotpref:2.13.1")
    implementation("com.chibatching.kotpref:gson-support:2.13.1")

    // Smart Refresh Layout
    implementation("com.scwang.smartrefresh:SmartRefreshLayout:1.0.3")
    implementation("com.scwang.smartrefresh:SmartRefreshHeader:1.0.3")

    // Animation => Spinner & Lottie
    implementation("com.github.ybq:Android-SpinKit:1.4.0")
    implementation("com.airbnb.android:lottie:4.1.0")

    // Dynamic Layout => SDP | SSP
    implementation("com.intuit.sdp:sdp-android:1.0.6")
    implementation("com.intuit.ssp:ssp-android:1.0.6")

    // Image Loading => Glide | Glide-Transformations
    implementation("com.github.bumptech.glide:glide:4.12.0")
    implementation("jp.wasabeef:glide-transformations:4.3.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.12.0")

    // Room DB | Kotlin Extensions and Coroutines support for Room
    implementation("androidx.room:room-ktx:2.6.0")
    implementation("androidx.room:room-runtime:2.6.0")
    ksp("androidx.room:room-compiler:2.6.0")

    // Rx Android Networking
    implementation("com.amitshekhar.android:rx2-android-networking:1.0.2")

    // Android Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}