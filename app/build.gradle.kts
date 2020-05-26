plugins {
  id("com.android.application")
  kotlin("android")
  kotlin("android.extensions")
  kotlin("kapt")
  id("realm-android")
}

androidExtensions {
  isExperimental = true
}

android {
  compileSdkVersion(Versions.COMPILE_SDK_VERSION)
  defaultConfig {
    applicationId = "com.android.nikhil.worldnow"
    minSdkVersion(Versions.MIN_SDK_VERSION)
    targetSdkVersion(Versions.TARGET_SDK_VERSION)
    versionCode = 1
    versionName = "1.0"
    testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
  }

  // Add the kotlin src folder to the srcSets
  sourceSets {
    val main by getting {
      java.setSrcDirs(listOf("src/main/kotlin"))
    }
  }
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*jar"))))

  implementation(Dependency.Core.KOTLIN)

  // Support Libraries
  implementation(Dependency.Core.SUPPORT_DESIGN)
  implementation(Dependency.Core.APPCOMPAT)
  implementation(Dependency.Core.CONSTRAINT_LAYOUT)

  // Retrofit
  implementation(Dependency.Core.RETROFIT)
  implementation(Dependency.Core.RETROFIT_GSON_CONVERTER)
  implementation(Dependency.Core.GSON)

  // Architecture Components ViewModal
  implementation(Dependency.Core.ARCH_LIFECYCLE)
  implementation(Dependency.Core.ARCH_LIFECYCLE_EXTENSIONS)
  kapt(Dependency.Core.ARCH_LIFECYCLE_COMPILER)

  // Dagger Android
  implementation(Dependency.Core.DAGGER)
  implementation(Dependency.Core.DAGGER_ANDROID)
  implementation(Dependency.Core.DAGGER_ANDROID_SUPPORT)
  kapt(Dependency.Core.DAGGER_ANDROID_COMPILER)
  kapt(Dependency.Core.DAGGER_ANDROID_PROCESSOR)

  // Coroutines
  implementation(Dependency.Core.COROUTINES)
  implementation(Dependency.Core.COROUTINES_CALL_ADAPTER)

  // Logging Interceptor
  implementation(Dependency.Core.LOGGING_INTERCEPTOR)

  // Glide
  implementation(Dependency.Core.GLIDE)

  // Testing
  testImplementation(Dependency.Test.JUNIT)
  androidTestImplementation(Dependency.AndroidTest.TEST_RUNNER)
  androidTestImplementation(Dependency.AndroidTest.ESPRESSO_CORE)
}
