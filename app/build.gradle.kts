@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.ksp)
    alias(libs.plugins.dagger)
}

android {
    namespace = "co.ferman.saltweather"
    compileSdk = 34

    defaultConfig {
        applicationId = "co.ferman.saltweather"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    viewBinding.enable = true
    kotlinOptions {
        jvmTarget = "1.8"
    }
    hilt {
        enableAggregatingTask = false
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)
    implementation(libs.google.gson)
    implementation(libs.androidx.view.constraintlayout)
    implementation(libs.androidx.view.cardview)
    implementation(libs.androidx.view.recyclerview)
    implementation(libs.androidx.view.swiperefreshlayout)
    implementation(libs.androidx.view.viewpager)
    implementation(libs.androidx.lifecycle.livedata)
    implementation(libs.androidx.lifecycle.viewmodel)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp.core)
    implementation(libs.external.glide)
    implementation(libs.dagger.core)
    ksp(libs.dagger.compiler)
    debugImplementation(libs.okhttp.chucker)
    releaseImplementation(libs.okhttp.chuckerNoop)
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.junitAndroid)
    androidTestImplementation(libs.test.espresso)
}