package Entities;
import java.util.LinkedList;

import FrameWork.GameObject;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import Classes.Game;
import FrameWork.ObjectId;
import FrameWork.PlayerCam;
import Screens.Menu;
import Utils.BufferedImageLoader;
import Utils.Constants;
import Utils.Texture;


public class Manager{

	public  LinkedList<GameObject> gameObjects;
	private GameObject tempObject;
	private PlayerCam camera;
	private BufferedImageLoader imageLoader;
	private Texture texture;
	private Player player;
	private  int level;
	private Game game;
	
	public Manager(PlayerCam camera, Game game){
		this.game = game;
		texture = Game.getTexture();
		this.camera = camera;
		gameObjects = new LinkedList<GameObject>();
		imageLoader = new BufferedImageLoader();
		level = 1;
		switchLevel(level);
		
	}
	

	public void switchLevel(int level){
		clearLevel();
		
		camera.setPositionX(0);	
		camera.setPositionY(0);
		
		switch(Constants.LEVELS)
		{
			case 1:			
				loadingImages(texture.Level1Game);
			break;
			case 2:
				loadingImages(texture.levels[Constants.LEVELS]);
			break;
			case 3:
				loadingImages(texture.levels[Constants.LEVELS]);
			break;
			case 4:
			break;

		}
		
				
	}
	

	private void clearLevel(){
		gameObjects.clear();
	}
	
	
	public void mapReset(){
		clearLevel();
		camera.setPositionX(0);
		loadingImages(texture.Level1Game);
		level = 1;
	}
	
	
	public void update(){

		for(int i = 0; i < gameObjects.size(); i ++ ){
			tempObject = gameObjects.get(i);
			tempObject.update(gameObjects);
			if(tempObject.getObjectId() == ObjectId.Blast){
				if(!camera.getBounds().contains(tempObject.getBoundsRight())){
					removeObject(tempObject);
					System.out.println("remove right");
				}
				if(!camera.getBounds().contains(tempObject.getBoundsLeft())){
					removeObject(tempObject);
					System.out.println("remove left");
				}
			}
		}
		
	}
	
	

	
	public void render(Graphics g){	
	
			for(int i = 0; i < gameObjects.size(); i ++ ){
				tempObject = gameObjects.get(i);
				tempObject.render(g);
			}
			
	}


	
	public void addObject(GameObject object){
		this.gameObjects.add(object);
	}

	
	public void removeObject(GameObject object){
		this.gameObjects.remove(object);
	}
	

	
	public  void loadingImages(BufferedImage image){
		int width = image.getWidth();
		int height = image.getHeight();
		
		//where looping through every single pixel on the image
		for(int i = 0;i < height; i++)
		{		
			for(int j = 0;j < width; j++)
			{
				int pixel = image.getRGB(i,j);
				int red   = (pixel >> 16) & 0xff;
				int green = (pixel >> 8)  &  0xff;
				int blue  = (pixel) & 0xff;
				
				if(red == 255 && green == 255 && blue == 0) addObject(new Flag(i * 32, j * 32,ObjectId.FlagA));
				if(red == 255 && green == 0 && blue == 0) addObject(new Flag(i * 32, j * 32,ObjectId.FlagB));
				if(red == 255 && green == 255 && blue == 255) addObject(new Block(i * 32, j * 32,0,ObjectId.Block));
				if(red == 129 && green == 243 && blue == 158) addObject(new Block(i * 32, j * 32,1,ObjectId.Block));
				if(red == 0 && green == 0 && blue == 255) addObject(new Player(i * 32, j * 32,this,ObjectId.Player, game));
				if(red == 0 && green == 250 && blue == 0) addObject(new Minion(i * 32, j * 32,this,ObjectId.Enemy));
				
			}		
		}

	}

	public PlayerCam getCamera(){
		return camera;
	}

}