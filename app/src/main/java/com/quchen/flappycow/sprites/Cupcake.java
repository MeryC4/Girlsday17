package com.quchen.flappycow.sprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.quchen.flappycow.Game;
import com.quchen.flappycow.GameView;
import com.quchen.flappycow.R;
import com.quchen.flappycow.Util;

/**
 * Created by meryc on 27.04.17.
 */

public class Cupcake extends PlayableCharacter {

    public static Bitmap globalBitmap;

    private Rainbow rainbow;


    public Cupcake(GameView view, Game game) {
        super(view, game);
        if(globalBitmap == null){
            globalBitmap = Util.getScaledBitmapAlpha8(game, R.drawable.cupcake2);
        }
        this.bitmap = globalBitmap;
        this.width = this.bitmap.getWidth();
        this.height = this.bitmap.getHeight();
        this.y = game.getResources().getDisplayMetrics().heightPixels / 2;

        this.rainbow = new Rainbow(view, game);
    }

    // Bewegung
    public void move(){
        super.move();
        if(rainbow != null){
            manageRainbowMovement();
        }
    }

    private void manageRainbowMovement(){
        rainbow.y = this.y;        // nyan cat and rainbow bitmap have the same height
        rainbow.x = this.x - rainbow.width;
        rainbow.move();

        // manage frames of the rainbow
        if(speedY > getTabSpeed() / 3 && speedY < getMaxSpeed() * 1/3){
            rainbow.row = 0;
        }else if(speedY > 0){
            rainbow.row = 1;
        }else{
            rainbow.row = 2;
        }
    }

    /**
     * Draws itself via super.draw
     * and the rainbow.
     */
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if(rainbow != null && !isDead){
            rainbow.draw(canvas);
        }
    }

    /**
     * Calls super.dead,
     * removes the rainbow tail
     * and set the bitmapframe to a dead cat -.-
     */
    @Override
    public void dead() {
        super.dead();
        this.row = 1;

        // Maybe an explosion
    }

    @Override
    public void revive() {
        super.revive();
        manageRainbowMovement();
    }



}
