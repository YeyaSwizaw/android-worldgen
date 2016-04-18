package uk.co.yeyaswizaw.worldgen

class Colour(var r: Float, var g: Float, var b: Float, var a: Float = 1.0f)

val LOG_TAG = "Worldgen"

enum class Log(var f: (String) -> Unit) {
    I({ msg -> android.util.Log.i(LOG_TAG, msg) }),
    D({ msg -> android.util.Log.d(LOG_TAG, msg) }),
    E({ msg -> android.util.Log.e(LOG_TAG, msg) });

    operator fun invoke(msg: String) {
        f(msg)
    }
}
