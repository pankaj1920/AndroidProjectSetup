import ConfigData.namespace
import ConfigData.testInstrumentationRunner
import ConfigData.versionCode
import ConfigData.versionName

plugins {
    id(CorePlugins.Application)
    id(CorePlugins.Android)
    id(CorePlugins.KotlinKapt)
    id(CorePlugins.KotlinLint) version Versions.KotlinLint
}

android {
    namespace = ConfigData.namespace
    compileSdk = ConfigData.compileSdkVersion

    defaultConfig {
        applicationId = ConfigData.appId
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
        versionCode = ConfigData.versionCode
        versionName = ConfigData.versionName

        testInstrumentationRunner = ConfigData.testInstrumentationRunner
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    ktlint {
        android.set(true)
        debug.set(true)
        ignoreFailures.set(true)
        outputToConsole.set(true)
        outputColorName.set("RED")
        enableExperimentalRules.set(true)
        disabledRules.addAll(
            mutableListOf(
                "final-newline",
                "no-wildcard-imports"

            )
        )
        reporters {
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.PLAIN)
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.CHECKSTYLE)
            reporter(org.jlleitschuh.gradle.ktlint.reporter.ReporterType.SARIF)
        }
    }
}

tasks.getByPath("preBuild").dependsOn("ktlintFormat")

dependencies {

    api(Lib.AndroidX.CoreKtx)
    api(Lib.AndroidX.AppCompact)
    api(Lib.AndroidX.Material)
    api(Lib.AndroidX.Constraintlayout)
    api(Lib.Testing.Junit)
    api(Lib.Testing.JunitTest)
    api(Lib.Testing.Espresso)
    api(project(path = ConfigData.BaseApp))
}