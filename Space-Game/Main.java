import CL.SpaceGame;
import javax.swing.JFrame;
import java.awt.Dimension;

public class Main{

	public static final int WIDTH  = 320;
	public static final int HEIGHT = WIDTH / 12 * 9;
	public static final int SCALE  = 2;

	public static void main(String args[]){
		SpaceGame game= new SpaceGame();
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));

        JFrame  frame = new JFrame(game.TITLE);
        frame.add(game);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();

   }



}