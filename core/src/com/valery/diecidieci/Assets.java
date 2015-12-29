package com.valery.diecidieci;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Created by Ciro on 26/12/2015.
 */
public class Assets {

    public static Texture[] blocksColors;
    public static BitmapFont font;

	public static Texture retryButton;

    public static void init(){
    	blocksColors = new Texture[10];
   	    blocksColors[0] = new Texture(Gdx.files.internal("data/grey.png"));
    	blocksColors[1] = new Texture(Gdx.files.internal("data/blue.png"));
    	blocksColors[2] = new Texture(Gdx.files.internal("data/d_green.png"));
    	blocksColors[3] = new Texture(Gdx.files.internal("data/l_green.png"));
    	blocksColors[4] = new Texture(Gdx.files.internal("data/orange.png"));
    	blocksColors[5] = new Texture(Gdx.files.internal("data/pink.png"));
    	blocksColors[6] = new Texture(Gdx.files.internal("data/purple.png"));
    	blocksColors[7] = new Texture(Gdx.files.internal("data/red.png"));
    	blocksColors[8] = new Texture(Gdx.files.internal("data/teal.png"));
    	blocksColors[9] = new Texture(Gdx.files.internal("data/yellow.png"));
		if(Configs.FACES == true) {
			blocksColors[1] = new Texture(Gdx.files.internal("data/tony.png"));
			blocksColors[2] = new Texture(Gdx.files.internal("data/dad.png"));
			blocksColors[3] = new Texture(Gdx.files.internal("data/mum.png"));
			blocksColors[4] = new Texture(Gdx.files.internal("data/son1.png"));
			blocksColors[5] = new Texture(Gdx.files.internal("data/son2.png"));
			//blocksColors[5] = new Texture(Gdx.files.internal("data/susy.png"));
		}
		retryButton = new Texture(Gdx.files.internal("data/retryButton.png"));
    	font = new BitmapFont();
    	//font.getData().setScale(0.2f);
    }

    public static void dispose(){
        for(int x = 0; x < 10; x++){
        	blocksColors[x].dispose();
        }
		retryButton.dispose();
	}
}
