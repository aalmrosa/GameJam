package com.game.jam.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class Player {
    private Texture playerTexture;
    private Rectangle playerHitbox;
    private boolean alreadyLoaded;

    //action keys
    public int moveUp = Input.Keys.UP;
    public int moveDown = Input.Keys.DOWN;
    public int moveLeft = Input.Keys.LEFT;
    public int moveRight = Input.Keys.RIGHT;

    public Player(Rectangle playerHitbox, Texture playerTexture){
        this.playerHitbox = playerHitbox;
        this.playerTexture = playerTexture;
        this.alreadyLoaded = false;
    }
    //movement
    public void move(int velocityX, int velocityY, Array<Rectangle> obstacles){
         playerHitbox.x += velocityX * Gdx.graphics.getDeltaTime();
         playerHitbox.y += velocityY * Gdx.graphics.getDeltaTime();

         for(Rectangle wall : obstacles){
             if(playerHitbox.overlaps(wall)){
                 playerHitbox.x -= velocityX * Gdx.graphics.getDeltaTime();
                 playerHitbox.y -= velocityY * Gdx.graphics.getDeltaTime();
             }
         }
    }

    public void checkMovement(Array<Rectangle> obstacles){
        if(Gdx.input.isKeyPressed(moveUp)){
            move(0, 180, obstacles);
        }
        if(Gdx.input.isKeyPressed(moveDown)){
            move(0, -180, obstacles);
        }
        if(Gdx.input.isKeyPressed(moveLeft)){
            move(-180, 0, obstacles);
        }
        if(Gdx.input.isKeyPressed(moveRight)){
            move(180, 0, obstacles);
        }
    }
}
