/* Made by Kyle J Dulce
 * credit to LWJGL.com for library use
 * Credit to unknown user on github.com for use of JOML
 * Credit to eclispe.com for use of ide
 * All other ownerships go to their respective owners
 * 
 * 
 * 
 * */

package dulce.PixelFighter.main;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.joml.*;

import dulce.PixelFighter.sprite.*;
import dulce.PixelFighter.sprite.inherited.*;
import dulce.PixelFighter.window.*;
import dulce.PixelFighter.logs.*;
import dulce.PixelFighter.logs.Error;
import dulce.PixelFighter.world.*;
import static dulce.PixelFighter.window.Window.*;
import static dulce.PixelFighter.sprite.PFvar.*;
import static dulce.PixelFighter.main.Input.*;
import static dulce.PixelFighter.logs.GameLog.*;

public class Main implements Runnable {
	
		                          //running status
	public static boolean         running = false;
	                              //window
	protected Window 			  windowOb;
	protected long 				  window;
	protected Background          background;
	private Timer				  timer;
								  //game var
	private Thread 				  GameThread;
								  //test objects
	public PlayerDat 			  Player;
	protected Matrix4f            projection,
								  scale;
	protected Texture 			  texture;
	protected Shader              shader;
	protected TileRender 		  tiles;
	protected boolean 			  alreadySpawned = false;
		
	private void init() {
		//initionalize program
		
		windowOb = new Window();  //create window object
		
		//initionalize window
		try {
			winInit();
		}catch (Exception e) {
			Error.crashReport("the window failed to start up", "for an unknown reason", e.getStackTrace(), windowOb.getError());
		}
		
		//setting pointer value for class reference
		window   = getWindow();
		
		//testing if pointer is Null
		try{
			if(window == 0L)
				throw new NullPointerException("main window vaiable is null");
		}catch(Exception e) {
			Error.crashReport("the main class window pointer is", "null due to an unknown reason", e.getStackTrace(), 
																		"window at init(), when set is null");
		}
		
		//initionalize AllEntities Class
		AllEntities.enInit();
		
		background = new Background();
		timer = new Timer();
		Input.inInit(window);
	}
			
	public void start() {	
		//start Threads
		running = true;
		createLog();
		GameThread = new Thread(this, "Game");    //new thread named "Game"
		GameThread.start();  //goes to run()
	}
		
	public void run() {		
		//Running Thread
		init();
		AllEntities.spawnPlayer(0, 0, window);
		while(running) {	
			timer.update();
			
			while(timer.shouldProcess()) {
				update();
				timer.process();
			}
			if(timer.getCanRender()) {
			render();
			timer.addFrame();
			}
			if(glfwWindowShouldClose(window)) {		
				running = false;
			}
			
		}
		
		endLog();
		glfwTerminate();
	}
	
	
	private void render() {
		glClear(GL_COLOR_BUFFER_BIT);
		
		background.renderBack();
		AllEntities.render();
		
		glfwSwapBuffers(window);	
	}
				
	private void update() {			
		glfwPollEvents();
		inUpdate();
		
		keyCheck();
		AllEntities.update();
			
	}
	
	private void keyCheck() {
		if(isKeyPressed(GLFW_KEY_J) && (alreadySpawned == false)) {
			AllEntities.spawn(joe, 0, 0, window);
			alreadySpawned = true;
		}
		if(!isKeyPressed(GLFW_KEY_J) && (alreadySpawned == true))
			alreadySpawned = false;
	}
	
	public static void end() {running = false;}
		
	public static void main(String[] args) {	
		//start program
		new Main().start();
	}
}
