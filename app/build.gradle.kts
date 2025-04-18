import com.android.build.gradle.internal.api.BaseVariantOutputImpl
import com.google.gson.JsonParser
import java.util.Date

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "name.liuyi.mifreeformenhance"
    compileSdk = 35

    packaging {
        dex {
            useLegacyPackaging = true
        }
    }
    defaultConfig {
        applicationId = "name.liuyi.mifreeformenhance"
        minSdk = 34
        targetSdk = 35
        val version = listOf(1, 0, 0)
        versionCode = version.reduce { acc, i -> acc.shl(8).or(i) }
        versionName = version.joinToString(".") + "-" + (Date().time / 1000).toString(16)

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    signingConfigs {
        register("release") {
            val json = JsonParser.parseString(File("sign.json").readText()).asJsonObject
            storeFile = file(json["storeFile"].asString)
            storePassword = json["storePassword"].asString
            keyAlias = json["keyAlias"].asString
            keyPassword = json["keyPassword"].asString
        }
    }
    buildTypes {
        release {
            signingConfig = signingConfigs.getByName("release")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    applicationVariants.all {
        outputs.all {
            val outputImpl = this as BaseVariantOutputImpl
            outputImpl.outputFileName = "${rootProject.name}_${versionName}_${name}.apk"
        }
    }
}

dependencies {

    compileOnly(project(":framwork-stub"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.miuix)
    implementation(libs.activity.compose)
    implementation(libs.androidx.material3)
    // https://mvnrepository.com/artifact/com.elvishew/xlog
    implementation(libs.xlog)
    compileOnly(libs.xposed.api)
    // https://mvnrepository.com/artifact/androidx.compose.ui/ui-tooling-preview
    implementation(libs.androidx.ui.tooling.preview)
    // https://mvnrepository.com/artifact/androidx.navigation/navigation-compose
    implementation(libs.androidx.navigation.compose)
    // https://mvnrepository.com/artifact/androidx.lifecycle/lifecycle-viewmodel-compose
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    // https://mvnrepository.com/artifact/org.jetbrains.kotlin/kotlin-reflect
    implementation(libs.kotlin.reflect)
    debugImplementation(libs.androidx.ui.tooling)
}