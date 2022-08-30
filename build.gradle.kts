@file:Suppress("UNUSED_VARIABLE")

plugins {
    kotlin("multiplatform") version "1.7.20-Beta"
    kotlin("plugin.serialization") version "1.7.20-Beta"
}

group = "net.henryhc"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {

    targets.all {
        compilations.all {
            kotlinOptions {
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-opt-in=kotlin.RequiresOptIn",
                    "-Xextended-compiler-checks",
//                    "-Xcontext-receivers",
//                    "-Xuse-k2",
                )
            }
        }
    }

    jvm {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
                freeCompilerArgs = freeCompilerArgs + listOf(
                    "-Xbackend-threads=0",
                    "-Xcontext-receivers",
                )
            }
        }
        withJava()
        testRuns["test"].executionTask.configure {
            useJUnitPlatform()
        }
    }

//    val hostOs = System.getProperty("os.name")
//    val arch = System.getProperty("os.arch")
//    val nativeTargetName = "native"
//    val nativeTarget = when (hostOs) {
//        "Mac OS X" -> when (arch) {
//            "aarch64" -> macosArm64(nativeTargetName)
//            "x86_64" -> macosX64(nativeTargetName)
//            else -> throw GradleException("Architecture $arch is not supported on macOS")
//        }
//
//        "Linux" -> when (arch) {
//            "aarch64" -> linuxArm64(nativeTargetName)
//            "amd64" -> linuxX64(nativeTargetName)
//            else -> throw GradleException("Architecture $arch is not supported on Linux")
//        }
//
//        else -> throw GradleException("Host OS is not supported.")
//    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                implementation("io.arrow-kt:arrow-core:1.1.2")
                implementation("io.arrow-kt:arrow-fx-coroutines:1.1.2")
                implementation("io.arrow-kt:arrow-fx-stm:1.1.2")
                implementation("com.github.ajalt.clikt:clikt:3.5.0")
                implementation("com.squareup.okio:okio:3.2.0")
                implementation("io.github.microutils:kotlin-logging:2.1.23")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-core:1.4.0")
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json-okio:1.4.0")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation("com.squareup.okio:okio-fakefilesystem:3.2.0")
                implementation("io.mockk:mockk:1.12.7")
            }
        }
        val jvmMain by getting
        val jvmTest by getting
//        val nativeMain by getting
//        val nativeTest by getting
    }
}
