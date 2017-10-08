package Screens;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import Classes.Game;
import Entities.Manager;
import Utils.Constants;
import Utils.Enums.ScreenState;
import Utils.Texture;


public class Menu {
	
	private Texture texture;
	private Font font; 
	private int currentChoice;
	private Game game;
	
	protected final String[] options = {
			"Start",
			"Help",
			"Quit"
		};
	
	
	public Menu(Game game) {
		this.game = game;
		texture = Game.getTexture();
		currentChoice = 0;
		
		try{
			font = new Font("Arial", Font.PLAIN, 30);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}


	public void update() {
		
		
	}


	public void render(Graphics g) {
		
		g.drawImage(texture.menuBackground[0], 0, 0, Constants.GAME_WINDOW_WIDTH + 10, Constants.GAME_WINDOW_HEIGHT + 10, null);
		
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.BLACK);
			}
			else {
				g.setColor(Color.RED);
			}
			g.drawString(options[i], 500, 300 + i * 40);
		}
		
		g.dispose();
	}
	
	
	public void select(){
		if(currentChoice == 0){
			game.setScreenState(ScreenState.Game);
		}else if(currentChoice == 1){
			
		}else if(currentChoice == 2){
			System.exit(0);
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
	

	




	
	
	
}
