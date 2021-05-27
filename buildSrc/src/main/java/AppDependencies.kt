import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {
    // std lib
    private const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"

    // android ui
    private const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private const val material = "com.google.android.material:material:${Versions.material}"
    private const val swipeRefresh = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}"

    // api call
    private const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private const val retrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"

    private const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLogging}"

    // Co
    private const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    private const val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
    private const val gson = "com.google.code.gson:gson:${Versions.gson}"
    private const val room = "androidx.room:room-runtime:${Versions.room}"
    private const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    private const val fragmentKtx = "androidx.fragment:fragment-ktx:1.2.5"
    private const val activityKtx = "androidx.activity:activity-ktx:1.1.0"

    // Image rendering
    private const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    // test libs
    private const val junit = "junit:junit:${Versions.junit}"
    private const val mocktio = "org.mockito.kotlin:mockito-kotlin:${Versions.mockito}"
    private const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTest}"
    private const val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private const val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    private const val espressoIntent = "androidx.test.espresso:espresso-intents:${Versions.espresso}"
    private const val espressoIdling = "androidx.test.espresso:espresso-idling-resource:${Versions.espresso}"
    private const val runner = "androidx.test:runner:1.3.0"
    private const val rules = "androidx.test:rules:1.3.0"
    private const val roomTest = "androidx.room:room-testing:${Versions.room}"
    private const val coroutinesTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    private const val turbine = "app.cash.turbine:turbine:${Versions.turbine}"
    private const val navTest = "androidx.navigation:navigation-testing:${Versions.navigation}"
    private const val hiltAndroidTest = "com.google.dagger:hilt-android-testing:2.33-beta"

    // Annotations
    private const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
    private const val hiltXCompiler = "androidx.hilt:hilt-compiler:${Versions.hilt}"
    private const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    val appLibraries = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(coreKtx)
        add(appcompat)
        add(constraintLayout)
        add(material)
        add(stetho)
        add(retrofit)
        add(retrofitConverter)
        add(okhttpLogging)
        add(coroutinesAndroid)
        add(gson)
        add(hilt)
        add(room)
        add(fragmentKtx)
        add(activityKtx)
        add(glide)
        add(swipeRefresh)
    }

    val coreLibraries = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(coreKtx)
        add(appcompat)
        add(constraintLayout)
        add(material)
        add(stetho)
        add(retrofit)
        add(okhttpLogging)
        add(coroutinesAndroid)
        add(gson)
        add(hilt)
        add(room)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
        add(espressoIntent)
        add(roomTest)
        add(runner)
        add(rules)
        add(coroutinesTest)
        add(hiltAndroidTest)
        add(navTest)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
        add(mocktio)
        add(coreTesting)
        add(coroutinesTest)
        add(turbine)
    }

    val annotations = arrayListOf<String>().apply {
        add(roomCompiler)
        add(hiltCompiler)
        add(glideCompiler)
    }
    val testAnnotations = arrayListOf<String>().apply {
        add(hiltCompiler)
    }
}

// util functions for adding the different type dependencies from build.gradle file
fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.api(list: List<String>) {
    list.forEach { dependency->
        add("api", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}

fun DependencyHandler.kaptAndroidTest(list: List<String>) {
    list.forEach { dependency ->
        add("kaptAndroidTest", dependency)
    }
}

fun DependencyHandler.kaptTest(list: List<String>) {
    list.forEach { dependency ->
        add("kaptTest", dependency)
    }
}
