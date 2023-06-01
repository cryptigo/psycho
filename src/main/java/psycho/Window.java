package psycho;

import org.lwjgl.Version;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;
import util.ColoredLogger;
import util.Time;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
    private int width, height;
    private String title;
    private long glfwWindow;

    public float r, g, b, a;
    private boolean fadeToBlack = false;

    private static Window window = null;

    private static Scene currentScene;

    private Window() {
        ColoredLogger.info("Created Window Object.");
        this.width = 1920;
        this.height = 1080;
        this.title = "PsychoEngine";

        r = 0.7f;
        g = 0.1f;
        b = 0.2f;
        a = 1;
    }

    public static void changeScene(int newScene) {
        ColoredLogger.info("Changing current scene to: " + newScene);

        switch (newScene) {
            case 0:
                currentScene = new LevelEditorScene();
                ColoredLogger.info("Changed current scene to 'LevelEditorScene'");
                currentScene.init();
                currentScene.start();
                break;
            case 1:
                currentScene = new LevelScene();
                ColoredLogger.info("Changed scene to 'LevelScene'");
                currentScene.init();
                currentScene.start();
                break;
            default:
                ColoredLogger.error("Error whilst changing current scene: Unknown Scene: '" + newScene + "'");
                assert false : "Unknown scene '" + newScene + "'";
                break;
        }
    }

    public static Window get() {
        if (Window.window == null) {
            Window.window = new Window();
        }

        return Window.window;
    }

    public static Scene getScene() {
        return get().currentScene;
    }

    public void run() {
        ColoredLogger.info("LWJGL Version: " + Version.getVersion());

        ColoredLogger.fine("Starting the main game engine loop.");
        init();
        loop();

        // Free the memory
        ColoredLogger.info("Freeing up memory that GLFW used.");
        glfwFreeCallbacks(glfwWindow);
        glfwDestroyWindow(glfwWindow);

        // Terminate GLFW and free the error callback
        ColoredLogger.info("Terminating GLFW.");
        glfwTerminate();

        ColoredLogger.fine("Freeing GLFW error context.");
        glfwSetErrorCallback(null).free();
    }

    public void init() {
        ColoredLogger.info("Initializing Window.");

        // Setup an error callback
        ColoredLogger.info("Setup error callback.");
        GLFWErrorCallback.createPrint(System.err).set();

        // Initialize GLFW
        if (!glfwInit()) {
            ColoredLogger.error("Failed to initialize GLFW.");
            throw new IllegalStateException("Unable to initialize GLFW.");
        } else {
            ColoredLogger.info("Initialized GLFW.");
        }

        // Configure GLFW
        ColoredLogger.config("GLFW_RESIZABLE: true");
        ColoredLogger.config("GLFW_MAXIMIZED: true");
        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);
        glfwWindowHint(GLFW_MAXIMIZED, GLFW_TRUE);

        // Create the window
        ColoredLogger.info("Creating the GLFW window.");
        ColoredLogger.config("glfwWindow: width->" + this.width + ", height->" + this.height + ", title->" + this.title);
        glfwWindow = glfwCreateWindow(this.width, this.height, this.title, NULL, NULL);
        if (glfwWindow == NULL) {
            ColoredLogger.error("Failed to create the GLFW window.");
            throw new IllegalStateException("Failed to create the GLFW window.");
        }

        // Setup callbacks
        ColoredLogger.info("Setting up GLFW callbacks.");
        glfwSetCursorPosCallback(glfwWindow, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(glfwWindow, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(glfwWindow, MouseListener::mouseScrollCallback);
        glfwSetKeyCallback(glfwWindow, KeyListener::keyCallback);

        // Make the OpenGL context current
        ColoredLogger.info("Setting OpenGL context current.");
        glfwMakeContextCurrent(glfwWindow);

        // Enable VSync
        ColoredLogger.config("glfwSwapInterval: 1");
        glfwSwapInterval(1);

        // Make the window visible
        ColoredLogger.info("Setting window to visible.");
        glfwShowWindow(glfwWindow);

        // Enable OpenGL LWJGL interop
        ColoredLogger.info("Enabling OpenGL LWJGL interop.");
        GL.createCapabilities();

        // Change to level editor scene
        Window.changeScene(0);
    }

    public void loop() {
        float beginTime = (float)glfwGetTime();
        float endTime;
        float dt = -1.0f;

        while (!glfwWindowShouldClose(glfwWindow)) {
            // Poll for events
            glfwPollEvents();

            glClearColor(r, g, b, a);
            glClear(GL_COLOR_BUFFER_BIT);

            if (dt >= 0) {
                currentScene.update(dt);
            }

            glfwSwapBuffers(glfwWindow);

            endTime = (float)glfwGetTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }
    }
}
