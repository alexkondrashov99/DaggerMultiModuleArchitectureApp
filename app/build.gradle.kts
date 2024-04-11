plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
}

kapt {
    correctErrorTypes = true
}

android {
    namespace = "com.testarchitecture.dynamicfeatureapp"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.testarchitecture.dynamicfeatureapp"
        minSdk = 21
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        getByName("debug") {

        }

        create("beta") {

        }

        getByName("release") {
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
    dynamicFeatures += setOf(":dynamicfeature1")
    flavorDimensions += listOf("platform")

    productFlavors {
        create("google") {
            dimension = "platform"
        }
        create("huawei") {
            dimension = "platform"
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar", "*.aar"))))
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.google.android.play:core:1.3.4")
    /*************************
     *
     */
    implementation ("com.google.dagger:dagger:2.50")
    kapt ("com.google.dagger:dagger-compiler:2.50")
    api ("com.google.dagger:dagger-android-support:2.50")
    kapt ("com.google.dagger:dagger-android-processor:2.50")

    implementation(project(":Core"))
    implementation(project(":dashboard"))
    implementation(project(":feature1:api"))
    implementation(project(":feature1:presentation"))
    implementation(project(":feature1:impl"))
    implementation(project(":feature1:data"))
    implementation(project(":feature1:domain"))
}