package dulce.PixelFighter.world;

import org.joml.*;
import dulce.PixelFighter.window.*;

public class Background {

	private Texture             texture;
	private Draw                model;
	private Shader              shader;
	private Matrix4f            projection,
								scale,
								target;
	
	public Background() {
		float[] vertices = new float[] {
				-1f, 1.5f, 0,   //top left     indices 0
				1f, 1.5f, 0,    //top right            1
				1f, -1f, 0,     //bottom right         2
				-1f, -1f, 0,    //bottom left          3
		};
		
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
		shader = new Shader("Shader_Grad");
		texture = new Texture("background");
		
		projection = new Matrix4f()
						              .ortho2D((-1 * Window.getWidth()) / 2, Window.getWidth() / 2, -1 * Window.getHeight(), Window.getHeight());
		scale = new Matrix4f()        .translate(new Vector3f(0, 0, 0))
									  .scale(775);  //775
		target = new Matrix4f();
		projection.mul(scale, target);
	}
	
	public void renderBack() {
		shader.bind();
		shader.setUniform("sampler", 0);
		shader.setUniform("projection", target);
		texture.bind(0);
		model.render();
		
	}

}
