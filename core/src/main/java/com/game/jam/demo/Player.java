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

    //action keys
    public int moveUp = Input.Keys.UP;
    public int moveDown = Input.Keys.DOWN;
    public int moveLeft = Input.Keys.LEFT;
    public int moveRight = Input.Keys.RIGHT;

    //movement
    public void move(int velocityX, int velocityY, Array<Rectangle> walls){
         playerHitbox.x += velocityX * Gdx.graphics.getDeltaTime();
         playerHitbox.y += velocityY * Gdx.graphics.getDeltaTime();

         for(Rectangle wall : walls){
             if(playerHitbox.overlaps(wall)){
                 playerHitbox.x -= velocityX * Gdx.graphics.getDeltaTime();
                 playerHitbox.y -= velocityY * Gdx.graphics.getDeltaTime();
             }
         }
    }

//    public void checkCollision(Array<Rectangle> walls){
//        for(Rectangle wall : walls){
//            if(playerHitbox.overlaps(wall)){
//                if(playerHitbox.x + playerHitbox.width > wall.x){
//                    playerHitbox.x = wall.x - playerHitbox.width;
//                }
//                if(playerHitbox.x < wall.x + wall.width){
//                    playerHitbox.x = wall.x + wall.width;
//                }
//                if(playerHitbox.y + playerHitbox.height > wall.y ){
//                    playerHitbox.y = wall.y - playerHitbox.height;
//                }
//                if(playerHitbox.y < wall.y + wall.height){
//                    playerHitbox.y = wall.y + wall.height;
//                }
//            }
//        }
//    }
}
