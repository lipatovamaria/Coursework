package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class EnemyBird extends AbstractBird{

    public EnemyBird(int x, int y, float velositiX){
        position = new Vector3(x,y,0);
        this.velocityX = velositiX;
        velocity = new Vector3(velositiX,0,0);
        texture = new Texture("enemy.png");
        birdAnimation = new Animation(new TextureRegion(texture),3, 0.5f);
        bounds = new Rectangle(x,y, texture.getWidth()/3, texture.getHeight());
    }

    public void update(float dt){
        birdAnimation.update(dt);
        position.x -= MOVEMENT * dt/7;
        bounds.setPosition(position.x, position.y);
    }
}
