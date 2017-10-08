package Screens;

import java.util.LinkedList;
import java.awt.Graphics;
import FrameWork.GameObject;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import util.Constants;
import Entities.ContainerCan;
import util.Enums.ObjectId;
import Entities.Can;
import Entities.ContainerFood;
import Entities.ContainerPaper;
import java.awt.Color;
import java.awt.Font;
import util.Enums.BinState;
import Logic.RecycleGame;

public class miniGame{


	private LinkedList<GameObject> gameObjects;
	private LinkedList<GameObject> canObjects;
	private GameObject tempObject;
	private BufferedImage gameBackground;
	private Font font; 
	private hubMiniGame hud;
	private int gameWidth;
	private int gameHeight;
	private RecycleGame game;

	private final String[] options = {
			"z",
			"x",
			"c"
		};
	
	public miniGame(RecycleGame  game){
		this.game = game;
		gameObjects = new LinkedList<GameObject>();
		canObjects = new LinkedList<GameObject>();
		hud = new hubMiniGame(canObjects , game);

		try{
			font = new Font("Arial", Font.PLAIN, 30);
			gameBackground = ImageIO.read(
				getClass().getResourceAsStream(
					Constants.GAME_MINI_RECYCLE_IMAGE
				)
			);
		}catch(Exception e){
			e.printStackTrace();
		}

		debugLevel();
	}

	public void update(double delta){

		for(int i = 0; i < gameObjects.size(); i ++ ){
			tempObject = gameObjects.get(i);
			tempObject.update(delta);
		}

		if(canObjects != null){
			for(int j = 0; j < canObjects.size(); j++ ){
				canObjects.get(j).update(delta);
			}
		}


	}

	public void render(Graphics g , int gameWidth, int gameHeight){
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		g.drawImage(gameBackground, 0, 0, gameWidth, gameHeight, null);

		for(int i = 0; i < gameObjects.size(); i ++ ){
			tempObject = gameObjects.get(i);
			tempObject.render(g , gameWidth, gameHeight);
		}

		if(canObjects != null){
			for(int j = 0; j < canObjects.size(); j++ ){
				canObjects.get(j).render(g, gameWidth, gameHeight);
			}
		}

		g.setFont(font);

		for(int i = 0; i < options.length; i++) {
			g.setColor(Color.WHITE);
			g.drawString(options[i], 115 + (i * 150), gameHeight );
		}

		hud.render(g, gameWidth, gameHeight);

		if(hud.getCountDown() == 0){
			g.setColor(new Color(0f,0f,0f,.7f));
	    	g.fillRect(0, 0, gameWidth + 10, gameHeight + 10);
		}

	}	

	public void addObject(GameObject object, BinState state){
		if(state == BinState.BIN){
			gameObjects.add(object);
		}
		if((state == BinState.METAL) || (state == BinState.FOOD) || (state == BinState.PLASTIC)){
			canObjects.add(object);
		}
	
	}
	
	public void removeObject(GameObject object, BinState state){
		if(state == BinState.BIN){
			gameObjects.remove(object);
		}
		if((state == BinState.METAL) || (state == BinState.FOOD) || (state == BinState.PLASTIC)){
			canObjects.remove(object);
		}

	}

	public void debugLevel(){

		addObject(new ContainerCan(
			0, 
			0, 
			ObjectId.metalBin),
			BinState.BIN);

		addObject(new ContainerFood(
			0 , 
			0 , 
			ObjectId.foodBin),
			BinState.BIN);

		addObject(new ContainerPaper(
			0 , 
			0 , 
			ObjectId.plasticBin),
			BinState.BIN);
		
		for(int  i = 0; i < 30 ; i++){
			addObject(new Can(
			0 , 
			-(i * 100),
			ObjectId.metalTrash,
			this,
			hud),
			BinState.METAL);
		}

	
	}

	public LinkedList<GameObject> getGameObjects(){
		return gameObjects;
	}

	public LinkedList<GameObject> getTrashObjects(){
		return canObjects;
	}

	public hubMiniGame getHud(){
		return hud;
	}


}