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
            srcDir("src/main/java")
        }
        resources {
            srcDir("src/main/resources")
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

tasks.create("printSourceSetInformation") {
    doLast {
        sourceSets.forEach {
            println("[${it.name}]")
            print("-->Java sources: ${it.allJava.srcDirs}\n")
            print("-->Resources: ${it.resources.srcDirs}\n")
            print("-->Output classes: ${it.output.classesDirs.files}\n")
            print("-->Output resources: ${it.output.resourcesDir}\n")
            println("")
        }
    }
}