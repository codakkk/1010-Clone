package com.valery.diecidieci;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by Ciro on 26/12/2015.
 */
public class Table {
	
	private int x, y;
	public Block[][] blocks;
    public List<Block> animatedBlocks;
    
	public Table(int x, int y){
		this.x = x;
		this.y = y;
		this.blocks = new Block[Configs.TABLE_SIZE][Configs.TABLE_SIZE];
		animatedBlocks = new ArrayList<Block>();
	}

	public void reset(){
		for(int x = 0; x < Configs.TABLE_SIZE; x++) {
			for (int y = 0; y < Configs.TABLE_SIZE; y++) {
				this.blocks[x][y] = null;
			}
		}
		animatedBlocks.clear();
	}

	public void render(SpriteBatch batch){
		Texture texture;
		for(int x = 0; x < Configs.TABLE_SIZE; x++){
			for(int y = 0; y < Configs.TABLE_SIZE; y++){
				if(this.blocks[x][y] == null){
					texture = Assets.blocksColors[Block.BLOCK_GREY];
				} else {
					texture = Assets.blocksColors[blocks[x][y].color];
				}
				batch.draw(texture, this.x + x * Configs.BLOCK_SIZE, this.y + y * Configs.BLOCK_SIZE, Configs.BLOCK_SIZE, Configs.BLOCK_SIZE);
			}
		}
		if(this.animatedBlocks != null && this.animatedBlocks.size() > 0){
			for(int x = 0; x < this.animatedBlocks.size(); x++){
				if(this.animatedBlocks.get(x).isAlive){
					this.animatedBlocks.get(x).anim(batch);
				}
			}	
		}
	}
	
	public boolean place(Shape shape){
		if(shape == null)return false;
		int[] position = computePosition(shape);
		if(position == null)return false;
		if(!isInTable(shape, position))return false;
		if(!isPlaceable(shape))return false;
		if(isOccupied(shape, position))return false;
		addToBlocks(shape, position);
		return true;
	}
	
	public void addToBlocks(Shape shape, int[] position){
		int[][] map = shape.mapBlocks;
		int idx = 0;
		for(int x = 0; x < map.length; x++){
			for(int y = 0; y < map[0].length; y++){
				if(map[x][y] == 1){
					int posX = position[0] + x;
					int posY = position[1] + y;
					Block b = shape.getBlockAt(idx);
					b.col = posX;
					b.row = posY;
					this.blocks[posX][posY] = b;
					idx++;
				}
			}
		}
	}
	
	public boolean isInTable(Shape shape, int position[]){
		return position[0] + shape.mapBlocks.length <= Configs.TABLE_SIZE && 
				position[1] + shape.mapBlocks[0].length <= Configs.TABLE_SIZE;
	}
	
	public boolean isPlaceable(Shape shape){
		for(int y = 0; y <= Configs.TABLE_SIZE-shape.mapBlocks[0].length; y++){
			for(int x = 0; x <= Configs.TABLE_SIZE-shape.mapBlocks.length; x++){
				if(isOccupied(shape, new int[]{x, y}))continue;
				return true;
			}
		}
		return false;
	}
	
	public boolean isOccupied(Shape shape, int[] position){
		int[][] dmap = shape.mapBlocks;
		for(int x = 0; x < dmap.length; x++){
			for(int y = 0; y < dmap[0].length; y++){
				if(dmap[x][y] == 1 && blocks[position[0]+x][position[1]+y] != null){
					return true;
				}
			}
		}
		return false;
	}
	
	public int[] computePosition(Shape shape){
		for(int x = 0; x < Configs.TABLE_SIZE; x++){
			for(int y = 0; y < Configs.TABLE_SIZE; y++){
				int eachX = this.x + x * Configs.BLOCK_SIZE-1;
				int eachY = this.y + y * Configs.BLOCK_SIZE-1;
				if(shape.x > eachX - Configs.BLOCK_SIZE/2 && shape.x < eachX + Configs.BLOCK_SIZE/2 &&
						shape.y > eachY - Configs.BLOCK_SIZE/2 && shape.y < eachY + Configs.BLOCK_SIZE/2){
					shape.x = eachX;
					shape.y = eachY;
					return new int[]{x, y};					
				}
			}
		}
		return null;
	}
	
	public int clearLines(){
		int c = 0;
		for(int y = 0; y < Configs.TABLE_SIZE; y++){
			if(isRowLineDone(y)){
				removeRowLine(y);
				c += 10;
			}
			
			if(isColLineDone(y)){
				removeColLine(y);
				c += 10;
			}
		}
		return c;
	}
	
	public boolean isRowLineDone(int y){
		int c = 0;
		for(int x = 0; x < Configs.TABLE_SIZE; x++){
			if(this.blocks[x][y] == null)break;
			c++;
		}
		if(c < Configs.TABLE_SIZE){
			return false;
		}
		return true;
	}
	
	public boolean isColLineDone(int x){
		int c = 0;
		for(int y = 0; y < Configs.TABLE_SIZE; y++){
			if(this.blocks[x][y] == null)break;
			c++;
		}
		if(c < Configs.TABLE_SIZE){
			return false;
		}
		return true;
	}
	
	public void removeRowLine(int y){
		for(int x = 0; x < Configs.TABLE_SIZE; x++){
			this.animatedBlocks.add(this.blocks[x][y]);
			blocks[x][y] = null;
		}
	}
	
	public void removeColLine(int x){
		for(int y = 0; y < Configs.TABLE_SIZE; y++){
			this.animatedBlocks.add(this.blocks[x][y]);
			blocks[x][y] = null;
		}
	}
}
