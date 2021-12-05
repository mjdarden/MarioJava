/*
Michael Darden
10/21/2021
Homework 5
*/

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Mario extends Sprite {
    int previousX;
    int previousY;
    double vert_vel;
	static BufferedImage[] mario_images = null;
	int marioNumber;
    int marioScreenLocation;
    boolean isColliding;
    boolean head = false;
    boolean foot = false;
    boolean left = false;
    boolean right = false;
    int numberOfFrames = 0;

    Mario(Model m) {
        marioScreenLocation = 150;
        x = 0;
        y = 100;
        w = 60;
        h = 95;
        vert_vel = 0;
		marioNumber = 0;
        model = m;
        if (mario_images == null) 
        {
            mario_images = new BufferedImage[5];
            mario_images[0] = View.loadImage("mario1.png");
            mario_images[1] = View.loadImage("mario2.png");
            mario_images[2] = View.loadImage("mario3.png");
            mario_images[3] = View.loadImage("mario4.png");
            mario_images[4] = View.loadImage("mario5.png");
        }
    }

    void update()
	{
		vert_vel += 1.2;
		y += vert_vel;
        if (y + h > 500)
		{
			vert_vel = 0.0;
			y = 500 - h; // snap back to the ground
            isColliding = true;
            foot = true;
		}
        if (isColliding && foot == true) {
            numberOfFrames = 0;
        } else {
            numberOfFrames++;
        }
        isColliding = false;
        head = false;
        foot = false;
        left = false;
        right = false;
	}

    public void fixCollision(Sprite b) {
        if (((x + w) >= b.x) && ((previousX + w) <= b.x)) {
            right = true;
			//vert_vel = 0.0;
            x = b.x - w;
        }
        if ((x <= (b.x + b.w)) && (previousX >= (b.x + b.w))) {
            left = true;
			//vert_vel = 0.0;
            x = b.x + b.w;
        }
        if((y + h) >= b.y && (previousY + h) <= b.y) {
            foot = true;
			vert_vel = 0.0;
            y = b.y - h;
        }
        if(y <= (b.y + b.h) && previousY >= (b.y + b.h)) {
            head = true;
            b.throwCoin();
			vert_vel = 0.0;
            y = b.y + b.h;
        }
    }

    void draw(Graphics g) {
        g.drawImage(mario_images[this.marioNumber], marioScreenLocation, y, null);
    }

    void cycleImages() {
        if (this.marioNumber == 4){
            this.marioNumber = 0;
        }else if (this.marioNumber < 4){
            this.marioNumber++;
        }
    }

    void jump() {
        if (isColliding && numberOfFrames == 0){
            vert_vel -= 10.1;
        } else if (numberOfFrames < 10) {
            vert_vel -= 3.1;
        }
    }

    Json marshal() { 
        Json ob = Json.newObject();
        return ob;
    }

    @Override
    public String toString() {
        return "Mario is located at (" + x + ", " + y + ") with a width of " + w + 
        "px and a height of " + h + "px.";
    }
    
    @Override
    boolean isMario() {
        return true;
    }
}
