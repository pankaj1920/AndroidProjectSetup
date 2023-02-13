plugins {
    id(CorePlugins.Library)
    id(CorePlugins.Android)
    id(CorePlugins.KotlinKapt)

}

android {

    compileSdk = ConfigData.compileSdkVersion
    defaultConfig {
        minSdk = ConfigData.minSdkVersion
        targetSdk = ConfigData.targetSdkVersion
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

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }


}

dependencies {

    api(Lib.AndroidX.CoreKtx)
    api(Lib.AndroidX.AppCompact)
    api(Lib.AndroidX.Material)
    api(Lib.AndroidX.Constraintlayout)
    api(Lib.Testing.Junit)
    api(Lib.Testing.JunitTest)
    api(Lib.Testing.Espresso)

    //Jetpack
    api(Lib.Jetpack.Coroutine)
    api(Lib.Jetpack.Livedata)
    api(Lib.Jetpack.Viewmodel)
    api(Lib.Jetpack.NavigationFragment)
    api(Lib.Jetpack.NavigationUi)
    api(Lib.Jetpack.Datastore)

    //Retrofit
    api(Lib.Retrofit.Gson)
    api(Lib.Retrofit.Interceptor)
    api(Lib.Retrofit.Retrofit)
    api(Lib.Retrofit.GsonConverter)


    //External Library
    api(Lib.Ext.Glide)
    api(Lib.Ext.Lottie)
    api(Lib.Ext.Sdp)
    api(Lib.Ext.Ssp)

    //Force Update
    api(Lib.ForceUpdate.PlayCore)


}

