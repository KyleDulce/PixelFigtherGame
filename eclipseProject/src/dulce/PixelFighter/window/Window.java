package dulce.PixelFighter.window;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

import java.rmi.activation.*;

import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;

public class Window {

								//dimensions of window
	private static int         	width,   
					   			height;   
								//window pointer
	private static long         window;
								//if error occurs
	private static String       error;
	
	public Window() {}
	
	public static void winInit() throws NullPointerException, ActivateFailedException {
		//initonalize object
		
		//initonalize glfw
		if(!glfwInit()){ //initionalze glfw for window, returns false if failed
			error = "glfw failed to start";
			throw new ActivateFailedException("glfw failed to intitionalize");
		}
		
		//getting info on monitor
		GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
		width = vidmode.width();
		height = vidmode.height();
			
		//set glfw window caracteristics
		glfwWindowHint(GLFW_RESIZABLE, GL_TRUE);		
		glfwWindowHint(GLFW_MAXIMIZED, GL_TRUE);	
		
		//create window and set pointer
		window = glfwCreateWindow(width, height, "Pixel Fighter", NULL, NULL);
		
		//test if pointer failed to be set
		if(window == NULL) {
			error = "the window variable is blank for an unknown reason";
			throw new NullPointerException();
		}
		
		//show window
		glfwShowWindow(window);
		
		//opengl current context set with capabilities
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		//enabling opengl func
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	}
	
	//public getters
	public static int getWidth() {return width;}
	public static int getHeight() {return height;}
	public static long getWindow() {return window;}
	public String getError() {return error;}

}
