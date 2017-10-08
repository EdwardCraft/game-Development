package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Classes.Game;
import Entities.Manager;
import FrameWork.GameObject;
import Utils.Constants;
import Utils.Texture;
import Utils.Enums.ScreenState;

public class DeathScreen {
	
	private Texture texture;
	private Font font; 
	private int currentChoice;
	private Game game;
	private int health;
	
	protected final String[] options = {
			"continue",
			"Quit"
		};
	
	
	public DeathScreen(Game gane){
		this.game = gane;
		this.texture = Game.getTexture();
		currentChoice = 0;
		
		try{
			font = new Font("Arial", Font.PLAIN, 30);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	
	
	public void update(){
		
		
		
	}
	
	
	
	public void render(Graphics g){
		
		g.setColor(new Color(0,0,0));
		g.fillRect(0, 0, Constants.GAME_WINDOW_WIDTH, Constants.GAME_WINDOW_HEIGHT);
		
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.RED);
			}
			else {
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], 500, 300 + i * 40);
		}
		
		g.dispose();
		
	}
	
	public void select(Manager manager, GameObject player){
		if(currentChoice == 0){
			player.setHelath(Constants.PLAYER_HEALTH);
			game.setScreenState(ScreenState.Game);
		}else if(currentChoice == 1){
			manager.mapReset();
			game.setScreenState(ScreenState.Menu);
			game.setLevelOne(new LevelOne());
		}
		
	}
	
	public int getCurrentChoise(){
		return currentChoice;
	}
	
	public void  setCurrentChoise(int currentChoice){
		this.currentChoice = currentChoice;
	}
	
	public String[] getOptions(){
		return options;
	}
	
	public void setHealth(int  health){
		this.health = health;
	}
	
}
