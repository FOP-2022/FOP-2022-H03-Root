plugins {
  java
}
allprojects {
  apply(plugin = "java")
  version = "1.0.0"
  repositories {
    mavenCentral()
  }
  dependencies {
    implementation("org.sourcegrade:fopbot:0.3.0")
  }
  java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
    withSourcesJar()
  }
  tasks {
    withType<JavaCompile> {
      options.encoding = "UTF-8"
    }
    jar {
      archiveFileName.set("FOP-2022-H03-${project.name}-${project.version}.jar")
    }
    named<Jar>("sourcesJar") {
      archiveFileName.set("FOP-2022-H03-${project.name}-${project.version}-sources.jar")
    }
    test {
      useJUnitPlatform()
    }
  }
}
