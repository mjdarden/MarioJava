/*
Michael Darden
10/21/2021
Homework 5
*/

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
//import java.io.IOException; //never used
import java.io.File;
import javax.swing.JButton;
import java.awt.Color;

class View extends JPanel
{
	JButton b1;
    Model model;
	Color backgroundColor = new Color(128, 255, 255);
	Color boxColor = new Color(255, 0, 0);
	BufferedImage background;

	View(Controller c, Model model)
	{
        this.model = model;
		background = loadImage("background.png");
		c.setView(this);
	}

	static BufferedImage loadImage(String fileName) {
		BufferedImage im = null;
		try
		{
			im = ImageIO.read(new File(fileName));
			System.out.println(fileName + " loaded");
		} catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1);
		}
		return im;
	}


    //draw stuff to screen
    public void paintComponent(Graphics g)
	{
		//draw background
		g.setColor(backgroundColor);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
        g.drawImage(background, Controller.backgroundPos, 0, 1000, 800, null);
		//draw sprites
		for(int i = 0; i < model.sprites.size(); i++)
		{
			Sprite s = model.sprites.get(i);
			s.draw(g);
		}
	}
}
