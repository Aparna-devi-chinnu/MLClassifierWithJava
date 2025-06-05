plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.deeplearning4j:deeplearning4j-core:0.9.1")
    implementation("nz.ac.waikato.cms.weka:weka-stable:3.8.0")
}

tasks.test {
    useJUnitPlatform()
}