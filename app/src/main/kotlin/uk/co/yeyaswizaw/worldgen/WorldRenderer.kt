package uk.co.yeyaswizaw.worldgen

import android.opengl.GLES20.*
import android.opengl.GLSurfaceView
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class WorldRenderer : GLSurfaceView.Renderer {
    private var surface_width = -1
    private var surface_height = -1

    var background = Colour(0.1f, 0.3f, 0.7f)

    override fun onSurfaceCreated(unused: GL10?, config: EGLConfig?) {
        surface_width = -1
        surface_height = -1
    }

    override fun onSurfaceChanged(unused: GL10?, width: Int, height: Int) {
        surface_width = width
        surface_height = height

        Log.D("Surface resized to $width x $height")

        glViewport(0, 0, surface_width, surface_height)
        glClearColor(background.r, background.g, background.b, background.a)
    }

    override fun onDrawFrame(unused: GL10?) {
        Log.D("Rendering frame")

        glClearColor(background.r, background.g, background.b, background.a)
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)
    }
}