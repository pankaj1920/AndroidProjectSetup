//Core Plugin For Project Level Gradle
object CorePlugins {
    val Application by lazy { "com.android.application" }
    val Library by lazy { "com.android.library" }
    val Android by lazy { "org.jetbrains.kotlin.android" }
    val KotlinKapt by lazy { "kotlin-kapt" }

    val KotlinLint by lazy { "org.jlleitschuh.gradle.ktlint" }

}

object Lib {
    object AndroidX {
        val CoreKtx by lazy { "androidx.core:core-ktx:${Versions.CoreKtx}" }
        val AppCompact by lazy { "androidx.appcompat:appcompat:${Versions.AppCompact}" }
        val Material by lazy { "com.google.android.material:material:${Versions.Material}" }
        val Constraintlayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.Constraintlayout}" }
    }

    object Testing {
        val Junit by lazy { "junit:junit:${Versions.Junit}" }
        val JunitTest by lazy { "androidx.test.ext:junit:${Versions.JunitTest}" }
        val Espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.Espresso}" }
    }

    object Jetpack {
        val Coroutine by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.Coroutine}" }
        val Livedata by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Livedata}" }
        val Viewmodel by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Viewmodel}" }
        val NavigationFragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.NavigationFragment}" }
        val NavigationUi by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.NavigationUi}" }
        val Datastore by lazy { "androidx.datastore:datastore-preferences:${Versions.Datastore}" }
    }

    object Retrofit {
        val Gson by lazy { "com.google.code.gson:gson:${Versions.Gson}" }
        val Interceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.Interceptor}" }
        val Retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.Retrofit}" }
        val GsonConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.GsonConverter}" }
    }

    object Ext {
        val Glide by lazy { "com.github.bumptech.glide:glide:${Versions.Glide}" }
        val Lottie by lazy { "com.airbnb.android:lottie:${Versions.Lottie}" }
        val Sdp by lazy { "com.intuit.sdp:sdp-android:${Versions.Sdp}" }
        val Ssp by lazy { "com.intuit.ssp:ssp-android:${Versions.Ssp}" }
    }

    object ForceUpdate{
      val PlayCore by lazy { "com.google.android.play:core-ktx:${Versions.PlayCore}" }
    }
}

