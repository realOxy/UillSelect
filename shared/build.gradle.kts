plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("org.jetbrains.compose")
    id("app.cash.sqldelight")
    id("com.google.devtools.ksp")
    id("kotlinx-serialization")
    id("de.jensklingenberg.ktorfit")
    id("kotlin-parcelize")
}

val ktorVersion = extra["ktor.version"] as String
val ktorfitVersion = extra["ktorfit.version"] as String
val decomposeVersion = extra["decompose.version"] as String
val sqldelightVersion = extra["sqldelight.version"] as String

configure<de.jensklingenberg.ktorfit.gradle.KtorfitGradleConfiguration> {
    version = ktorfitVersion
}

kotlin {
    androidTarget()

    jvm("desktop")

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                // sqldelight
                implementation("app.cash.sqldelight:coroutines-extensions:$sqldelightVersion")

                // ktorfit
                implementation("de.jensklingenberg.ktorfit:ktorfit-lib:$ktorfitVersion")
                implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")

                // decompose
                api("com.arkivanov.decompose:decompose:$decomposeVersion")
                api("com.arkivanov.decompose:extensions-compose-jetbrains:$decomposeVersion")

                // datastore
                implementation("androidx.datastore:datastore-preferences-core:1.1.0-alpha04")
                implementation("androidx.datastore:datastore-core-okio:1.1.0-alpha04")

                // kotinx-io
                implementation("org.jetbrains.kotlinx:kotlinx-io-core:0.2.1")

                // kotlinx-datetime
                implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.6.1")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.9.0")

                implementation("app.cash.sqldelight:android-driver:$sqldelightVersion")
            }
        }
        val desktopMain by getting {
            dependencies {
                implementation(compose.desktop.common)
                implementation("app.cash.sqldelight:sqlite-driver:$sqldelightVersion")
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.oxy.uillselect"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}

sqldelight {
    databases {
        create("Database") {
            packageName.set("com.oxy.uillselect")
        }
    }
}

dependencies {
    add("kspCommonMainMetadata", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
    add("kspDesktop", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
    add("kspAndroid", "de.jensklingenberg.ktorfit:ktorfit-ksp:$ktorfitVersion")
}