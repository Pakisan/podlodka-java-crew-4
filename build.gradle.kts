import com.bmuschko.gradle.docker.tasks.image.*

plugins {
    java
    id("org.springframework.boot") version "3.2.3"
    id("io.spring.dependency-management") version "1.1.4"
    id("ca.cutterslade.analyze") version "1.9.2"
    id("com.bmuschko.docker-spring-boot-application") version "9.4.0"
}

group = "com.github.pakisan"
version = "1.0.0"

java {
    sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

dependencies {

    // Spring Wolf
    implementation("io.github.springwolf:springwolf-core:1.0.0")
    annotationProcessor("io.github.springwolf:springwolf-amqp:1.0.0")
    implementation("io.github.springwolf:springwolf-amqp:1.0.0")
    implementation("io.github.springwolf:springwolf-ui:1.0.0")
    implementation("io.swagger.core.v3:swagger-annotations:2.2.20")

    // Spring Boot
    implementation("org.springframework.boot:spring-boot-starter-amqp")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-autoconfigure")
//    implementation("org.springframework:spring-context")
//    implementation("org.springframework.boot:spring-boot")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
//    testImplementation("org.springframework:spring-beans")
//    testImplementation("org.springframework:spring-web")
//    testImplementation("org.springframework:spring-test")
    testImplementation("org.springframework.amqp:spring-rabbit-test")

    annotationProcessor("org.projectlombok:lombok:1.18.30")
    compileOnly("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
    testCompileOnly("org.projectlombok:lombok:1.18.30")

    implementation("org.slf4j:slf4j-api:2.0.12")

    testImplementation("org.testcontainers:testcontainers:1.19.6")
    testImplementation("org.testcontainers:junit-jupiter:1.19.6")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter:5.10.2")
    testImplementation("org.assertj:assertj-core:3.25.3")
    testImplementation("org.awaitility:awaitility:4.2.0")
    testImplementation("org.mockito:mockito-core:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
}

docker {
    springBootApplication {
        baseImage = "eclipse-temurin:17-jre-focal"
        ports = listOf(8080)
        images = listOf("pakisan/springwolf-amqp-example:1.0.0")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()

    dependsOn(tasks.withType<DockerBuildImage>())
}
