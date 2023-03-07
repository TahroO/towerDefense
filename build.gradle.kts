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
}

application {
    mainClass.set("brot.main.Game")
}

tasks.create("printSourceSetInformation") {
    doLast {
        sourceSets.forEach {
            println("[${it.name}]")
            println("-->Java sources: ${it.allJava.srcDirs}")
            println("-->Non Java resources: ${it.resources.srcDirs}")
            println("-->Output classes: ${it.output.classesDirs.files}")
            println("-->Output resources: ${it.output.resourcesDir}")
            println("")
        }
    }
}
