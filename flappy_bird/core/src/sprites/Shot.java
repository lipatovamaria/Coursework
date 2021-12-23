package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Shot {

    public static final int MOVEMENT = 100;
    private Texture texture;
    private Vector2 position;
    private Rectangle shotRec;

    public Shot(float x, float y){
        texture = new Texture("plumage.png");
        position = new Vector2(x + 10,y + 10);
        shotRec = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    public void update(float dt){
        position.x += MOVEMENT * dt * 2;
        shotRec.set(position.x, position.y, texture.getWidth(), texture.getHeight());
    }

    public Texture getTexture(){return texture;}

    public Rectangle getShotRec(){return shotRec;}

    public Vector2 getPosition(){return position;}

    public void dispose(){
        texture.dispose();
    }
}
