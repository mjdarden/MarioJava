/*
Michael Darden
10/21/2021
Homework 5
*/

import java.awt.Graphics;
//import java.awt.image.BufferedImage;

abstract class Sprite {
    int x, y, w, h;
    Model model;
    boolean isCoinbock;
    int hasCoin = 0;

    abstract void update();
    abstract void draw(Graphics g);
    abstract Json marshal();

    boolean isMario() {
        return false;
    }
    
    boolean isBrick() {
        return false;
    }
    
    boolean isCoin() {
        return false;
    }

    public boolean checkCollision(Sprite s) {
        if (this.x + this.w <= s.x) {
            return false;
        }
        if (this.x >= s.x + s.w) {
            return false;
        }
        if (this.y + this.h <= s.y) {
            return false;
        }
        if (this.y >= s.y + s.h) {
            return false;
        }
        else {
            return true;
        }
    }

    void throwCoin() {

    }
}
