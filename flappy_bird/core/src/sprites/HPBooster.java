package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import states.LevelState;

import java.util.Random;

public class HPBooster {

    private Rectangle hpBoosterRect;
    private Vector2 posHPBooster;
    private Texture texture;
    private Random random = new Random();

    public HPBooster(){
        texture = new Texture("health.png");
        generateXY();
        while ((((posHPBooster.x-300)%(LevelState.TUBE_SPACING + 50)>=0)&&((posHPBooster.x-300)%(LevelState.TUBE_SPACING + 50)<55))||
                (((posHPBooster.x-300+texture.getWidth())%(LevelState.TUBE_SPACING + 50)>=0)&&((posHPBooster.x-300+texture.getWidth())%(LevelState.TUBE_SPACING + 50)<55))){
            generateXY();
        }
        hpBoosterRect = new Rectangle(posHPBooster.x + texture.getWidth()/2, posHPBooster.y, texture.getWidth(),texture.getHeight());
    }

    private Vector2 generateXY(){
        int x = random.nextInt(3000) + 500;
        int y = random.nextInt(250) + 50;
        posHPBooster = new Vector2(x,y);
        return posHPBooster;
    }

    public Rectangle getHpBoosterRec(){return hpBoosterRect;}

    public Vector2 getPosHPBooster(){return posHPBooster;}

    public Texture getTexture(){return texture;}
}
