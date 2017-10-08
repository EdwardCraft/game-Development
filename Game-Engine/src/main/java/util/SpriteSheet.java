package util;
import java.awt.image.BufferedImage;

public class SpriteSheet{


	private BufferedImage image;
	private BufferedImage img;
	
	public SpriteSheet(BufferedImage image){
		this.image = image;
	}

	public BufferedImage grabImage(int col, int row, int width, int height){
		return img = image.getSubimage((col*width)-width,
			(row*height)-height,width,height);
	}





}