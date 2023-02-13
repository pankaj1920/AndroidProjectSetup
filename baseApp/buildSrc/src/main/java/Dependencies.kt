object CorePlugins {
    val application by lazy { "com.android.application" }
    val library by lazy { "com.android.library" }
    val android_library by lazy { "com.android.library" }
    val jetbrains by lazy { "org.jetbrains.kotlin.android" }
    val kotlin_kapt by lazy { "kotlin-kapt" }
}

object CoreLibs {
    val core by lazy { "androidx.core:core-ktx:${Versions.core}" }
    val appcompat by lazy { "androidx.appcompat:appcompat:${Versions.appcompat}" }
    val material by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraintlayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintlayout}" }
    val multidex by lazy { "androidx.multidex:multidex:${Versions.multidex}" }
}


object TestLibs {
    val junit by lazy { "junit:junit:${Versions.jUnit}" }
    val jUnitTest by lazy { "androidx.test.ext:junit:${Versions.jUnitTest}" }
    val espresso by lazy { "androidx.test.espresso:espresso-core:${Versions.espresso}" }
}

object Library {
    val indicator by lazy { "com.tbuonomo:dotsindicator:${Versions.viewPagerIndicator}" }
    val mPAndroidChart by lazy { "com.github.PhilJay:MPAndroidChart:v${Versions.mPAndroidChart}" }
}
