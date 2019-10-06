package dulce.PixelFighter.sprite.inherited;

import org.joml.*;
import dulce.PixelFighter.sprite.*;
import dulce.PixelFighter.window.*;
import dulce.PixelFighter.world.*;

import static dulce.PixelFighter.sprite.PFvar.*;


public abstract class Sprite {
	
	protected int 				//coordinates
								coordx,
	              				coordy,
	              				//id
	              				id,
	              				//facing direction
	              				facing,
	              				//size of texture
	              				size;
								//texture used
	private Texture 			texture;
								//model not used in class
	//private Draw 				model;
								//active shader
	private Shader 				shader;
								//projection of player
	private Matrix4f            projection,
								//scale and facing of player
								scaleLeft,
								scaleRight,
								activeScale;
								
	private Vector3f            translation;
	protected long 				window;
	private TileRender 			tiles_render;
	private Tile 				tiles;
	
	//temp
	public Sprite() {}
	
	public void spawn(Texture texture, int identifictionNumber, int coordinateX, int coordinateY,int size, long window) {
		this.window = window;
		coordx = coordinateX;
		coordy = coordinateY;
		id = identifictionNumber;
		this.size = size;
		
		// set vertices
		float[] vertices = new float[] {
				-0.5f, 1.75f, 0,     //top left     indices 0
				0.5f, 1.75f, 0,      //top right            1
				0.5f, -1f, 0,     //bottom right         2
				-0.5f, -1f, 0,    //bottom left          3
		};
		
		/*float[] textureLoc = new float[] {
				0,0,            //top left     indices 0
				1,0,            //top right            1
				1,1,            //bottom right         2
				0,1,            //bottom left          3
		};
		
		int[] indices = new int[] {
				0,1,2,
				2,3,0,
		};*/
		
		
		this.texture = texture;
		facing = Right;
		try{
			if(this.texture == null) throw new NullPointerException("No Texture found!");
		}catch(NullPointerException e) {
			despawn();
			System.out.println("spawn falied");
			return;
		}
		tiles = new Tile(id, texture.getFilename());
		tiles_render = new TileRender(vertices);
		shader = new Shader("Shader_Grad");		
		translation = new Vector3f(coordx, coordy, 0);
		projection = new Matrix4f()
						              .ortho2D((-1 * Window.getWidth()) / 2, Window.getWidth() / 2, -1 * Window.getHeight(), Window.getHeight());
		//facing directions
		//scaleRight = new Matrix4f()   .translate(translation)
		//							  .scale((float)size, (float) size, 1);  
		//scaleLeft = new Matrix4f()    .translate(translation)
		//							  .scale(((float)size) * -1f, (float) size, 1);
		activeScale = new Matrix4f() .translate(translation)
									 .scale((float)size, (float)size, 1);
		
	}
	
	protected void despawn() {
		if(id == Player_Dat)
		AllEntities.despawnPlayer();
		else AllEntities.despawn(id);
		tiles.remove();
	}
	
	public void render() {
		
		float trueCoordx = ((float)coordx) / 10;
		float trueCoordy = ((float)coordy) / 10;
		
		tiles_render.renderTile(id, trueCoordx, trueCoordy, shader, activeScale, projection);
	}
	
	/*protected void flipTex(int direction) {
		//texture.flip(direction);
		/*if(direction == left)
			activeScale = scaleLeft;	
		else
			activeScale = scaleRight;
		activeScale.translate(new Vector3f(coordx, coordy, 0));
		activeScale.scale(-1, 1, 1);
		facing = direction;
	}*/
	
	public abstract void update();
	
}
