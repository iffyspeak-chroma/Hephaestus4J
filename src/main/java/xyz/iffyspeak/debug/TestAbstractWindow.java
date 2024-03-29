package xyz.iffyspeak.debug;

import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.nio.*;

import xyz.iffyspeak.debug.Logger;
import xyz.iffyspeak.window.AbstractWindow;

public class TestAbstractWindow extends AbstractWindow {
    @Override
    public void run() {
        Logger.Info("LWJGL " + Version.getVersion() + " <3");
        init();
        loop();

        glfwFreeCallbacks(this.getHandle());
        glfwDestroyWindow(this.getHandle());

        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void init()
    {
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit())
        {
            throw new IllegalStateException("Cannot initialize GLFW");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        this.setHandle(glfwCreateWindow(1280, 720, "Hephaestus4J", NULL, NULL));

        if (this.getHandle() == NULL)
        {
            throw new RuntimeException("Failed to create a window");
        }

        glfwSetKeyCallback(this.getHandle(), (window, key, scancode, action, mods) -> {
            if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                glfwSetWindowShouldClose(window, true); // We will detect this in the rendering loop
        });

        try (MemoryStack stack = stackPush())
        {
            IntBuffer pWidth = stack.mallocInt(1);
            IntBuffer pHeight = stack.mallocInt(1);

            glfwGetWindowSize(this.getHandle(), pWidth, pHeight);

            GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

            glfwSetWindowPos(this.getHandle(), (vidmode.width() - pWidth.get(0)) / 2,
                                               (vidmode.height() - pHeight.get(0)) / 2
            );

            glfwMakeContextCurrent(this.getHandle());
            glfwSwapInterval(1);
            glfwShowWindow(this.getHandle());
        }
    }

    private void loop()
    {
        GL.createCapabilities();

        glClearColor(1.0f, 0.0f, 0.0f, 0.0f);

        while (!glfwWindowShouldClose(this.getHandle()))
        {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            glfwSwapBuffers(this.getHandle());

            glfwPollEvents();
        }
    }
}
