plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.test_github"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.test_github"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {


    //Dagger Hilt dependency injection
    //implementation("com.google.dagger:hilt-android:2.49")
    //kapt ("com.google.dagger:hilt-compiler:2.49")

    implementation("com.google.dagger:hilt-android:2.49")

    kapt("com.google.dagger:hilt-compiler:2.49")
    // bdd Room
    implementation("androidx.room:room-ktx:2.6.1")
    kapt("androidx.room:room-compiler:2.6.1")
    // Autres dependances
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.9")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    /*Ajout de retrofit (obligatoire)
    C'est une bibliothèque HTTP qui facilite les appels réseau dans les applications Android.
    Elle simplifie la communication avec les API en convertissant automatiquement les réponses JSON en objets Kotlin.
    Cela facilite le développement d'applications qui nécessitent des interactions avec des serveurs web.
     on le trouve dans : https://square.github.io/retrofit/
     */
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // OOn ajoute la dependance GSON pour convertir les données
    implementation("com.squareup.retrofit2:converter-gson:2.1.0")

    implementation ("com.github.bumptech.glide:glide:4.11.0")
    // Pour la navigation
    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.5")
    // Pour insérer les photos
    implementation ("androidx.cardview:cardview:1.0.0")

    // pour les tests unitaires
    implementation("androidx.test.ext:junit-ktx:1.1.5")
    // pour tester la connexion
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:3.11.2")

    testImplementation("org.mockito:mockito-core:3.11.2")
}

kapt {

    correctErrorTypes = true
}

