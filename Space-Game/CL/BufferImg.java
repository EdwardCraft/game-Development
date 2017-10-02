package  CL;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
//class to load de  img
public  class BufferImg{

	private BufferedImage image;

	public BufferedImage loadImage(String path) throws IOException{

		image = ImageIO.read(getClass().getResource(path));
		return image;


	}



}