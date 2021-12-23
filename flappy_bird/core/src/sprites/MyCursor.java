package sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.VioletBird;

public class MyCursor {
    private Texture cursorTexture;
    private Vector3 position;
    private int numberOfLevel;

    public MyCursor(){
        cursorTexture = new Texture("cursor.png");
        position = new Vector3((VioletBird.WIDTH/2) - 125 - 10 - cursorTexture.getWidth(),485,0);
        numberOfLevel = 0;
    }

    public void update(float dt){
        if (position.x > (VioletBird.WIDTH/2) - 125 - 10 - cursorTexture.getWidth())
        {
            position.x = (VioletBird.WIDTH/2) - 125 - 20 - cursorTexture.getWidth();
        }
        if (position.x < (VioletBird.WIDTH/2) - 125 - 20 - cursorTexture.getWidth())
        {
            position.x = (VioletBird.WIDTH/2) - 125 - 10 - cursorTexture.getWidth();
        }
        position.x += 0.25f;
    }

    public Vector3 getPosition(){
        return position;
    }

    public void handleInput(){
        if (Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
            if (position.y <= 85)
            {
                position.y = 485;
                numberOfLevel = 0;
            }
            else {
                position.y -= 100;
                numberOfLevel++;
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)){
            if(position.y >= 485){
                position.y = 85;
                numberOfLevel = 4;
            }
            else {
                position.y += 100;
                numberOfLevel--;
            }
        }
    }

    public Texture getCursorTexture() {
        return cursorTexture;
    }

    public int getNumberOfLevel() {
        return numberOfLevel;
    }

    public void dispose(){
        cursorTexture.dispose();
    }
}
