package dulce.PixelFighter.sprite.enemy.boss;

import static org.lwjgl.glfw.GLFW.*;

import dulce.PixelFighter.sprite.*;
import dulce.PixelFighter.sprite.inherited.*;
import static dulce.PixelFighter.sprite.PFvar.*;
import dulce.PixelFighter.main.*;
import static dulce.PixelFighter.main.Input.*;

public class Joe extends Boss {
	//TODO: BossJOE
	public void update() {
		if(isKeyPressed(GLFW_KEY_K))despawn();
		if(isKeyDown(GLFW_KEY_RIGHT)) {moveRight();} 
		if(isKeyDown(GLFW_KEY_LEFT)) moveLeft();
		if(isKeyDown(GLFW_KEY_UP)) coordy++;
		if(isKeyDown(GLFW_KEY_DOWN)) coordy--;
	}	
	
}
