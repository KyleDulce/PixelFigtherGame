package dulce.PixelFighter.sprite.inherited;

import static org.lwjgl.glfw.GLFW.*;
import dulce.PixelFighter.window.*;

import static dulce.PixelFighter.sprite.PFvar.*;
import static dulce.PixelFighter.main.Input.*;

public class PlayerDat extends Sprite {

	protected int health = 100000;
	protected int[] effectduration = new int[5];
	protected int[] inventory = new int[2];
	protected int[] ammo = new int[2];    //0=norm_ammo  1=bomb ammo
	
	public PlayerDat(Texture texture,int coordinateX, int coordinateY,int size, long window) {
		spawn(texture, Player_Dat, coordinateX, coordinateY, size, window);
	}
	
	protected void attack() {
		//TODO: player attack
	}
	
	protected void die() {
		if(health == 0) {
			//TODO: if player dies
		}
	}
	
	public void update() {
		if(isKeyDown(GLFW_KEY_D)) {
			if(facing != Right){
				//flipTex(Right);
				facing = Right;
			}
				
			moveRight();
		}
		
		if(isKeyDown(GLFW_KEY_A)) {
			if(facing != left) {
				//flipTex(left);
				facing = left;
			}
			moveLeft();
		} 
		if(isKeyDown(GLFW_KEY_W)) coordy++;
		if(isKeyDown(GLFW_KEY_S)) coordy--;
		if(isKeyPressed(GLFW_KEY_C)) setCoord(0, 0);
	}
	
	public void moveLeft() {
			coordx--;
		}
	public void moveRight() {coordx++;}
	
	public void setCoord(int x, int y) {coordx = x; coordy = y;}
	
}
