package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class PlayerBird extends AbstractBird{

    private Array<Shot> shots;
    private boolean isShot;
    private Music shotSound;
    public int hp;

    public PlayerBird(int x, int y, float velocityX){
        position = new Vector3(x,y,0);
        this.velocityX = velocityX;
        velocity = new Vector3(velocityX,0,0);
        texture = new Texture("bird.png");
        birdAnimation = new Animation(new TextureRegion(texture),3, 0.5f);
        bounds = new Rectangle(x,y, texture.getWidth()/3, texture.getHeight());
        flap = Gdx.audio.newMusic(Gdx.files.internal("flap.mp3"));
        flap.setVolume(0.3f);
        shotSound = Gdx.audio.newMusic(Gdx.files.internal("shot.mp3"));
        shotSound.setVolume(0.3f);
        hp = 3;
        scoring = new Rectangle(position.x + texture.getWidth()/2, position.y + texture.getHeight()/2, 1,1);
        isShot = false;
        shots = new Array<Shot>();
    }

    public boolean isShot(){return isShot;}

    public Array<Shot> getShots(){return shots;}

    public void shot(){
        shotSound.play();
        isShot = true;
        shots.add(new Shot(position.x + bounds.width, position.y + bounds.height/2));
    }
}
