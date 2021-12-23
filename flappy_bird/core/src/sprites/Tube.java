package sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Tube {

    public static final int FLUCTUATION = 110;
    public static final int TUBE_GAP = 130;
    public static final int LOWEST_OPENING = 80;
    public static final int TUBE_WIDTH = 50;
    private Texture topTube, bottomTube;
    private Vector2 posTopTube, posBotTube;
    private Random rand;
    private Rectangle boundsTop, boundsBot;

    public Tube(float x){
        topTube = bottomTube = new Texture("wall.png");
        rand = new Random();

        posTopTube = new Vector2(x + 300, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube = new Vector2(x + 300, posTopTube.y - TUBE_GAP - bottomTube.getHeight());

        boundsTop = new Rectangle(posTopTube.x, posTopTube.y, topTube.getWidth(), topTube.getHeight());
        boundsBot = new Rectangle(posBotTube.x, posBotTube.y, bottomTube.getWidth(), bottomTube.getHeight());
    }

    public void setPosBotTube(float newPosBotTube){
        posBotTube.y = newPosBotTube;
    }

    public void setPosTopTube(float newPosTopTube){
        posTopTube.y = newPosTopTube;
    }

    public Texture getBottomTube() {
        return bottomTube;
    }

    public Texture getTopTube() {
        return topTube;
    }

    public Vector2 getPosBotTube() {
        return posBotTube;
    }

    public Vector2 getPosTopTube() {
        return posTopTube;
    }

    public void reposition(float x){
        posTopTube.set(x, rand.nextInt(FLUCTUATION) + TUBE_GAP + LOWEST_OPENING);
        posBotTube.set(x, posTopTube.y - TUBE_GAP - bottomTube.getHeight());
    }

    public void update(float dt){
        boundsBot.setPosition(posBotTube.x, posBotTube.y);
        boundsTop.setPosition(posTopTube.x, posTopTube.y);
    }

    public boolean collides(Rectangle player){
        return player.overlaps(boundsTop) || player.overlaps(boundsBot);
    }

    public void dispose() {
        topTube.dispose();
        bottomTube.dispose();
    }
}
