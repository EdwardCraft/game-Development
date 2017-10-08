package Screens;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import Logic.RecycleGame;
import util.Enums.ScreenState;
import util.Constants;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import Screens.miniGame;

public class Menu {
	
	private Font font; 
	private int currentChoice;
	private RecycleGame game;
	private BufferedImage mainMenu;
	private BufferedImage menuIcon;

	private final String[] options = {
			"Recycle Game",
			"Ladder Game",
			"Quit"
		};
	
	
	public Menu(RecycleGame game) {
		this.game = game;
		currentChoice = 0;
		
		try{
			font = new Font("Arial", Font.PLAIN, 30);

			mainMenu = ImageIO.read(
				getClass().getResourceAsStream(
					Constants.GAME_MENU_IMAGE
				)
			);

			menuIcon = ImageIO.read(
				getClass().getResourceAsStream(
					Constants.GAME_MENU_ICON
				)
			);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}


	public void update() {
		
		
	}


	public void render(Graphics g , int gameWidth, int gameHeight) {

		g.drawImage(mainMenu, 0, 0, gameWidth + 10, gameHeight + 10, null);

		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.WHITE);
				g.drawImage(menuIcon, (gameWidth  / 2) - 50, (gameHeight - 182) + i * 40, null);
			}
			else {
				g.setColor(Color.WHITE);
			}
			g.drawString(options[i], gameWidth  / 2, (gameHeight - 150) + i * 40);
		}

		

		g.dispose();
	}
	
	
	public void select(){
		if(currentChoice == 0){
			game.setScreenState(ScreenState.Game);
		}else if(currentChoice == 1){
			game.setScreenState(ScreenState.Ladder);
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