package dulce.PixelFighter.window;

import java.awt.geom.*;
import java.awt.image.*;
import java.io.*;
import java.nio.*;
import javax.imageio.*;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import org.lwjgl.*;

import static dulce.PixelFighter.sprite.PFvar.Right;
import dulce.PixelFighter.logs.Error;

public class Texture {
	
	private int    id ,
				   id2,
				   width , 
				   height; 
	private String Location;
	private String Filename;
	private int facing;

	public Texture(String file) {
		Filename = file;
		BufferedImage img;
		Location = ("./Assets/" + file + ".png");
		String error = "Error not found";
		try {
			File f = new File(Location);
			if(!f.exists()){
				error = "file at : " + Location + " does not exist!";
				throw new IllegalPathStateException("file does not exist!");
			}
			
			if(!f.canRead()){
				error = "file at :" + Location + " cannot be read!";
				throw new IllegalAccessException("file cannot be read!");
			}
			
			img = ImageIO.read(f);
			width = img.getWidth();
			height = img.getHeight();
			int size = (width * height * 4);
			
			int[] pixels_raw = new int[size];
			
			pixels_raw = img.getRGB(0, 0, width, height, pixels_raw, 0, width);
			ByteBuffer img_pixels = BufferUtils.createByteBuffer(size);
			
			for(int h = 0; h < height; h++) {
				for(int w = 0; w < width; w++) {
					
					int pixel = pixels_raw[h * width + w];
					img_pixels.put((byte)((pixel >> 16) & 0xFF)); //red
					img_pixels.put((byte)((pixel >> 8) & 0xFF));  //green
					img_pixels.put((byte)((pixel) & 0xFF));       //blue
					img_pixels.put((byte)((pixel >> 24) & 0xFF)); //alpha

				}
			}
			
			img_pixels.flip();
			id = glGenTextures();
			glBindTexture(GL_TEXTURE_2D, id);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, img_pixels);
				
		}catch(IOException | IllegalPathStateException | IllegalAccessException e) {
			Error.crashReport("A texture failed to load: ", file, e.getStackTrace(), "failed to load: " + file + " reasoning: " + error);
		}
	}
	
	public Texture(String file, boolean dualTex) {
		Filename = file;
		BufferedImage img;
		Location = ("./Assets/" + file + ".png");
		String error = "Error not found";
		try {
			File f = new File(Location);
			if(!f.exists()){
				error = "file at : " + Location + " does not exist!";
				throw new IllegalPathStateException("file does not exist!");
			}
			
			if(!f.canRead()){
				error = "file at :" + Location + " cannot be read!";
				throw new IllegalAccessException("file cannot be read!");
			}
			
			img = ImageIO.read(f);
			width = img.getWidth();
			height = img.getHeight();
			int size = (width * height * 4);
			
			int[] pixels_raw = new int[size];
			
			pixels_raw = img.getRGB(0, 0, width, height, pixels_raw, 0, width);
			ByteBuffer img_pixels = BufferUtils.createByteBuffer(size);
			
			//Texture to right
			
			for(int h = 0; h < height; h++) {
				for(int w = 0; w < width; w++) {
					
					int pixel = pixels_raw[h * width + w];
					img_pixels.put((byte)((pixel >> 16) & 0xFF)); //red
					img_pixels.put((byte)((pixel >> 8) & 0xFF));  //green
					img_pixels.put((byte)((pixel) & 0xFF));       //blue
					img_pixels.put((byte)((pixel >> 24) & 0xFF)); //alpha

				}
			}
			
			//Texture to left
			
			
			
			/*for(int h = 0; h < height; h++) {
				for(int w = (width - 1); w < width; w--) {
					
					int pixel = pixels_raw[h * width + (((-2 * w) + (width - 1)) + w)];
					img_pixels.put((byte)((pixel >> 16) & 0xFF)); //red
					img_pixels.put((byte)((pixel >> 8) & 0xFF));  //green
					img_pixels.put((byte)((pixel) & 0xFF));       //blue
					img_pixels.put((byte)((pixel >> 24) & 0xFF)); //alpha
					
				}
			}*/
			
			facing = Right;
			
			//facing right
			img_pixels.flip();
			id = glGenTextures();
			glBindTexture(GL_TEXTURE_2D, id);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, img_pixels);
			
			//facing left
			/*if(dualTex){
				img_pixels.flip();
				id2 = glGenTextures();
				glBindTexture(GL_TEXTURE_2D, id2);
				glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
				glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
				glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, img_pixels);	
			}*/
			
		}catch(IOException | IllegalPathStateException | IllegalAccessException e) {
			Error.crashReport("A texture failed to load: ", file, e.getStackTrace(), "failed to load: " + file + " reasoning: " + error);
		}
	}
	
	/*public void flip(int direction) {
		facing = direction;
		System.out.println("flip!");
	}*/
	
	//to render
	public void bind(int sampler) {
		System.out.println(facing);
		if(sampler >= 0 && sampler <= 31) {
		glActiveTexture(GL_TEXTURE0 + sampler);
		if(facing == Right){
			glBindTexture(GL_TEXTURE_2D, id);
			System.out.println("use 1");
		}	
		else {
			glBindTexture(GL_TEXTURE_2D, id2);
			System.out.println("use 2");
		}
			
		}
	}
	
	public String getLocation() {return Location;}
	public String getFilename() {return Filename;}
	
}
