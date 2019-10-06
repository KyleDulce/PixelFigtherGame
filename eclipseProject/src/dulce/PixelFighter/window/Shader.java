package dulce.PixelFighter.window;

import static org.lwjgl.opengl.GL20.*;
import java.io.*;
import java.nio.*;

import org.joml.*;
import org.lwjgl.*;

public class Shader {

	private int program,
				vs,
				fs;
	
	public Shader(String File) {
		program = glCreateProgram();
		
		vs = glCreateShader(GL_VERTEX_SHADER);
		glShaderSource(vs, readFile(File + ".vs"));
		glCompileShader(vs);
		
		if(glGetShaderi(vs, GL_COMPILE_STATUS) != 1){
			System.out.println(glGetShaderInfoLog(vs));		
		}
		
		fs = glCreateShader(GL_FRAGMENT_SHADER);
		glShaderSource(fs, readFile(File + ".fs"));
		glCompileShader(fs);
		
		if(glGetShaderi(fs, GL_COMPILE_STATUS) != 1){
			System.out.println(glGetShaderInfoLog(fs));
		}
		
		glAttachShader(program, vs);
		glAttachShader(program, fs);
		
		glBindAttribLocation(program, 0, "vertices");
		glBindAttribLocation(program, 1, "textures");
		
		glLinkProgram(program);
		if(glGetProgrami(program, GL_LINK_STATUS) != 1) {
			System.out.println(glGetProgramInfoLog(program));
			return;
		}
		glValidateProgram(program);
		if(glGetProgrami(program, GL_VALIDATE_STATUS) != 1) {
			System.out.println(glGetProgramInfoLog(program));
			return;
		}
	}
	
	public void setUniform(String name, int value) {
		int location = glGetUniformLocation(program, name);
		if(location != -1) 
			glUniform1i(location, value);
	}
	
	public void setUniform(String name, Matrix4f value) {
		int location = glGetUniformLocation(program, name);
		FloatBuffer buffer = BufferUtils.createFloatBuffer(16);
		value.get(buffer);
		if(location != -1) 
			glUniformMatrix4fv(location, false, buffer);
	}
	
	public void bind() {
		glUseProgram(program);
	}
	
	private String readFile(String file) {
		StringBuilder String = new StringBuilder();
		BufferedReader br;
		try{
			br = new BufferedReader(new FileReader(new File("./Assets/Shaders/" + file)));
			String line;
			while((line = br.readLine()) != null) {
				String.append(line);
				String.append("\n");
			}
			br.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		return String.toString();
	}
	
}
