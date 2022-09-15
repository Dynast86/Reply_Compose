package com.dynast.replycompose

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory

class ReplyComposeApp : Application(), ImageLoaderFactory {

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .build()
    }
}
