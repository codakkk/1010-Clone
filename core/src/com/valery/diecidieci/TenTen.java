package com.valery.diecidieci;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class TenTen extends ApplicationAdapter {

	SpriteBatch batch;
	ShapeRenderer sr;
	
	OrthographicCamera camera;

	Table table;

    public Shape[] shapes;
    public Shape currentShape;
    
    public int currentShapeID;
	
    public Vector3 touchPosition;
	
    public int points;

	GameState gameState;

	@Override
	public void create () {
		Assets.init();
		batch = new SpriteBatch();
		sr = new ShapeRenderer();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, Configs.GAME_WIDTH, Configs.GAME_HEIGHT);
		camera.position.set(Configs.GAME_WIDTH/2, Configs.GAME_HEIGHT/2, 0);
		//
		table = new Table(Configs.WIDTH_MARGIN, Configs.HEIGHT_MARGIN);
		fillShapes();
		gameState = GameState.PLAYING_STATE;
	}

	public void reset(){
		this.points = 0;
		this.currentShape = null;
		this.table.reset();
		gameState = GameState.PLAYING_STATE;
		fillShapes();
	}
	
	public void update(){
		updatePosition();

		if(gameState == GameState.PLAYING_STATE) {
			if(!this.canPlaceShapes()){
				gameState = GameState.GAMEOVER_STATE;
				return;
			}
			if (Gdx.input.justTouched()) {
				for (int x = 0; x < 3; x++) {
					if (shapes[x] == null) continue;
					if (shapes[x].isColliding(touchPosition)) {
						this.currentShape = shapes[x];
						this.currentShapeID = x;
						shapes[x].touch();
					}
				}
			}
			if (this.currentShape != null) {
				if (Gdx.input.isTouched()) {
					this.currentShape.x = (int) touchPosition.x - this.currentShape.getShapeWidth() / 2;
					this.currentShape.y = (int) touchPosition.y + this.currentShape.getShapeHeight() / 2;
				} else {
					if (table.place(this.currentShape)) {
						points += (this.currentShape.blocks.length - 1) + (table.clearLines());
						this.shapes[this.currentShapeID] = null;
						this.currentShape = null;
						if (!hasShapes()) {
							fillShapes();
						}
					} else {
						this.currentShape.release();
						this.currentShape.x = this.nextXcoordAt(this.currentShapeID);
						this.currentShape.y = this.nextYcoordAt(this.currentShapeID);
						this.currentShape = null;
					}
				}
			}

		} else if(gameState == GameState.GAMEOVER_STATE) {
			//RESET BUTTON
			if(Gdx.input.justTouched()) {
				if (touchPosition.x > Configs.RETRYBUTTON_POSITION_X && touchPosition.x < Configs.RETRYBUTTON_POSITION_X + 64) {
					if (touchPosition.y > Configs.RETRYBUTTON_POSITION_Y && touchPosition.y < Configs.RETRYBUTTON_POSITION_Y + 64) {
						this.reset();
						return;
					}
				}
			}
		}
	}
	
	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.update();
		update();

		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		table.render(batch);
		for(int x = 0; x < 3; x++){
			if(this.shapes[x] == null)continue;
			this.shapes[x].render(batch);
		}
		Assets.font.setColor(Color.BLACK);
		Assets.font.draw(batch, ""+points, Configs.GAME_WIDTH/2, Configs.GAME_HEIGHT - 40);
		Assets.font.setColor(Color.WHITE);
		batch.end();
		if(gameState == GameState.GAMEOVER_STATE) {
			sr.setProjectionMatrix(camera.combined);
			sr.begin(ShapeType.Filled);
			sr.setColor(Color.valueOf("ADD8E6"));
			sr.rect(0, (Configs.GAME_HEIGHT-Configs.HEIGHT_MARGIN)/2, Configs.GAME_WIDTH, Configs.HEIGHT_MARGIN);
			sr.end();
            batch.begin();
            Assets.font.getData().setScale(1.5f);
            Assets.font.draw(batch, "no moves left", Configs.GAME_WIDTH/2-20, (Configs.GAME_HEIGHT-Configs.HEIGHT_MARGIN)/2+40);
			batch.draw(Assets.retryButton, Configs.RETRYBUTTON_POSITION_X, Configs.RETRYBUTTON_POSITION_Y);
            batch.end();

		}
    }

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}

	@Override
	public void dispose() {
		super.dispose();
		Assets.dispose();
	}
	
	public void updatePosition() {
		if(touchPosition == null)new Vector2();
		touchPosition = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
	}
	
	public void fillShapes(){
		if(shapes == null)shapes = new Shape[3];
		for(int x = 0; x < 3; x++){
			this.shapes[x] = new Shape();
			this.shapes[x].x = this.nextXcoordAt(x);
			this.shapes[x].y = this.nextYcoordAt(x);
		}
	}
    
	public boolean hasShapes(){
		int count = 3;
		for(int x = 0; x < 3; x++){
			if(this.shapes[x] == null){
				count--;
			}
		}
		System.out.println(count);
		return count != 0;
	}

	public boolean canPlaceShapes(){
		for (int x = 0; x < 3; x++) {
			if (this.shapes[x] == null) continue;
			if (table.isPlaceable(shapes[x])) return true;
		}
		return false;
	}

    private int nextXcoordAt(int i) {
        int x = Configs.WIDTH_MARGIN + i * (Configs.SMALL_BLOCK_SIZE * 6);
        return x;
    }
    
    private int nextYcoordAt(int i){
    	return Configs.HEIGHT_MARGIN/3;
    
    }
}
