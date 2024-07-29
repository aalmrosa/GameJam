package com.game.jam.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;

public class MainMenuScreen implements Screen {
    final Demo game;

    OrthographicCamera camera;

    public MainMenuScreen(Demo game) {
        this.game = game;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 320);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);

        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.font.draw(game.batch, "Welcome to Demo", 32, 320-32);
        game.font.draw(game.batch, "Tap anywhere to begin", 32, 320-(32*2));
        game.batch.end();

        if(Gdx.input.isTouched()){
            game.setScreen(
                new FirstScreen(game, new Player(
                    new Rectangle(32, 32, 32, 32),
                    new Texture(Gdx.files.internal("player-highlight.png"))
                )));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
