package dulce.PixelFighter.world;

public class Tile {

	public static Tile tiles[] = new Tile[120];
	
	private int id;
	private String texture;
	
	public Tile(int id, String Texture) {
		this.id = id;
		this.texture = Texture;
		if(tiles[id] != null){
			throw new IllegalStateException("tiles " + id + " is already being used!");
		}
		tiles[id] = this;
	}
	
	public void remove() {tiles[id] = null;}

    public int getId() {return id;}
	public String getTexture() {return texture;}
	public void setTexture(String texture) {this.texture = texture;}

}
