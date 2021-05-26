import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}
//apply(plugin = "com.google.firebase.crashlytics")
val localProperties = gradleLocalProperties(rootDir)

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = "com.buchi.movieapp"
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode(AppConfig.versionCode)
        versionName(AppConfig.versionName)

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "MOVIE_BASE_URL", localProperties.getProperty("MOVIE_BASE_URL"))
        }

        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "MOVIE_BASE_URL", localProperties.getProperty("MOVIE_BASE_URL"))
        }
    }

    flavorDimensions(AppConfig.dimension)
    productFlavors {
        create("staging") {
            applicationIdSuffix = ".staging"
            dimension(AppConfig.dimension)
        }

        create("production") {
            dimension(AppConfig.dimension)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        android.buildFeatures.viewBinding = true
    }
    hilt {
        enableTransformForLocalTests = true
    }
}

dependencies {
    // std lib
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    // app libs
    implementation(AppDependencies.appLibraries)
    kapt(AppDependencies.annotations)

    // test libs
    testImplementation(AppDependencies.testLibraries)
    androidTestImplementation(AppDependencies.androidTestLibraries)
    kaptAndroidTest(AppDependencies.testAnnotations)
    debugImplementation("androidx.fragment:fragment-testing:${Versions.fragment}")
    project(":core")
}