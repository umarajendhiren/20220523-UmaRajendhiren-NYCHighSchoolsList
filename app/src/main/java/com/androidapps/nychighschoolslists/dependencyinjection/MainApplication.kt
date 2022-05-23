package com.androidapps.nychighschoolslists.dependencyinjection

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
/*DI library will use this application class*/
@HiltAndroidApp
class MainApplication:Application() {
}