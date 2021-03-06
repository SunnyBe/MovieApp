import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt
import java.util.Properties
import java.io.InputStreamReader
import java.io.FileInputStream
import java.io.File

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
}
//apply(plugin = "com.google.firebase.crashlytics")
val localProperties = gradleLocalProperties(rootDir)
val gradleProperties = GradleUtils.gradleProperties(rootDir, "gradle.properties")

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    defaultConfig {
        applicationId = "com.buchi.fullentry"
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
            buildConfigField("String", "MOVIE_BASE_URL", localProperties.alternateData(gradleProperties, "MOVIE_BASE_URL"))
            buildConfigField("String", "MOVIE_ACCESS_TOKEN", localProperties.alternateData(gradleProperties, "MOVIE_ACCESS_TOKEN"))
            buildConfigField("String", "MOVIE_API_KEY", localProperties.alternateData(gradleProperties, "MOVIE_API_KEY"))
        }

        getByName("debug") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "MOVIE_BASE_URL", localProperties.alternateData(gradleProperties, "MOVIE_BASE_URL"))
            buildConfigField("String", "MOVIE_ACCESS_TOKEN", localProperties.alternateData(gradleProperties, "MOVIE_ACCESS_TOKEN"))
            buildConfigField("String", "MOVIE_API_KEY", localProperties.alternateData(gradleProperties, "MOVIE_API_KEY"))
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

    packagingOptions {
        exclude("META-INF/DEPENDENCIES")
        exclude("META-INF/INDEX.LIST")
        exclude("META-INF/AL2.0")
        exclude("META-INF/LGPL2.1")
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
    kaptTest(AppDependencies.testAnnotations)
    kaptAndroidTest(AppDependencies.hiltCompiler)

    androidTestImplementation("com.google.dagger:hilt-android-testing:2.35")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.35")
    debugImplementation("androidx.fragment:fragment-testing:${Versions.fragment}")
    testImplementation("com.squareup.okhttp3:mockwebserver:4.9.1")

    implementation(project(":core"))
}