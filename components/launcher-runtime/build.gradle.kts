plugins {
    id("java-library")
    kotlin("jvm") version "1.9.22"
    id("org.jetbrains.compose") version "1.6.0"
}

group = "pro.gravit.launcher.runtime"
version = project.version

sourceSets {
    main {
        java.srcDirs("src/main/java")
        java.srcDirs("src/main/kotlin") 
        resources.srcDirs("src/main/resources")
    }
}

dependencies {
    api(project(":components:launcher-api"))
    api(project(":components:launcher-core"))
    
    implementation(compose.desktop.currentOs)
    implementation(compose.material)
    implementation(compose.material3)
    
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-swing:1.7.3")

    compileOnly("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
