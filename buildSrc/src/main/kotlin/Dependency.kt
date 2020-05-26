object Versions {
  const val KOTLIN = "1.3.61"
  const val REALM_GRADLE_PLUGIN = "5.8.0"
  const val ANDROID_GRADLE_PLUGIN = "3.5.3"
  const val COMPILE_SDK_VERSION = 28
  const val MIN_SDK_VERSION = 26
  const val TARGET_SDK_VERSION = 28

  const val RETROFIT = "2.4.0"
  const val GSON = "2.8.5"
  const val GSON_CONVERTER = "2.4.0"
  const val ARCH_LIFECYCLE = "1.1.1"
  const val DAGGER = "2.16"
  const val OKHTTP_INTERCEPTOR = "3.11.0"
  const val SUPPORT_VERSION = "28.0.0"
  const val COROUTINE_CALL_ADAPTER = "0.9.2"
  const val COROUTINES = "1.1.1"
}

object Dependency {

  object Core {
    const val ANDROID_GRADLE_PLUGIN =
      "com.android.tools.build:gradle:${Versions.ANDROID_GRADLE_PLUGIN}"
    const val KOTLIN_GRADLE_PLUGIN = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}"
    const val REALM_GRADLE_PLUGIN = "io.realm:realm-gradle-plugin:${Versions.REALM_GRADLE_PLUGIN}"

    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
    const val SUPPORT_DESIGN = "com.android.support:design:${Versions.SUPPORT_VERSION}"
    const val APPCOMPAT = "com.android.support:appcompat-v7:${Versions.SUPPORT_VERSION}"
    const val CONSTRAINT_LAYOUT = "com.android.support.constraint:constraint-layout:1.1.3"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    const val RETROFIT_GSON_CONVERTER =
      "com.squareup.retrofit2:converter-gson:${Versions.GSON_CONVERTER}"
    const val GSON = "com.google.code.gson:gson:${Versions.GSON}"
    const val LOGGING_INTERCEPTOR =
      "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP_INTERCEPTOR}"

    const val ARCH_LIFECYCLE = "android.arch.lifecycle:runtime:${Versions.ARCH_LIFECYCLE}"
    const val ARCH_LIFECYCLE_EXTENSIONS =
      "android.arch.lifecycle:extensions:${Versions.ARCH_LIFECYCLE}"
    const val ARCH_LIFECYCLE_COMPILER = "android.arch.lifecycle:compiler:${Versions.ARCH_LIFECYCLE}"

    const val DAGGER = "com.google.dagger:dagger:${Versions.DAGGER}"
    const val DAGGER_ANDROID = "com.google.dagger:dagger-android:${Versions.DAGGER}"
    const val DAGGER_ANDROID_SUPPORT = "com.google.dagger:dagger-android-support:${Versions.DAGGER}"
    const val DAGGER_ANDROID_PROCESSOR =
      "com.google.dagger:dagger-android-processor:${Versions.DAGGER}"
    const val DAGGER_ANDROID_COMPILER = "com.google.dagger:dagger-compiler:${Versions.DAGGER}"

    const val COROUTINES_CALL_ADAPTER =
      "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.COROUTINE_CALL_ADAPTER}"
    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"

    const val GLIDE = "com.github.bumptech.glide:glide:4.6.1"
  }

  object Test {
    const val JUNIT = "junit:junit:4.12"
  }

  object AndroidTest {
    const val TEST_RUNNER = "com.android.support.test:runner:1.0.2"
    const val ESPRESSO_CORE = "com.android.support.test.espresso:espresso-core:3.0.2"
  }
}