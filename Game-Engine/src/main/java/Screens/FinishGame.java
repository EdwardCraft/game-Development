package Screens;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import Logic.RecycleGame;
import util.Enums.ScreenState;
import util.Constants;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class FinishGame{

	private Font font; 
	private int currentChoice;
	private RecycleGame game;
	private final String[] options = {
			"Menu",
			"Quit"
		};


	public FinishGame(RecycleGame game){
		this.game = game;
		currentChoice = 0;
		
		try{
			font = new Font("Arial", Font.PLAIN, 30);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}


	public void render(Graphics g){


		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.RED);
			}
			else {
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], Constants.GAME_WIDTH  / 2, 
				(Constants.GAME_HEIGHT - 150) + i * 40);
		}

		

		g.dispose();

	}

	public void select(){
		if(currentChoice == 0){
			game.setScreenState(ScreenState.Menu);
			game.setNewMiniGame(new miniGame(game));
			game.setNewMiniGameKey();
			game.setNewMiniGame(new miniGame(game));
			game.setNewMiniGameKey();
		}else if(currentChoice == 1){
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