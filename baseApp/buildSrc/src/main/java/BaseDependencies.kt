object BaseCorePlugins {
    val application by lazy {  "com.android.application"}
    val library by lazy {  "com.android.library"}
    val android_library by lazy {  "com.android.library"}
    val jetbrains by lazy {  "org.jetbrains.kotlin.android"}
    val kotlin_kapt by lazy { "kotlin-kapt" }
}

object BaseCoreLibs {
    val core by lazy {  "androidx.core:core-ktx:${BaseVersions.core}"}
    val appcompat by lazy {  "androidx.appcompat:appcompat:${BaseVersions.appcompat}"}
    val material by lazy {  "com.google.android.material:material:${BaseVersions.material}"}
    val constraintlayout by lazy { 
        "androidx.constraintlayout:constraintlayout:${BaseVersions.constraintlayout}"}
    val multidex by lazy {  "androidx.multidex:multidex:${BaseVersions.multidex}"}
    val legacy by lazy {  "androidx.legacy:legacy-support-${BaseVersions.legacy}"}
}

object BaseTestLibs {
    val junit by lazy {  "junit:junit:${BaseVersions.jUnit}"}
    val jUnitTest by lazy {  "androidx.test.ext:junit:${BaseVersions.jUnitTest}"}
    val espresso by lazy {  "androidx.test.espresso:espresso-core:${BaseVersions.espresso}"}
}

object BaseMvvmLibs {
    val coroutines by lazy {  "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BaseVersions.coroutines}"}
    val livedata by lazy {  "androidx.lifecycle:lifecycle-livedata-ktx:${BaseVersions.livedata}"}
    val viewmodel by lazy {  "androidx.lifecycle:lifecycle-viewmodel-ktx:${BaseVersions.viewmodel}"}
    val datastore by lazy {  "androidx.datastore:datastore-preferences:${BaseVersions.datastore}"}
    val navigation_fragment by lazy { 
        "androidx.navigation:navigation-fragment-ktx:${BaseVersions.navigation_fragment}"}
    val navigation_ui by lazy {  "androidx.navigation:navigation-ui-ktx:${BaseVersions.navigation_ui}"}
}

object BaseRetrofit {
    val gson by lazy {  "com.google.code.gson:gson:${BaseVersions.gson}"}
    val logging_interceptor by lazy { 
        "com.squareup.okhttp3:logging-interceptor:${BaseVersions.logging_interceptor}"}
    val retrofit by lazy {  "com.squareup.retrofit2:retrofit:${BaseVersions.retrofit}"}
    val converter_gson by lazy {  "com.squareup.retrofit2:converter-gson:${BaseVersions.converter_gson}"}
}

object BaseUtilLibs {
    val glide by lazy {  "com.github.bumptech.glide:glide:${BaseVersions.glide}"}
    val image_compressor by lazy {  "id.zelory:compressor:${BaseVersions.image_compressor}"}
    val lottie by lazy {  "com.airbnb.android:lottie:${BaseVersions.lottie}"}
}

object BaseDynamicSize {
    val sdp by lazy {  "com.intuit.sdp:sdp-android:${BaseVersions.sdp}"}
    val ssp by lazy {  "com.intuit.ssp:ssp-android:${BaseVersions.ssp}"}
}

