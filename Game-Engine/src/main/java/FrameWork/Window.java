package FrameWork;
import Logic.RecycleGame;
import java.awt.Dimension;
import javax.swing.JFrame;
import util.Constants;

public class Window{

	private JFrame frame;

	public Window(int width, int height, String title, RecycleGame game){
		
		game.setPreferredSize(new Dimension( width, height));
		//game.setMaximumSize(new Dimension( width, height));
		game.setMinimumSize(new Dimension(width, height));
		
		frame = new JFrame(title);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();

	}


}