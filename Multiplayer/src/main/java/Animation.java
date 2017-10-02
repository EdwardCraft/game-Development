
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Animation {
	
	private float speed;
	private int frames;
	private float index =0;//what we are current at
	private int count =0;//where we need to be 
	private BufferedImage[] images;
	private BufferedImage currentImg;

	public Animation(float speed, BufferedImage... args){
		this.speed = speed;
		images = new BufferedImage[args.length];

		for(int i = 0; i < args.length; i++)
		{
			images[i] = args[i];
			
		}
		frames = args.length;
	}


	public void runAnimation(){
		
		index ++;
		if(index > speed)
		{
			index = 0;
			nextFrame();				
		}

	}

	private void nextFrame(){
		 currentImg = images[count % frames];
         count++;

	}

	public void drawAnimation(Graphics g, int x, int y){

		g.drawImage(currentImg, x, y, null);

	}

	public void drawAnimation(Graphics g, int x, int y,int scaleX, int scaleY){

		g.drawImage(currentImg, x, y, scaleX, scaleY, null);
			
	}
	
	
	
}