/*
Michael Darden
10/21/2021
Homework 5
*/

import java.util.ArrayList;

class Model
{
	ArrayList<Sprite> sprites;
	Brick b;
	Mario mario;

	Model()
	{
		sprites = new ArrayList<Sprite>();
		mario = new Mario(this);
		sprites.add(mario);
		Json obj =  Json.load("map.json");
		unmarshal(obj);
		System.out.println("Loading Map...");
	}

	public void setPosition(int x, int y)
	{
		b = new Brick(x, y, this);
		sprites.add(b);
	}

	public void setDimensions(int x, int y, boolean coinMode) {
		b.setDimensions(x, y, coinMode);
	}
	
	public void update()
	{
		for (int i = 0; i < sprites.size(); i++){
			Sprite s = sprites.get(i);
			s.update();
		}
		checkCollision();
	}

	void checkCollision() {
		for(int i = 0; i < sprites.size(); i++) {
			Sprite s = sprites.get(i);
			if (s.isMario()){
				mario = (Mario)s;
			}
			if (!s.isMario() && mario != null){
				if (mario.checkCollision(s)) {
					mario.fixCollision(s);
					mario.isColliding = true;
				}
			}
		}
	}

    // Marshals this object into a JSON DOM
    Json marshal()
    {
        Json ob = Json.newObject();
        Json tmpList = Json.newList();
        ob.add("bricks", tmpList);
        for(int i = 0; i < sprites.size(); i++){
			Sprite s = sprites.get(i);
			if (s.isBrick()){
				tmpList.add(s.marshal());
			}
		}
        return ob;
    }

	void unmarshal(Json ob)
	{
		//bricks = new ArrayList<Brick>();
		sprites = new ArrayList<Sprite>();
		mario = new Mario(this);
		sprites.add(mario);
		Json tmpList = ob.get("bricks");
		for (int i = 0; i < tmpList.size(); i++)
		{
			sprites.add(new Brick(tmpList.get(i), this));
		}
	}
}