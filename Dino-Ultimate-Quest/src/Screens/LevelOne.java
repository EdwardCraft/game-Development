package Screens;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import Audio.GameAudio;
import Classes.Game;
import FrameWork.Dialogue;
import Utils.Constants;
import Utils.Texture;

public class LevelOne {

	private Texture texture;
	private BufferedImage cloud;
	private float positionX;
	private int positionY;
	private float velocityX;
	private int velocityY;
	Dialogue dialogue;
	public LevelOne(){
		this.texture = Game.getTexture();
		positionX = 600;
		positionY = 0;
		velocityX = 0.5f;
		velocityY = 0;
		dialogue = new Dialogue();
		try {
			cloud = ImageIO.read(
				getClass().getResourceAsStream(
					Constants.GAME_LEVEL_1_CLOUDS
				)
			);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public void update(double delta){
		positionX -= velocityX * delta;
		
		
	}
	
	
	
	
	public void render(Graphics g){
	
		
		for(int i = 0; i < texture.Backgrounds[1].getWidth() * 10; i +=texture.Backgrounds[1].getWidth())
			g.drawImage(texture.Backgrounds[1], i, 50,null); 
		
		g.drawImage(cloud, (int)positionX+1000,150,null);
		g.drawImage(cloud, (int)positionX + 1500,90,null);
		g.drawImage(cloud, (int)positionX + 1300 ,300,null);
		
		
		
	}

	
	public Rectangle getBounds(){
		
		return  new Rectangle(
				(int)((int)positionX+(Constants.CLOUD_RECTANGLE_WIDTH / 4)),
				(int)((int)150+(Constants.CLOUD_RECTANGLE_HEIGHT/2)),
				(int)Constants.CLOUD_RECTANGLE_WIDTH/2,
				(int)Constants.CLOUD_RECTANGLE_HEIGHT/2);
	}
	
}
