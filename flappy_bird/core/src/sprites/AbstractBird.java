package sprites;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public abstract class AbstractBird {
    public static final int MOVEMENT = 100;
    public static final int GRAVITY = -15;
    public Vector3 position;
    public Vector3 velocity;
    public Rectangle bounds;
    public Animation birdAnimation;
    public Texture texture;
    public Music flap;
    public Rectangle scoring;
    public float velocityX;

    public void update(float dt){
        birdAnimation.update(dt);
        if (position.y>0)
            velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt + velocity.x, velocity.y,0);
        if (position.y < 0)
            position.y = 0;
        if (position.y>=600-texture.getHeight()-200){
            position.y = 600 - texture.getHeight()-200;
        }
        velocity.scl(1/dt);
        bounds.setPosition(position.x, position.y);
        scoring.setPosition(position.x, position.y);
    }

    public Vector3 getPosition(){
        return position;
    }

    public TextureRegion getBird(){
        return birdAnimation.getFrame();
    }

    public void jump(){
        velocity.y = 200;
        flap.play();
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
        flap.dispose();
    }
}
