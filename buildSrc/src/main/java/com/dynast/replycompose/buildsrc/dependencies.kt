package com.dynast.replycompose.buildsrc

object Versions {
    const val ktlint = "0.44.0"
}

object Libs {

    const val gson = "com.google.code.gson:gson:2.8.9"

    object JUnit {
        private const val version = "4.13.2"
        const val junit = "junit:junit:$version"
    }

    object Hilt {
        private const val version = "2.42"
        const val gradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
        const val compiler = "com.google.dagger:hilt-android-compiler:$version"
        const val android = "com.google.dagger:hilt-android:$version"
    }

    object Retrofit {
        private const val version = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val converter = "com.squareup.retrofit2:converter-gson:$version"
    }

    object OkHttp {
        private const val version = "5.0.0-alpha.3"
        const val okhttp = "com.squareup.okhttp3:okhttp:$version"
        const val logging = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object DataStore {
        private const val version = "1.0.0"
        const val datastore = "androidx.datastore:datastore-preferences:$version"
    }

    object Coil {
        private const val version = "2.1.0"
        const val coilCompose = "io.coil-kt:coil-compose:$version"
    }

    object Lottie {
        private const val version = "5.0.3"
        const val lottie = "com.airbnb.android:lottie:$version"
        const val lottieCompose = "com.airbnb.android:lottie-compose:$version"
    }

    object AndroidX {

        const val coreKtx = "androidx.core:core-ktx:1.8.0"

        object PlayServices {
            private const val version = "20.0.0"
            const val playServiceLocation = "com.google.android.gms:play-services-location:$version"
            const val playServiceAuth = "com.google.android.gms:play-services-auth:$version"
        }

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.5.0"
        }

        object Lifecycle {
            private const val version = "2.5.0"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
        }

        object Window {
            const val window = "androidx.window:window:1.0.0"
        }

        object Paging {
            const val pagingCompose = "androidx.paging:paging-compose:1.0.0-alpha15"
        }

        object Room {
            private const val version = "2.4.2"
            const val runtime = "androidx.room:room-runtime:$version"
            const val compiler = "androidx.room:room-compiler:$version"
            const val ktx = "androidx.room:room-ktx:$version"
        }

        object Navigation {
            private const val version = "2.5.0"
            const val navigation = "androidx.navigation:navigation-compose:$version"
        }

        object Compose {
            const val version = "1.2.0-rc02"

            const val material = "androidx.compose.material:material:1.1.1"
            const val material3 = "androidx.compose.material3:material3:1.0.0-alpha14"

            object Ui {
                const val ui = "androidx.compose.ui:ui:$version"
                const val uiTooling = "androidx.compose.ui:ui-tooling:$version"
                const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:$version"
                const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$version"
                const val uiTestJUnit = "androidx.compose.ui:ui-test-junit4:$version"
                const val uiFont = "androidx.compose.ui:ui-text-google-fonts:$version"
            }
        }

        object Hilt {
            private const val version = "1.0.0"
            const val compiler = "androidx.hilt:hilt-compiler:$version"
            const val common = "androidx.hilt:hilt-common:$version"
            const val navigationCompose = "androidx.hilt:hilt-navigation-compose:$version"
        }

        object Test {

            object Ext {
                private const val version = "1.1.3"
                const val junit = "androidx.test.ext:junit:$version"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"
        }
    }

    object Accompanist {

        object Pager {
            private const val version = "0.25.0"
            const val pager = "com.google.accompanist:accompanist-pager:$version"
            const val pagerIndicators = "com.google.accompanist:accompanist-pager-indicators:$version"
        }

        object SwipeRefresh {
            private const val version = "0.25.0"
            const val swipe = "com.google.accompanist:accompanist-swiperefresh:$version"
        }
    }
}