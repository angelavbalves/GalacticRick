import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "1.5.21"
    id("de.jensklingenberg.ktorfit") version "2.0.0"
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(libs.kotlin.serialization.json)
                implementation(libs.kotlin.coroutines.core)
                implementation(libs.ktor.client.core)
                implementation(libs.ktorfit.lib)
                implementation(libs.kodein.core)
                implementation("io.ktor:ktor-client-content-negotiation:2.3.12")
                implementation("io.ktor:ktor-client-json:2.3.12")
                implementation("io.ktor:ktor-client-logging:2.3.12")
                implementation("org.kodein.di:kodein-di:7.3.1")

            }
        }

        val androidMain by getting {
            dependencies {
                implementation("cafe.adriel.voyager:voyager-navigator:1.1.0-beta02")
                implementation("cafe.adriel.voyager:voyager-screenmodel:1.1.0-beta02")
                implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:1.1.0-beta02")
                implementation("cafe.adriel.voyager:voyager-tab-navigator:1.1.0-beta02")
                implementation("cafe.adriel.voyager:voyager-transitions:1.1.0-beta02")
            }
        }

        val iosMain by creating {
            dependsOn(commonMain)
        }
        val iosX64Main by getting {
            dependsOn(iosMain)
        }
        val iosArm64Main by getting {
            dependsOn(iosMain)
        }
        val iosSimulatorArm64Main by getting {
            dependsOn(iosMain)
        }
    }
}

android {
    namespace = "org.angelavbalves.br.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}
