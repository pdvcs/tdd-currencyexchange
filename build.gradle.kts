plugins {
    java
    application
}

version = "0.1.0"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

application {
    mainClassName = "net.pdutta.multimoney.Main"
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.5.1")
    compileOnly("org.projectlombok:lombok:1.18.8")
    annotationProcessor("org.projectlombok:lombok:1.18.8")
}

tasks.jar {
    manifest {
        attributes(
                "Main-Class" to "net.pdutta.multimoney.Main"
        )
    }
}

tasks.test {
    useJUnitPlatform()
}

repositories {
    mavenCentral()
}
