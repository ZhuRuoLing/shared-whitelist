plugins {
    id("java")
}

group = "net.zhuruoling"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven { url = uri("https://jitpack.io") }
}

dependencies {
    compileOnly("com.github.OhMyMinecraftServer:omms-central:master-SNAPSHOT")
    compileOnly("com.google.code.gson:gson:2.9.0")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}