import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Coin extends Sprite{
    double vert_vel, horiz_vel;
    static BufferedImage coinImage = null;
    int index;

    Coin(int x, int y, Model m){
        Random rand = new Random();
        int randXvel = rand.nextInt(10);
        if (randXvel > 5) {
            randXvel -= 5;
            randXvel = randXvel * (-1);
        }
        this.x = x - 30;
        this.y = y - 60;
        this.w = 60;
        this.h = 60;
        vert_vel = -20;
        horiz_vel = randXvel;
        model = m;
        loadImage();
    }

    void loadImage() {
        if (coinImage == null){
            coinImage = View.loadImage("coin.png");
        }
    }

    void update() {
        x += horiz_vel;
		vert_vel += 1.2;
		y += vert_vel;
        if ( y > 750){
            model.sprites.remove(this);
        }
    }

    void draw(Graphics g) {
        g.drawImage(coinImage, x - model.mario.x + model.mario.marioScreenLocation, y, w, h, null);
    }

    @Override
    public String toString() {
        return "Coin is located at (" + x + ", " + y + ") with a width of " + w + 
        "px and a height of " + h + "px.";
    }
    
    @Override
    boolean isCoin() {
        return true;
    }

    Json marshal() {
        return null;
    }
    
}
