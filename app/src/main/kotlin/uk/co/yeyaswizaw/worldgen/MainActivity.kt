package uk.co.yeyaswizaw.worldgen

import android.app.Activity
import android.opengl.GLSurfaceView
import android.os.Bundle
import org.jetbrains.anko.*
import java.util.Random

class MainActivity : Activity() {
    private lateinit var surface: GLSurfaceView
    private var renderer = SurfaceRenderer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load native library
        System.loadLibrary("native")
        Log.D("Native library loaded")

        // Set content
        verticalLayout {
            surface = gLSurfaceView {
                setEGLContextClientVersion(2)
                preserveEGLContextOnPause = true
                setRenderer(renderer)
            }.lparams {
                height = 0
                weight = 1.0f
            }

            button(R.string.button_text) {
                onClick { onButtonClick() }
            }
        }

        Log.D("Content view set")
        Log.D("Surface + renderer set")
    }

    override fun onResume() {
        super.onResume()
        surface.onResume()
    }

    override fun onPause() {
        super.onPause()
        surface.onPause()
    }

    fun onButtonClick() {
        Log.I("Hello Android World!")
        Native().hello()

        var random = Random()
        renderer.background = Colour(random.nextFloat(), random.nextFloat(), random.nextFloat())
        surface.requestRender()
    }
}
