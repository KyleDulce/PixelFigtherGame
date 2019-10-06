package dulce.PixelFighter.world;

import java.util.*;
import org.joml.*;
import static dulce.PixelFighter.sprite.PFvar.*;
import dulce.PixelFighter.window.*;

public class TileRender {

	private HashMap<String, Texture> tile_textures;
	private Draw model;
	
	public TileRender(float[] vertices){
		tile_textures = new HashMap<String, Texture>();
		/*float[] vertices = new float[] {
				-0.75f, 1.5f, 0,   //top left     indices 0
				0.75f, 1.5f, 0,    //top right            1
				0.75f, -1f, 0,     //bottom right         2
				-0.75f, -1f, 0,    //bottom left          3
		};*/
		
		float[] textureLoc = new float[] {
				0,0,            //top left     indices 0
				1,0,            //top right            1
				1,1,            //bottom right         2
				0,1,            //bottom left          3
		};
		
		int[] indices = new int[] {
				0,1,2,
				2,3,0,
		};
		
		model = new Draw(vertices, textureLoc, indices);

		for(int i = 0; i < Tile.tiles.length; i++) {
			if(Tile.tiles[i] != null) {
				if(!tile_textures.containsKey(Tile.tiles[i].getTexture())){
					String tex = Tile.tiles[i].getTexture();
					tile_textures.put(tex , new Texture(tex));
				}
			}
			
		}
	}
	
	public void renderTile(int id, float x, float y, Shader shader, Matrix4f world, Matrix4f projection) {
		shader.bind();
		if(tile_textures.containsKey(Tile.tiles[id].getTexture())) {
			tile_textures.get(Tile.tiles[id].getTexture()).bind(0);
		}
		
		Matrix4f tile_pos = new Matrix4f().translate(new Vector3f(x, y, 0));
		Matrix4f target = new Matrix4f();
		
		projection.mul(world, target);
		target.mul(tile_pos);
		
		shader.setUniform(Sampler, 0);
		shader.setUniform(Projection, target);
		
		model.render();
	}

}
