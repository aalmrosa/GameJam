package com.game.jam.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class SecondScreen implements Screen {
    final Demo game;

    Player playerCharacter;

    Texture doorAreaHighlight;
    Texture wallHighlight;
    Texture doorHighlight;

    Rectangle doorArea;

    Array<Rectangle> obstacles;

    Array<Rectangle> walls;
    Array<Rectangle> doors;

    OrthographicCamera camera;

    public SecondScreen(Demo game, Player playerCharacter) {
        this.game = game;

        wallHighlight = new Texture(Gdx.files.internal("wall-highlight.png"));
        walls = new Array<Rectangle>();
        setWalls();

        doorHighlight = new Texture(Gdx.files.internal("door-highlight.png"));
        doors = new Array<Rectangle>();
        setDoors();

        doorAreaHighlight = new Texture(Gdx.files.internal("door-area-highlight.png"));
        doorArea  = new Rectangle(doors.get(0).x, doors.get(0).y+18, doors.get(0).width, doors.get(0).height+8);

        this.playerCharacter = playerCharacter;
        playerCharacter.setPlayerHitbox(new Rectangle(doors.get(0).x+18, doors.get(0).y+32, 32, 32));

        obstacles = new Array<Rectangle>();
        obstacles.addAll(walls);
        obstacles.addAll(doors);

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
        game.batch.draw(playerCharacter.getPlayerTexture(), playerCharacter.getPlayerHitbox().x, playerCharacter.getPlayerHitbox().y);
        for(Rectangle wall : walls) {
            game.batch.draw(wallHighlight, wall.x, wall.y, wall.width, wall.height);
        }
        for(Rectangle door : doors) {
            game.batch.draw(doorHighlight, door.x, door.y, door.width, door.height);
        }
        game.batch.draw(doorAreaHighlight, doorArea.x, doorArea.y, doorArea.width, doorArea.height);
        game.batch.end();

        playerCharacter.checkMovement(obstacles);
        if(Gdx.input.isKeyJustPressed(Input.Keys.E) && playerCharacter.getPlayerHitbox().overlaps(doorArea)){
            game.setScreen(new FirstScreen(game, playerCharacter));
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
        doorHighlight.dispose();
        wallHighlight.dispose();
    }

    private void setWalls(){
        walls.add(new Rectangle(0, 0, 320-(32*4), 32));//bottom 1
        walls.add(new Rectangle(320-(32*2), 0, (32*2), 32));//bottom 2
        walls.add(new Rectangle(0, 32, 32, 320-(32*2)));//left
        walls.add(new Rectangle(320-32, 32, 32, 320-(32*2)));//right
        walls.add(new Rectangle(0, 320-32, 320, 32));//up

        walls.add(new Rectangle(180, 180, 32, 32));//rogue
    }
    private void setDoors(){

        doors.add(new Rectangle(320-(32*4), 0, 64, 32-8));
    }
}
