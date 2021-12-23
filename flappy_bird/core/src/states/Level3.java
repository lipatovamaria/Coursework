package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.VioletBird;
import sprites.EnemyBird;
import sprites.HPBooster;
import sprites.PlayerBird;
import sprites.Tube;

import java.util.Random;

public class Level3 extends LevelState{

    private Array<EnemyBird> enemies;
    private Music kill;

    public Level3(GameStateManager gsm) {
        super(gsm);

        bird = new PlayerBird(100,250, 0);
        background = new Texture("back.png");
        health = new Texture("health.png");
        kill = Gdx.audio.newMusic(Gdx.files.internal("kill.mp3"));
        kill.setVolume(0.5f);
        lossHP.setVolume(0.3f);
        plusHP.setVolume(0.3f);

        camera.setToOrtho(false, (float) (VioletBird.WIDTH/1.5), (float) (VioletBird.HEIGHT/1.5));
        backPos1 = new Vector2((float) (camera.position.x - camera.viewportWidth/1.5),0);
        backPos2 = new Vector2((float) ((camera.position.x - camera.viewportWidth/1.5) + VioletBird.WIDTH),0);

        healthPos = new Array<Vector2>();
        for (int i = 0; i < bird.hp; i++){
            healthPos.add(new Vector2((float) (camera.position.x - camera.viewportWidth/1.5) + health.getWidth() + 20,370));
        }

        tubes = new Array<Tube>();
        for (int i = 0; i < TUBE_COUNT; i++){
            tubes.add(new Tube(i*(TUBE_SPACING + Tube.TUBE_WIDTH)));
        }

        Random random = new Random();
        int countOfHPBoost = random.nextInt(3) + 1;
        hpBooster = new Array<HPBooster>();
        for(int i = 0; i < countOfHPBoost; i++)
        {
            hpBooster.add(new HPBooster());
        }

        enemies = new Array<EnemyBird>();
        int countOfEnemies = random.nextInt(6) + 3;
        for(int i = 0; i < countOfEnemies; i++){
            int x = random.nextInt(3000) + 500;
            int y = random.nextInt(150) + 60;
            enemies.add(new EnemyBird(x,y,10));
        }

        final String FONT_PATH = "font.ttf";
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(FONT_PATH));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 20;
        parameter.color = Color.WHITE;
        score = generator.generateFont(parameter);
        scorePoint = 0;
    }

    @Override
    public void update(float dt) {
        handleInput();
        updateBack();
        bird.update(dt);
        for(EnemyBird enemy: enemies){
            enemy.update(dt);
        }

        if (bird.isShot()) {
            for (int i = 0; i < bird.getShots().size; i++) {
                bird.getShots().get(i).update(dt);
            }
        }

        camera.position.x = bird.getPosition().x + 100;
        healthPos.get(0).x = (float) (camera.position.x - camera.viewportWidth/1.5) + 100;
        healthPos.get(1).x = (float) (camera.position.x - camera.viewportWidth/1.5) + 150;
        healthPos.get(2).x = (float) (camera.position.x - camera.viewportWidth/1.5) + 200;

        for(int i = 0; i < tubes.size; i++){
            Tube tube = tubes.get(i);
            tube.update(dt);

            for(int j = 0; j < enemies.size; j++){
                EnemyBird enemy = enemies.get(j);
                enemy.update(dt);
            }

            if(camera.position.x - (camera.viewportWidth/2)>tube.getPosTopTube().x
                    + tube.getTopTube().getWidth()){
                tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING)*TUBE_COUNT));
            }

            if (tube.collides(bird.getBounds())){
                bird.hp -= 1;
                lossHP.play();
                bird.getPosition().x = tube.getPosBotTube().x + tube.getBottomTube().getWidth() + 10;
            }

            if (bird.hp == 0){
                gsm.set(new MenuState(gsm));
            }

            if (scorePoint >= 20){
                levelCompleted = true;
            }

            betweenTubes = new Rectangle(tube.getPosBotTube().x + tube.getTopTube().getWidth(), tube.getPosTopTube().y-Tube.TUBE_GAP,1,130);
            if(bird.scoring.overlaps(betweenTubes)){
                scorePoint++;
            }
        }

        if(levelCompleted){
            gsm.set(new VictoryState(gsm, "back.png"));
        }
        for(int i = 0; i < hpBooster.size; i++){
            if((hpBooster.get(i).getHpBoosterRec().overlaps(bird.bounds))&&(bird.hp < 3)){
                hpBooster.removeIndex(i);
                bird.hp++;
                plusHP.play();
            }
        }

        for(EnemyBird enemy: enemies){
            if(enemy.bounds.overlaps(bird.bounds)){
                enemies.removeValue(enemy, true);
                lossHP.play();
                bird.hp--;
            }
            for(int i = 0; i < bird.getShots().size; i++){
                if ((bird.isShot())&&(enemy.bounds.overlaps(bird.getShots().get(i).getShotRec()))){
                    enemies.removeValue(enemy, true);
                    bird.getShots().removeIndex(i);
                    kill.play();
                }
            }
        }
        camera.update();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(background, backPos1.x, backPos1.y, VioletBird.WIDTH, VioletBird.HEIGHT);
        batch.draw(background, backPos2.x, backPos2.y, VioletBird.WIDTH, VioletBird.HEIGHT);

        batch.draw(bird.getBird(),bird.getPosition().x, bird.getPosition().y);
        for (Tube tube : tubes){
            batch.draw(tube.getTopTube(),tube.getPosTopTube().x,tube.getPosTopTube().y);
            batch.draw(tube.getBottomTube(),tube.getPosBotTube().x,tube.getPosBotTube().y);
        }

        for (int i = 0; i<bird.hp;i++){
            batch.draw(health,healthPos.get(i).x, healthPos.get(i).y);
        }

        for(int i = 0; i < hpBooster.size; i++){
            batch.draw(hpBooster.get(i).getTexture(), hpBooster.get(i).getPosHPBooster().x,hpBooster.get(i).getPosHPBooster().y);
        }

        for(int i = 0; i < enemies.size; i++){
            batch.draw(enemies.get(i).getBird(), enemies.get(i).position.x, enemies.get(i).position.y);
        }

        if (bird.isShot()){
            for(int i = 0; i < bird.getShots().size; i++){
                batch.draw(bird.getShots().get(i).getTexture(), bird.getShots().get(i).getPosition().x, bird.getShots().get(i).getPosition().y);
            }
        }

        CharSequence str = Integer.toString(scorePoint);
        score.draw(batch, str, camera.position.x, 390);
        batch.end();
    }
}
