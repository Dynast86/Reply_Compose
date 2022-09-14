import com.dynast.replycompose.buildsrc.Libs

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.dynast.replycompose"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.dynast.replycompose"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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
        kotlinCompilerExtensionVersion = "1.1.1"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    kotlin.sourceSets.all{
        languageSettings.optIn("kotlin.RequiresOptIn")
    }
}

dependencies {

    implementation(Libs.AndroidX.coreKtx)

    implementation(Libs.AndroidX.Compose.Ui.ui)
    implementation(Libs.AndroidX.Compose.Ui.uiToolingPreview)
    implementation(Libs.AndroidX.Compose.Ui.uiFont)
    debugImplementation(Libs.AndroidX.Compose.Ui.uiTooling)
    debugImplementation(Libs.AndroidX.Compose.Ui.uiTestManifest)
    androidTestImplementation(Libs.AndroidX.Compose.Ui.uiTestJUnit)

    implementation(Libs.AndroidX.Lifecycle.runtime)
    implementation(Libs.AndroidX.Activity.activityCompose)
    implementation(Libs.AndroidX.PlayServices.playServiceLocation)
    implementation(Libs.AndroidX.PlayServices.playServiceAuth)
    testImplementation(Libs.JUnit.junit)
    androidTestImplementation(Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(Libs.AndroidX.Test.espressoCore)

    // 머티리얼 테마
    implementation(Libs.AndroidX.Compose.material)
    implementation(Libs.AndroidX.Compose.material3)
    // 네비게이션
    implementation(Libs.AndroidX.Navigation.navigation)

    // Hilt
    kapt(Libs.Hilt.compiler)
    kapt(Libs.AndroidX.Hilt.compiler)
    implementation(Libs.AndroidX.Hilt.common)
    implementation(Libs.AndroidX.Hilt.navigationCompose)
    implementation(Libs.Hilt.android)

    // Coil
    implementation(Libs.Coil.coilCompose)

    // DataStore
    implementation(Libs.DataStore.datastore)

    // Retrofit2
    implementation(Libs.Retrofit.retrofit)
    implementation(Libs.Retrofit.converter)
    implementation(Libs.OkHttp.okhttp)
    implementation(Libs.OkHttp.logging)
    implementation(Libs.gson)

    // Room
    implementation(Libs.AndroidX.Room.runtime)
    annotationProcessor(Libs.AndroidX.Room.compiler)
    kapt(Libs.AndroidX.Room.compiler)
    implementation(Libs.AndroidX.Room.ktx)

    // Paging
    implementation(Libs.AndroidX.Paging.pagingCompose)

    // Window
    implementation(Libs.AndroidX.Window.window)

    // Lottie
    implementation(Libs.Lottie.lottie)
    implementation(Libs.Lottie.lottieCompose)

    implementation(Libs.Accompanist.Pager.pager)
    implementation(Libs.Accompanist.Pager.pagerIndicators)
    implementation(Libs.Accompanist.SwipeRefresh.swipe)
}