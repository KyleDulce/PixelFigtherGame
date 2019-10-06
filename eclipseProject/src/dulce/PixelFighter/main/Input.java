package dulce.PixelFighter.main;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.*;

public class Input {

	private static long Window;
	private static boolean[] keys;
	private static int key = 0;
	private static int action = 0;
	private static int button = 0;
	private static int bAction = 0;
	private static double mouseX = 0;
	private static double mouseY = 0;
	private static double scroll = 0;
	private static boolean entered = false;
	
	public static void inInit(long window) {	
		
		Window = window;
		glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
		
		glfwSetKeyCallback(window, new GLFWKeyCallback() {
			public void invoke(long window, int key, int scancode, int action, int mods) {
				setKey(key);
				setAction(action);
			}
		});
		
		keys = new boolean[GLFW_KEY_LAST];
		for(int x = 0; x > keys.length; x++)
			keys[x] = false;
		
		glfwSetMouseButtonCallback(window, new GLFWMouseButtonCallback() {			
			public void invoke(long window, int button, int action, int mods) {
				setButton(button);
				setbAction(action);
			}
		});
		
		glfwSetCursorPosCallback(window, new GLFWCursorPosCallback() {
			public void invoke(long window, double xpos, double ypos) {
				mouseX = xpos;
				mouseY = ypos;
			}
		});
		
		glfwSetScrollCallback(window, new GLFWScrollCallback() {
			public void invoke(long window, double xoffset, double yoffset) {
				scroll = yoffset;
			}
		});
		
		glfwSetCursorEnterCallback(window, new GLFWCursorEnterCallback() {
			public void invoke(long window, boolean entered) {
				setEntered(entered);
			}
		});
		
	}


	public static void hideCursor(long window) {
		glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_HIDDEN);
	}
	
	public static void showCursor(long window) {
		glfwSetInputMode(window, GLFW_CURSOR, GLFW_CURSOR_NORMAL);
	}
	
	public static boolean isKeyDown(int key) {
		//return getKey() == key && (action == GLFW_PRESS || action == GLFW_REPEAT);
		return glfwGetKey(Window, key) == 1;
	}
	
	public static boolean isKeyPressed(int key) {
		return getKey() == key && action == GLFW_PRESS;
	}
	
	public static boolean isKeyReleased(int key) {
		return getKey() == key && action == GLFW_RELEASE;
	}
	
	public static boolean isMousePressed(int button) {
		return getButton() == button && bAction == GLFW_PRESS;
	}
	
	public static boolean ifMouseEntered() {
		return entered;
	}
	
	public static boolean ifScroll(double offsetAmountMin, double offsetAmountMax) {
		return (scroll >= offsetAmountMin && scroll <= offsetAmountMax);
	}
	
	public static boolean ifMouseinBounds(double topLeftx, double topLefty, double bottomRightx, double bottomRighty) {
		return (mouseX > topLeftx && mouseX < bottomRightx && mouseY < topLefty && mouseY > bottomRighty);
	}
	
	public static void inUpdate() {
		for(int x = 0; x > keys.length; x++) {
			if(glfwGetKey(Window, x) == GLFW_PRESS || glfwGetKey(Window, x) == GLFW_REPEAT)
				keys[x] = true;
		}
	}

	private static int getButton() {return button;}
	private static int getKey() {return key;}
	private static void setKey(int Key) {key = Key;}
	private static void setAction(int Action) {action = Action;}
	private static void setbAction(int bAction) {Input.bAction = bAction;}
	private static void setEntered(boolean entered) {Input.entered = entered;}		
	private static void setButton(int button) {Input.button = button;}
	
}
