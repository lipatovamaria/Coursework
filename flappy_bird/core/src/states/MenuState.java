package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.VioletBird;
import sprites.MyCursor;

public class MenuState extends State {

    private final Texture background;
    private final Texture level1Btn;
    private final Texture level2Btn;
    private final Texture level3Btn;
    private final Texture level4Btn;
    private final Texture level5Btn;
    private final MyCursor cursor;

    private Array<LevelState> levels;



    public MenuState(GameStateManager gsm){
        super(gsm);

        background = new Texture("menu.png");
        level1Btn = new Texture("level1.png");
        level2Btn = new Texture("level2.png");
        level3Btn = new Texture("level3.png");
        level4Btn = new Texture("level4.png");
        level5Btn = new Texture("level5.png");

        levels = new Array<LevelState>();
        levels.add(new Level1(gsm));
        levels.add(new Level2(gsm));
        levels.add(new Level3(gsm));
        levels.add(new Level4(gsm));
        levels.add(new Level5(gsm));

        cursor = new MyCursor();
        camera.setToOrtho(false, (float) (VioletBird.WIDTH), (float) (VioletBird.HEIGHT));
    }
    @Override
    public void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE))
            gsm.set(levels.get(cursor.getNumberOfLevel()));
    }

    @Override
    public void update(float dt) {
        handleInput();
        cursor.handleInput();
        cursor.update(dt);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, 0, 0, VioletBird.WIDTH, VioletBird.HEIGHT);
        batch.draw(level1Btn, (VioletBird.WIDTH/2) - 125, 460, 250, 80);
        batch.draw(level2Btn, (VioletBird.WIDTH/2) - 125, 360, 250, 80);
        batch.draw(level3Btn, (VioletBird.WIDTH/2) - 125, 260, 250, 80);
        batch.draw(level4Btn, (VioletBird.WIDTH/2) - 125, 160, 250, 80);
        batch.draw(level5Btn, (VioletBird.WIDTH/2) - 125, 60, 250, 80);
        batch.draw(cursor.getCursorTexture(), cursor.getPosition().x, cursor.getPosition().y);
        batch.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        level1Btn.dispose();
        level2Btn.dispose();
        level3Btn.dispose();
        level4Btn.dispose();
        level5Btn.dispose();
        cursor.dispose();
    }
}
