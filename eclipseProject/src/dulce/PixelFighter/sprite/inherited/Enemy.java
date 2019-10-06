package dulce.PixelFighter.sprite.inherited;

import static dulce.PixelFighter.sprite.PFvar.*;

public abstract class Enemy extends Entity {

	protected void moveLeft() {
		if(facing != left) {
			//flipTex(left);
			facing = left;
		}
			
		coordx--;
	}
	protected void moveRight() {
		if(facing != Right) {
			//flipTex(Right);
			facing = Right;
		}		
		coordx++;
	}
	
	protected void findPlayer() {
		//TODO implement find player method
		
	}
	
	protected void IfPlayerNear() {
		//TODO: test if player near
	}
	
	
}
