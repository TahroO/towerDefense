plugins {
    id("java")
    application
}

group = "brot"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

application {
    mainClass.set("brot.main.Game")
}

sourceSets {
    main {
        java {
            srcDir("source")
        }
        resources {
            srcDir("resources")
        }
    }
    test {
        java {
            srcDir("test")
        }
    }
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}