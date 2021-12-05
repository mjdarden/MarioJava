/*
Michael Darden
10/21/2021
Homework 5
*/

import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

class Controller implements ActionListener, MouseListener, KeyListener, MouseMotionListener
{
	View view;
    Model model;
    boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean keySpace;
	boolean canEdit;
	boolean coinMode = false;
	static int backgroundPos = 0;

	Controller(Model m)
	{
		model = m;
		canEdit = false;
	}

	void setView(View v)
	{
		view = v;
	}

    //check for actions
	public void actionPerformed(ActionEvent e)
	{
	}

    //check for mouse clicks
    public void mousePressed(MouseEvent e)
	{
		if (canEdit == true){
			model.setPosition(e.getX(), e.getY());
		}
	}
	public void mouseDragged(MouseEvent e) {
		if (canEdit == true){
			model.setDimensions(e.getX(), e.getY(), coinMode);
		}
	}
	public void mouseMoved(MouseEvent e) {	  }
	public void mouseReleased(MouseEvent e) {
		if (canEdit == true){
			model.setDimensions(e.getX(), e.getY(), coinMode);
		}
	}
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) {    }

    //check for key presses
    public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; break;
			case KeyEvent.VK_LEFT: keyLeft = true; break;
			case KeyEvent.VK_UP: keyUp = true; break;
			case KeyEvent.VK_DOWN: keyDown = true; break;
			case KeyEvent.VK_SPACE: keySpace = true; break;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break;
			case KeyEvent.VK_LEFT: keyLeft = false; break;
			case KeyEvent.VK_UP: keyUp = false; break;
			case KeyEvent.VK_DOWN: keyDown = false; break;
			case KeyEvent.VK_SPACE: keySpace = false; break;
			case KeyEvent.VK_ESCAPE: 
				System.out.println("Exiting Now...");
				System.exit(0);
				break;
		}
		char c = e.getKeyChar();
		if (c == 's' || c == 'S') {
			model.marshal().save("map.json");
			System.out.println("Map Saved...");
		}
		if (c == 'l' || c == 'L') {
			Json obj =  Json.load("map.json");
			model.unmarshal(obj);
			System.out.println("Loading Map...");
		}
		if (c == 'e' || c == 'E') {
			canEdit = !canEdit;
			System.out.println("Edit Mode: " + canEdit);
		}
		if (c == 'c' || c == 'C') {
			coinMode = !coinMode;
			System.out.println("coinMode: " + coinMode);
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
		model.mario.previousX = model.mario.x;
        model.mario.previousY = model.mario.y;
		if (keyRight) {
			model.mario.x += 5;
			if ((model.mario.previousX != model.mario.x)) {
				backgroundPos -= 1;
			}
			model.mario.cycleImages();
		}
		if (keyLeft) {
			model.mario.x -= 5;
			if ((model.mario.previousX != model.mario.x)) {
				backgroundPos += 1;
			}
			model.mario.cycleImages();
		}
		if (keySpace) {
			model.mario.jump();
		}
	}
}
