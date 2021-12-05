/*
Michael Darden
10/21/2021
Homework 5
*/

import javax.swing.JFrame;
import java.awt.Toolkit;

public class Game extends JFrame
{
	Model model;
	Controller controller;
	View view;

	public Game()
	{
		model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		view.addMouseListener(controller);
		view.addMouseMotionListener(controller);
		this.addKeyListener(controller);
		this.setTitle("It's a me, Mario!");
		this.setSize(1000, 700);
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}

	//thes is updating the screen
	public void run()
	{
		while(true)
		{
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen

			// Go to sleep for 50 miliseconds
			try
			{
				Thread.sleep(25); //frame rate
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
}
