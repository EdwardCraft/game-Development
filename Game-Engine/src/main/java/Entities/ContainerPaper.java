package Entities;

import FrameWork.GameObject;
import util.Enums.ObjectId;
import java.awt.Graphics;
import java.awt.Rectangle;
import util.Enums.RectangleBounds;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import util.Constants;
import java.awt.Graphics2D;
import java.awt.Color;

public class ContainerPaper extends GameObject{

	private BufferedImage foodBin;
	private int gameWidth;
	private int gameHeight;

	public ContainerPaper(float x, float y, ObjectId id){
		super(x, y, id);
		try{
			foodBin = ImageIO.read(
				getClass().getResourceAsStream(
					Constants.PAPER_TRASH_IMAGE
				)
			);

		}catch(Exception e){
			e.printStackTrace();
		}


	}


	public void update(double delta){

	}
	public void render(Graphics g, int gameWidth,int gameHeight){
		this.gameWidth = gameWidth;
		this.gameHeight = gameHeight;
		Graphics2D g2d = (Graphics2D) g; 
		g.setColor(Color.red);
		g2d.draw(getBounds());
		g.drawImage(foodBin, 
			(int)x + gameWidth - (Constants.TRASH_IMGAME_WIDTH + 100), 
			(int)y + gameHeight - Constants.TRASH_IMGAME_HEIGHT, null);

	}





	public Rectangle getBounds(){
		return new Rectangle(
				(int)x + gameWidth - (Constants.TRASH_IMGAME_WIDTH + 100) + 2,
				(int)y + gameHeight - Constants.TRASH_IMGAME_HEIGHT + 6,
				97,
				20
			);
	}
	
	public Rectangle getBoundsTop(){
		return null;
	}
	public Rectangle getBoundsRight(){
		return null;
	}
	public Rectangle getBoundsLeft(){
		return null;
	}



}