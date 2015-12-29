package com.valery.diecidieci;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public class Shape {

	//debug purpose
	public static int idCounter;
	public int id;
	//
	Block[] blocks;
	int[][] mapBlocks;
	
	ShapeType shapeType;
	
	public int x, y;
	public int color;
	public int blockSize;
	
	public Shape(){
		this.id = idCounter++;
		this.x = 0;
		this.y = 0;
		shapeType = ShapeType.getRandom();
		this.color = shapeType.getShapeColor();
		this.blockSize = Configs.SMALL_BLOCK_SIZE;
		this.mapBlocks = this.shapeType.getShape();
		this.blocks = new Block[this.mapBlocks.length * this.mapBlocks[0].length];
		System.out.println(blocks.length);
		for(int xx = 0; xx < blocks.length; xx++){
			this.blocks[xx] = new Block(color);
		}
	}
	
	public void render(SpriteBatch batch){
		for(int x = 0; x < this.mapBlocks.length; x++){
			for(int y = 0; y < this.mapBlocks[0].length; y++){
				if(mapBlocks[x][y] == 1){
					int blockX = this.x + x * blockSize + 1;
					int blockY = this.y + y * blockSize + 1;
					batch.draw(Assets.blocksColors[this.color], blockX, blockY, blockSize, blockSize);
				}
			}	
		}
	}
	
	public void release(){
		this.blockSize = Configs.SMALL_BLOCK_SIZE;
	}
	
	public void touch(){
		this.blockSize = Configs.BLOCK_SIZE;
	}
	
	public boolean isColliding(Vector3 pos){
		if(pos.x > this.x && pos.x < this.x+this.mapBlocks.length*blockSize+blockSize*2){
			if(pos.y > this.y && pos.y < this.y+this.mapBlocks[0].length*blockSize+blockSize*2){
				return true;
			}
		}
		return false;
	}
	
	public Block getBlockAt(int x){
		return blocks[x];
	}
	
	public int getShapeWidth(){
		return this.mapBlocks.length * Configs.BLOCK_SIZE;
	}
	
	public int getShapeHeight(){
		return this.mapBlocks[0].length * Configs.BLOCK_SIZE;
	}
}
