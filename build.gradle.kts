plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.shadow)
    alias(libs.plugins.resourceFactory.bukkit) // alias(libs.plugins.resourceFactory.paper)
    alias(libs.plugins.runPaper)
}

group = providers.gradleProperty("pluginGroup").get()
version = providers.gradleProperty("pluginVersion").get()

kotlin {
    @Suppress("unStableApiUsage") jvmToolchain(jdkVersion = JavaLanguageVersion.current().asInt())
}

repositories {
    mavenCentral()
    maven {
        name = "papermc"
        url = uri("https://repo.papermc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly(libs.paperApi)

    implementation(kotlin("stdlib"))
}

bukkitPluginYaml {
    main = providers.gradleProperty("pluginGroup").get() + "." + providers.gradleProperty("pluginName").get()
    apiVersion = providers.gradleProperty("minecraftVersion").get()
}

tasks {
    compileJava {
        options.encoding = Charsets.UTF_8.name()
    }

    javadoc {
        options.encoding = Charsets.UTF_8.name()
    }

    processResources {
        filteringCharset = Charsets.UTF_8.name()
    }

    shadowJar {
        archiveClassifier.set("")
    }

    build {
        dependsOn(shadowJar)
    }

    runServer {
        minecraftVersion(providers.gradleProperty("minecraftVersion").get())
    }
}