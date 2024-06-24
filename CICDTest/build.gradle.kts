@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    id("maven-publish")
}

afterEvaluate {
    publishing {
        publications {
            val versionName = "0.0.3"

            create("debug", MavenPublication::class) {
                artifactId = "ci-cd-test-debug"
                version = "${versionName}-debug"
                from(components["debug"])
            }

            create("release", MavenPublication::class) {
                artifactId = "ci-cd-test-release"
                version = "${versionName}-release"
                from(components["release"])
            }

            create("qa", MavenPublication::class) {
                artifactId = "ci-cd-test-qa"
                version = "${versionName}-qa"
                from(components["qa"])
            }

//            android.libraryVariants.forEach { variant ->
//                val buildType = variant.buildType.name
//
//                register<MavenPublication>(buildType) {
//                    artifactId = "ci-cd-test-${buildType.capitalized()}"
//                    version = "0.0.3-${buildType.capitalized()}"
//                    from(components[buildType])
//                }
//            }
        }
    }
}

android {
    namespace = "com.example.cicdtest"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        debug {
            isMinifyEnabled = false
        }

        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        create("qa") {
            initWith(buildTypes["debug"])
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
}