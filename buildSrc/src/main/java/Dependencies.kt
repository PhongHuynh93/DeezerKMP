object Versions {
    const val kotlin = "1.5.10"
    const val kotlinCoroutines = "1.5.0-native-mt"
    const val gradlePlugin: String = "4.1.1"
    const val glide: String = "4.10.0"
    const val supportLibrary: String = "1.1.0"
    const val material: String = "1.2.1"
    const val constraintLayout: String = "2.0.4"
    const val ktor = "1.6.1"
    const val kotlinxSerialization = "1.2.1"
    const val injection = "3.1.2"
}

object Libs {
    object Android {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.supportLibrary}"
        const val material = "com.google.android.material:material:${Versions.material}"
        const val recyclerView = "androidx.recyclerview:recyclerview:1.2.0-alpha06"
        const val swipe = "androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01"
        const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
        const val viewPager = "androidx.viewpager2:viewpager2:1.0.0"
        const val page = "androidx.paging:paging-runtime-ktx:3.0.0-alpha06"
        const val core = "androidx.core:core-ktx:1.3.0"
        const val fragment = "androidx.fragment:fragment-ktx:1.2.5"
        const val lifeCycle = "androidx.lifecycle:lifecycle-runtime-ktx:2.2.0"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:2.2.0"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0"
        const val palette = "androidx.palette:palette-ktx:1.0.0"
    }
    object Kotlin {
        const val std = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    }
    object Injection {
        const val core = "io.insert-koin:koin-core:${Versions.injection}"
        object Android {
            const val android = "io.insert-koin:koin-android:${Versions.injection}"
        }
    }
    object Test {
        const val junit = "junit:junit:4.12"
        const val runner = "com.android.support.test:runner:1.0.2"
        const val espresso = "com.android.support.test.espresso:espresso-core:3.0.2"
    }
    object Glide {
        const val glide1 = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glide2 = "com.github.bumptech.glide:compiler:${Versions.glide}"
        const val glideTransform = "jp.wasabeef:glide-transformations:4.0.0"
    }
    object Helper {
        const val log = "com.jakewharton.timber:timber:4.7.1"
        const val lottie = "com.airbnb.android:lottie:3.0.1"
        const val multidex = "androidx.multidex:multidex:2.0.1"
        const val playCore = "com.google.android.play:core:1.6.4"
        const val skeleton = "io.supercharge:shimmerlayout:2.1.0"
        const val workManager = "androidx.work:work-runtime-ktx:2.4.0"
    }
    object Thread {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
        const val coreAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions
            .kotlinCoroutines}"
    }
    object Network {
        //        https://ktor.io/docs/http-client-multiplatform.html#samples
        const val core = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val json = "io.ktor:ktor-client-json:${Versions.ktor}"
        const val android = "io.ktor:ktor-client-android:${Versions.ktor}"
        const val ios = "io.ktor:ktor-client-ios:${Versions.ktor}"

        // log
        const val logCore = "io.ktor:ktor-client-logging:${Versions.ktor}"
        const val logCore2 = "ch.qos.logback:logback-classic:1.2.3"

        // proguard for android
//        https://github.com/Kotlin/kotlinx.serialization#android
        const val parser = "io.ktor:ktor-client-serialization:${Versions.ktor}"
    }
}

object Configs {
    const val minSdk = 21
    const val compileSdk = 29
    const val targetSdk = 29

    const val androidApplicationId = "com.wind.deezerkmp.androidApp"
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Plugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "android"
    const val kapt = "kapt"
    const val serialization = "kotlinx-serialization"
    const val kotlinParcel = "kotlin-parcelize"
}

object ClassPaths {
    const val gradlePlugin = "com.android.tools.build:gradle:${Versions.gradlePlugin}"
    const val kotlinPlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val serialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
}