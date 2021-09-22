package com.gentlekboy.postappusingfragments.dependencyInjection

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//This tells hilt stay active as long as the application lives
@HiltAndroidApp
class PostApplication: Application() {

}