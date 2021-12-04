plugins {
  java
}

tasks {
  create<Jar>("graderJar") {
    group = "build"
    afterEvaluate {
      archiveFileName.set("FOP-2022-H03-${project.version}.jar")
      from(project(":grader").sourceSets.main.get().allSource)
      from(project(":solution").sourceSets.main.get().allSource)
    }
  }
}

allprojects {
  apply(plugin = "java")
  version = "2.0.0-SNAPSHOT"
  repositories {
    mavenCentral()
    maven("https://s01.oss.sonatype.org/content/repositories/snapshots")
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
