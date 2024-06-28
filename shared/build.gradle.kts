import org.jetbrains.kotlin.cli.jvm.main
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
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
                // Adicione suas dependências comuns aqui
            }
        }

        val androidMain by getting {
            dependencies {
                val voyagerVersion = "1.1.0-beta02"

                // Multiplatform

                // Navigator
                implementation("cafe.adriel.voyager:voyager-navigator:$voyagerVersion")

                // Screen Model
                implementation("cafe.adriel.voyager:voyager-screenmodel:$voyagerVersion")

                // BottomSheetNavigator
                implementation("cafe.adriel.voyager:voyager-bottom-sheet-navigator:$voyagerVersion")

                // TabNavigator
                implementation("cafe.adriel.voyager:voyager-tab-navigator:$voyagerVersion")

                // Transitions
                implementation("cafe.adriel.voyager:voyager-transitions:$voyagerVersion")
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