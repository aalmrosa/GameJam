package com.game.jam.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class FirstScreen implements Screen {
    final Demo game;

    Player playerCharacter;

    Texture wallHighlight;
    Texture doorHighlight;

    Array<Rectangle> walls;
    Array<Rectangle> doors;

    OrthographicCamera camera;

    public FirstScreen(Demo game) {
        this.game = game;

        //set scene
        wallHighlight = new Texture(Gdx.files.internal("wall-highlight.png"));
        walls = new Array<Rectangle>();
        setWalls();

        doorHighlight = new Texture(Gdx.files.internal("door-highlight.png"));
        doors = new Array<Rectangle>();
        setDoors();

        //set player
        playerCharacter = new Player();
        playerCharacter.setPlayerTexture(new Texture(Gdx.files.internal("player-highlight.png")));
        playerCharacter.setPlayerHitbox(new Rectangle(32,32,32,32));

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 320, 320);
    }

    @Override
    public void show() {
        // Prepare your screen here.
    }

    @Override
    public void render(float delta) {
        // Draw your screen here. "delta" is the time since last render in seconds.
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);

        game.batch.begin();
        game.batch.draw(playerCharacter.getPlayerTexture(), playerCharacter.getPlayerHitbox().x, playerCharacter.getPlayerHitbox().y);
        for(Rectangle wall : walls){
            game.batch.draw(wallHighlight, wall.x, wall.y, wall.width, wall.height);
        }
        for(Rectangle door : doors){
            game.batch.draw(doorHighlight, door.x, door.y, door.width, door.height);
        }
        game.batch.end();

        //movement keys
        if(Gdx.input.isKeyPressed(playerCharacter.moveUp)){
            playerCharacter.move(0, 180, walls);
        }
        if(Gdx.input.isKeyPressed(playerCharacter.moveDown)){
            playerCharacter.move(0, -180, walls);
        }
        if(Gdx.input.isKeyPressed(playerCharacter.moveLeft)){
            playerCharacter.move(-180, 0, walls);
        }
        if(Gdx.input.isKeyPressed(playerCharacter.moveRight)){
            playerCharacter.move(180, 0, walls);
        }
    }

    @Override
    public void resize(int width, int height) {
        // Resize your screen here. The parameters represent the new window size.
    }

    @Override
    public void pause() {
        // Invoked when your application is paused.
    }

    @Override
    public void resume() {
        // Invoked when your application is resumed after pause.
    }

    @Override
    public void hide() {
        // This method is called when another screen replaces this one.
    }

    @Override
    public void dispose() {
        // Destroy screen's assets here.
        doorHighlight.dispose();
        wallHighlight.dispose();
    }

    private void setWalls(){
        walls.add(new Rectangle(0, 0, 320, 32)); //sets bottom wall
        walls.add(new Rectangle(0, 32, 32, 320 - (32*2))); //sets left wall
        walls.add(new Rectangle(320 - 32, 32, 32, 320 - (32*2))); //sets right wall
        walls.add(new Rectangle(0, 320 - 32, 320 - (32*3), 32)); //sets top wall 1
        walls.add(new Rectangle(320 - (32*2), 320 - 32, (32*2), 32)); //sets top wall 2

        walls.add(new Rectangle(180, 180, 32, 32));
    }
    private void setDoors(){

        doors.add(new Rectangle(320 - (32*3), 320 - 32 + 8, 32, 32 - 8));
    }
}
