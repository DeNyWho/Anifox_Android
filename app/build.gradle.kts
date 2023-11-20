import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktlint)
}

val hostnameApi: String = gradleLocalProperties(rootDir).getProperty("hostname_api")
val certPath: String = gradleLocalProperties(rootDir).getProperty("cert_path")
val certAlias: String = gradleLocalProperties(rootDir).getProperty("cert_alias")

android {
    namespace = "club.anifox.android"
    compileSdk = 34

    defaultConfig {
        applicationId = "club.anifox.android"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            buildConfigField("String", "cert_alias", "\"${certAlias}\"")
            buildConfigField("String", "cert_path", "\"${certPath}\"")
            buildConfigField("String", "hostname_api", "\"${hostnameApi}\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/INDEX.LIST"
        }
    }
}

dependencies {

    // Ktor client
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.apache)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.json)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.content.negotiation)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.logging.jvm)
    implementation(libs.slf4j.simple)

    // Room
    implementation(libs.room.paging)
    implementation(libs.room)
    ksp(libs.room.complier)

    implementation(libs.mobile.native.foundation.store)
    implementation(libs.atomicfu)

    // Aaccompanist
    implementation(libs.accompanist.systemuicontroller)

    implementation(libs.paging)

    implementation(libs.toolbar.compose)
    implementation(libs.charty)
    implementation(libs.navigation)
    implementation(libs.serialization.json)
    implementation(libs.shimmer.compose)
    implementation(libs.coil)
    implementation(libs.coroutines)
    implementation(libs.datastore)
    implementation(libs.datastore.preferences)
    implementation(libs.koin.compose)
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    implementation(libs.icons.extened)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}