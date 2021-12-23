package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.VioletBird;
import sprites.HPBooster;
import sprites.PlayerBird;
import sprites.Tube;

public abstract class LevelState extends State{

    public static final int TUBE_SPACING = 170;
    public static final int TUBE_COUNT = 4;

    protected PlayerBird bird;
    protected Texture background;
    protected Vector2 backPos1, backPos2;
    protected Texture health;
    protected Array<Vector2> healthPos;
    protected Array<Tube> tubes;
    protected int scorePoint;
    protected BitmapFont score;
    protected Rectangle betweenTubes;
    protected Array<HPBooster> hpBooster;
    protected boolean levelCompleted = false;
    public static final Music lossHP = Gdx.audio.newMusic(Gdx.files.internal("lossHP.mp3"));;
    public static final Music plusHP = Gdx.audio.newMusic(Gdx.files.internal("plusHP.mp3"));;

    public LevelState(GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public abstract void update(float dt);

    @Override
    public abstract void render(SpriteBatch batch);

    @Override
    public void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
            bird.jump();

        if(Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)){
            bird.shot();
        }

    }

    @Override
    public void dispose() {
        background.dispose();
        bird.dispose();
        health.dispose();
        lossHP.dispose();
        plusHP.dispose();
        for (Tube tube : tubes){
            tube.dispose();
        }
        for (int i = 0; i < bird.getShots().size; i++){
            bird.getShots().get(i).dispose();
        }
    }

    public void updateBack(){
        if (camera.position.x-camera.viewportWidth/2>backPos1.x+ VioletBird.WIDTH){
            backPos1.add(VioletBird.WIDTH*2,0);
        }
        if (camera.position.x-camera.viewportWidth/2>backPos2.x+ VioletBird.WIDTH){
            backPos2.add(VioletBird.WIDTH*2,0);
        }
    }
}
