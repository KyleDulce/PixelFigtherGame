package dulce.PixelFighter.sprite.inherited;

public abstract class Entity extends Sprite {

	protected int health = 20000;
	
	protected void attack() {
		//TODO: attack for entities
	}
	
	protected void die() {
		if(health == 0) {
			//TODO: if entity dies (overide by player)
		}
	}	
		
}
