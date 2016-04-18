package uk.co.yeyaswizaw.worldgen

/**
 * Created by sam on 18/04/2016.
 */
class Native {
    init {
        System.loadLibrary("native")
    }

    external fun hello()
}