package uk.co.yeyaswizaw.worldgen

import android.app.Activity
import android.opengl.GLSurfaceView
import android.os.Bundle
import android.util.Log
import android.view.View
import java.util.*

class MainActivity : Activity() {
    private var surface: GLSurfaceView? = null
    private var renderer: WorldRenderer = WorldRenderer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Load native library
        System.loadLibrary("native")

        // Set content
        setContentView(R.layout.main_layout)

        // Set opengl surface
        surface = findViewById(R.id.surface) as GLSurfaceView?
        surface?.setEGLContextClientVersion(2)
        surface?.preserveEGLContextOnPause = true

        surface?.setRenderer(renderer)
    }

    override fun onResume() {
        super.onResume()
        surface?.onResume()
    }

    override fun onPause() {
        super.onPause()
        surface?.onPause()
    }

    fun onClick(view: View) {
        Log.i("Worldgen", "Hello Android World!")
        Native().hello()

        var random = Random()
        renderer.background = Colour(random.nextFloat(), random.nextFloat(), random.nextFloat())
        surface?.requestRender()
    }
}
