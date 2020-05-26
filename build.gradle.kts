// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
  repositories {
    google()
    jcenter()
  }
  dependencies {
    classpath(Dependency.Core.ANDROID_GRADLE_PLUGIN)
    classpath(Dependency.Core.KOTLIN_GRADLE_PLUGIN)
    classpath(Dependency.Core.REALM_GRADLE_PLUGIN)

    // NOTE: Do not place your application dependencies here; they belong
    // in the individual module build.gradle files
  }
}

allprojects {
  repositories {
    google()
    jcenter()
  }
}

tasks {
  val clean by registering(Delete::class) {
    delete(buildDir)
  }
}