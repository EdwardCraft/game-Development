package Screens;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.imageio.ImageIO;

import Classes.Game;
import FrameWork.GameObject;
import FrameWork.ObjectId;
import Utils.Constants;

public class Pause {
	
	private BufferedImage pauseImange;
	
	
	public Pause(){
		try {
			pauseImange = ImageIO.read(
				getClass().getResourceAsStream(
					Constants.GAME_PAUSE
				)
			);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void render(Graphics g){
		g.drawImage(pauseImange, 
				(Constants.GAME_WINDOW_WIDTH - 270) / 2, 
				(Constants.GAME_WINDOW_HEIGHT - 300 ) / 2, null);
		g.dispose();
	}
	
	
}
