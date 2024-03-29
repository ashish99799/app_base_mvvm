plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-android")
    id("maven-publish")
}

android {
    namespace = "com.application.base"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        version = "1.0.1"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    lint {
        abortOnError = false
        checkReleaseBuilds = false
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))        // << --- ADD This
    }
}

java {
    sourceCompatibility = JavaVersion.VERSION_11            // << --- ADD This
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    // Android X => AppCompat | Core | Support | Constraint
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

    // Material | Gson Google
    implementation("com.google.android.material:material:1.10.0")
    implementation("com.google.code.gson:gson:2.9.0")

    // Preference
    implementation("androidx.preference:preference-ktx:1.2.1")

    // Anko Kotlin
    implementation("org.jetbrains.anko:anko-commons:0.10.8")

    // Lifecycle => ViewModel | LiveData
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    // RxJava | RxAndroid
    implementation("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation("io.reactivex.rxjava2:rxandroid:2.1.1")

    implementation("com.google.firebase:firebase-crashlytics-buildtools:2.9.9")
    implementation("com.google.guava:guava:32.1.3-android")

    // Android Test
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // Jitpack
    implementation("com.github.jitpack:gradle-simple:1.1")
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.ashish99799"
            artifactId = "app_base_mvvm"
            version = "1.0.1"
            afterEvaluate {
                from(components["release"])
            }
        }
    }
}