/*
Michael Darden
10/21/2021
Homework 5
*/

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Brick extends Sprite {
    BufferedImage brickImage = null;
    BufferedImage coinBrickImage = null;
    int startX, startY;
    int numberOfCoins = 5;

    Brick(int xPos, int yPos, int width, int height, Model m) {
        x = xPos;
        y = yPos;
        w = width;
        h = height;
        model = m;
        loadImage();
    }

    Brick(int xPos, int yPos, Model m) {
        x = xPos;
        y = yPos;
        w = 10;
        h = 10;
        model = m;
        loadImage();
    }
	
	public void setDimensions(int mouseX, int mouseY, boolean coinMode) {
        if (coinMode) {
            isCoinbock = true;
        }
        int X = x;
        int Y = y;
        int width = 10;
        int height = 10;
		if (mouseX > startX) {
			width = mouseX - startX;
			X = startX;
		} else if (mouseX < startX){
			width = startX - mouseX;
			X = mouseX;
		}
		if (mouseY > startY) {
			height = mouseY - startY;
			Y = startY;
		} else if (mouseY < startY){
			height = startY - mouseY;
			Y = mouseY;
		}
		x = X + model.mario.x - model.mario.marioScreenLocation;
		y = Y;
		w = width;
		h = height;
	}

    // Unmarshaling constructor
    Brick(Json ob, Model m)
    {
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = (int)ob.getLong("w");
        h = (int)ob.getLong("h");
        isCoinbock = (Boolean)ob.getBool("isCoinblock");
        model = m;
        loadImage();
    }

    void loadImage() {
        if (brickImage == null){
            brickImage = View.loadImage("brick.png");
        }
        if (coinBrickImage == null){
            coinBrickImage = View.loadImage("coinBrick.png");
        }
    }

    void draw(Graphics g) {
        if (this.isCoinbock){
            g.drawImage(coinBrickImage, x - model.mario.x + model.mario.marioScreenLocation, y, w, h, null);
        } else if (!this.isCoinbock){
            g.drawImage(brickImage, x - model.mario.x + model.mario.marioScreenLocation, y, w, h, null);
        }
    }

    @Override
    void throwCoin(){
        hasCoin = 0;
        for (int i = 0; i < model.sprites.size(); i++){
            Sprite s = model.sprites.get(i);
            if (s.isCoin()){
                hasCoin++;
            }
        }
        if (this.isCoinbock && this.numberOfCoins > 0) {
            if (hasCoin < 3) {
                Coin c = new Coin(this.x + (this.w / 2), this.y, model);
                model.sprites.add(c);
                //c.index = model.sprites.indexOf(c);
                this.numberOfCoins--;
            }
        }
        if (this.numberOfCoins <= 0){
            this.isCoinbock = false;
        }
    }

    void update() {     }
    
    @Override
    public String toString() {
        return "Brick is located at (" + x + ", " + y + ") with a width of " + w + 
        "px and a height of " + h + "px.";
    }
    @Override
    boolean isBrick() {
        return true;
    }

    // Marshals this object into a JSON DOM
    Json marshal()
    {
        Json ob = Json.newObject();
        ob.add("x", x);
        ob.add("y", y);
        ob.add("w", w);
        ob.add("h", h);
        ob.add("isCoinblock", isCoinbock);
        return ob;
    }
}
