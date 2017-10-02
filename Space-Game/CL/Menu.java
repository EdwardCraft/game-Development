package CL;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.Color;
import java.awt.Rectangle;
public class Menu{


	public Rectangle playButton  =  new   Rectangle(SpaceGame.WIDTH/2+120,150,100,50);
	public Rectangle helpyButton  =  new  Rectangle(SpaceGame.WIDTH/2+120,250,100,50);
	public Rectangle quitButton  =  new   Rectangle(SpaceGame.WIDTH/2+120,350,100,50);

	public void render(Graphics g){

		Graphics2D g2d = (Graphics2D) g;


		Font Fnt0= new Font("arial",Font.BOLD,50);
		g.setFont(Fnt0);
		g.setColor(Color.white);
		g.drawString("Space Game", SpaceGame.WIDTH/2,100);


		Font fnt1 = new Font("arial",Font.BOLD,30);
		g.setFont(fnt1);
		g.drawString("play",playButton.x+20,playButton.y+35);
		g2d.draw(playButton);
		g.drawString("help",helpyButton.x+20,playButton.y+135);
		g2d.draw(helpyButton);
		g.drawString("Quit",quitButton.x+20,playButton.y+235);
		g2d.draw(quitButton);



	}


}