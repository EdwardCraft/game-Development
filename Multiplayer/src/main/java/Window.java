
import java.awt.Dimension;
import javax.swing.JFrame;


public class Window{

	 private GameMultiplayer gameMultiplayer;
	 private JFrame frame;


	public Window(){
		gameMultiplayer = new GameMultiplayer();

		gameMultiplayer.setPreferredSize(new Dimension( 1200, 720));
		gameMultiplayer.setMaximumSize(new Dimension( 1200, 720));
		gameMultiplayer.setMinimumSize(new Dimension( 1200, 720));

		frame = new JFrame("Test");
		frame.add(gameMultiplayer);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		gameMultiplayer.start();

	}

}