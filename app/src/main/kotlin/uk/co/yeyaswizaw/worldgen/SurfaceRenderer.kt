package uk.co.yeyaswizaw.worldgen

import android.opengl.GLES20.*
import android.opengl.GLSurfaceView
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

const val vertex_shader_src =
"""
attribute vec4 position;

void main() {
    gl_Position = position;
}
"""

const val fragment_shader_src =
"""
precision mediump float;

void main() {
    gl_FragColor = vec4(1.0, 0.0, 0.0, 1.0);
}
"""

class SurfaceRenderer(
    var background: Colour = Colour(0.1f, 0.3f, 0.7f)
) : GLSurfaceView.Renderer {

    private var surface_width = -1
    private var surface_height = -1

    private val vertex_data: FloatBuffer

    private var program = -1

    init {
        val vertices = floatArrayOf(
            -0.5f, -0.5f,
            -0.5f,  0.5f,
             0.5f, -0.5f,

            -0.5f,  0.5f,
             0.5f, -0.5f,
             0.5f,  0.5f
        )

        vertex_data = ByteBuffer
            .allocateDirect(vertices.size * 4)
            .order(ByteOrder.nativeOrder())
            .asFloatBuffer()

        vertex_data.put(vertices).position(0)
    }

    override fun onSurfaceCreated(unused: GL10?, config: EGLConfig?) {
        surface_width = -1
        surface_height = -1

        val status = intArrayOf(0)

        var vertex_shader = glCreateShader(GL_VERTEX_SHADER)
        if(vertex_shader != 0) {
            glShaderSource(vertex_shader, vertex_shader_src)
            glCompileShader(vertex_shader)
            glGetShaderiv(vertex_shader, GL_COMPILE_STATUS, status, 0)
            if(status[0] == 0) {
                glDeleteShader(vertex_shader)
                vertex_shader = 0;

                Log.E("Error compiling vertex shader")
            }
        }

        if(vertex_shader == 0) {
            Log.E("Error creating vertex shader")
            throw RuntimeException("Error creating vertex shader")
        }

        Log.D("Vertex shader created")

        var fragment_shader = glCreateShader(GL_FRAGMENT_SHADER)
        if(fragment_shader != 0) {
            glShaderSource(fragment_shader, fragment_shader_src)
            glCompileShader(fragment_shader)
            glGetShaderiv(fragment_shader, GL_COMPILE_STATUS, status, 0)
            if(status[0] == 0) {
                glDeleteShader(fragment_shader)
                fragment_shader = 0;

                Log.E("Error compiling fragment shader")
            }
        }

        if(fragment_shader == 0) {
            Log.E("Error creating fragment shader")
            throw RuntimeException("Error creating fragment shader")
        }

        Log.D("Fragment shader created")

        program = glCreateProgram()
        if(program != 0) {
            glAttachShader(program, vertex_shader)
            glAttachShader(program, fragment_shader)
            glBindAttribLocation(program, 0, "position")
            glLinkProgram(program)
            glGetProgramiv(program, GL_LINK_STATUS, status, 0)
            if(status[0] == 0) {
                glDeleteProgram(program)
                program = 0

                Log.E("Error linking program")
            }
        }

        if(program == 0) {
            Log.E("Error creating program")
            throw RuntimeException("Error creating program")
        }

        glUseProgram(program)
        Log.D("Program created")
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

        vertex_data.position(0)

        val position = glGetAttribLocation(program, "position")
        glVertexAttribPointer(position, 2, GL_FLOAT, false, 2 * 4, vertex_data)
        glEnableVertexAttribArray(position)
        glDrawArrays(GL_TRIANGLES, 0, 6)
    }
}