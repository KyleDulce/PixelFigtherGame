package dulce.PixelFighter.sprite;

import static dulce.PixelFighter.main.RandomGen.*;
import static dulce.PixelFighter.sprite.PFvar.*;
import dulce.PixelFighter.window.*;
import dulce.PixelFighter.logs.Error;
import dulce.PixelFighter.sprite.enemy.basicBoss.*;
import dulce.PixelFighter.sprite.enemy.boss.*;
import dulce.PixelFighter.sprite.inherited.*;

public class AllEntities {

	//active entites
	private static Integer[] entity_id = new Integer[100];
	//loaded entites
	private static Sprite sprites[] = new Sprite[100];
	//player object
	private static PlayerDat player;
	//loaded textures
	private static Texture[] textures = new Texture[20];
	
	public static void enInit() {
		//initionalize Textures TODO: finish texture loading
		textures[playerTex] 	= new Texture("player", true);
		textures[swordItTex]	= new Texture("swordItem");
		textures[joeTex]		= new Texture("JoeBoss", true);
	}
	
	public static void update() {
		//function to updating all current entities
		int calling = 0;
		try{
			for(int x = 0; x < (entity_id.length);x++) { //loop for entire array
				calling = x;
				if(entity_id[x] != null) //test if location is null
					sprites[x].update();  //update sprite
			}
			//update player
			if(player != null) {
				player.update();
			}
		}catch(Exception e) {
			Error.crashReport("the Game has failed to update sprites", e.getStackTrace(), "sprites[x].update() had no object at location: " 
																						 + calling + "   "
																						 + entity_id[calling]);
		}
	}
	
	public static void render() {
		//function for rendering entites
		int calling = 0;
		try{
			for(int x = 0; x < (entity_id.length);x++) {  //loop for active array
				calling = x;
				if(entity_id[x] != null) //test if null
					sprites[x].render();  //render active sprite
			}
			//render player
			if(player != null){
				player.render();
			}
		}catch(Exception e){
			Error.crashReport("the Game has failed to render sprites", e.getStackTrace(), "entity_object[x].update() had no object at location: " 
																						  + calling + "   "
																						  + entity_id[calling]);
		}
	}
	
	public static void spawn(int enemyType, int spawnCx, int spawnCy, long window) {
		//spawn entity
		
		//give the entity an id
		int id = intGen(entity_id.length, 0);
		//test if id is being used
		while(entity_id[id] != null){
			id = intGen(entity_id.length, 0);  //give another id
		}
		
		//enemy types and spawning
		switch(enemyType) {
			//vasic boss
			case zombie:    sprites[id] = new Zombie();
						    sprites[id].spawn(textures[zombieTex], id, spawnCx, spawnCy, 50, window);
						    entity_id[id] = Basic_boss;
						    break;
			case gunman:    sprites[id] = new Gunman();
						    sprites[id].spawn(textures[gunmanTex], id, spawnCx, spawnCy, 50, window);
						    entity_id[id] = Basic_boss;
			 			    break;
			case bomberman: sprites[id] = new BoomMan();
			 				sprites[id].spawn(textures[bombermanTex], id, spawnCx, spawnCy, 50, window);
			 				entity_id[id] = Basic_boss;
			 			    break;
			//boss
			case joe:		sprites[id] = new Joe();
							sprites[id].spawn(textures[joeTex], id, spawnCx, spawnCy, 50, window);
							entity_id[id] = Boss;
							break;
		}
		//test if failed
		if(sprites[id] == null || entity_id[id] == null){
			System.out.println("Spawn failed");
			sprites[id] = null;
			entity_id[id] = null;
		}
			
		
	}
	
	//despawn entity
	public static void despawn(int id) {entity_id[id] = null;}
	
	//remove all entities NOT player
	public static void despawnAll() {
		for(int x = 0; x <= entity_id.length; x++) {
			entity_id[x] = null;
		}
	}
	
	//spawn player
	public static void spawnPlayer(int spawnCx, int spawnCy, long window) {
		player = new PlayerDat(textures[playerTex], spawnCx, spawnCy, 50, window);}
	
	//despawn player
	public static void despawnPlayer() {player = null;}
	
}
