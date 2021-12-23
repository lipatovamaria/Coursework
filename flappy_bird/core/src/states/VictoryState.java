package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.VioletBird;

public class VictoryState extends State {

    private Texture background;
    private Texture victory;


    public VictoryState(GameStateManager gsm, String backPass){
        super(gsm);
        camera.setToOrtho(false, (float) (VioletBird.WIDTH), (float) (VioletBird.HEIGHT));
        background = new Texture(backPass);
        victory = new Texture("victory.png");
    }
    @Override
    public void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
            gsm.set(new MenuState(gsm));
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, VioletBird.WIDTH, VioletBird.HEIGHT);
        batch.draw(victory, (VioletBird.WIDTH/2) - (victory.getWidth()/2), VioletBird.HEIGHT/2 - victory.getHeight()/2);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        victory.dispose();
    }
}
