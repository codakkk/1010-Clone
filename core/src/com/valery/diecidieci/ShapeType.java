package com.valery.diecidieci;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;

public enum ShapeType {

	SINGLE, 
	SQUARE, 
	HORIZONTAL2, HORIZONTAL3, HORIZONTAL4, HORIZONTAL5,
	VERTICAL2, VERTICAL3, VERTICAL4, VERTICAL5,
	/*  @
	 * @@
	*/
	LEFT_L3, 
	/*  @
	 *  @@
	*/
	RIGHT_L3,
	/* @@
	 * @
	*/
	DOWN_L3, 
	/* @@
	 *  @
	*/
	UP_L3, 
	/*
	 * @
	 * @
	 * @@@
	 */
	RIGHT_L5, 
	/*
	 *   @
	 *   @
	 * @@@
	 */

	LEFT_L5, 
	/*
	 * @@@
	 * @
	 * @
	 */
	DOWN_L5, 
	/*
	 * @@@
	 *   @
	 *   @
	 */
	UP_L5,
	/*
	 * @@@
	 * @@@
	 * @@@
	 */
	THREEXTHREE;
	
	ShapeType(){
		
	}
	
	public static final ShapeType[] VALUES = ShapeType.values();
	
	public static ShapeType getRandom(){
		return VALUES[MathUtils.random(0, ShapeType.values().length-1)];
	}
	
	public int[][] getShape(){
		switch(this){
		case SQUARE:
			int map[][] = {{1, 1}, {1, 1}};
			return map;
		case HORIZONTAL2:
			int map1[][] = {{1},{1}};
			return map1;
		case HORIZONTAL3:
			int map2[][] = {{1},{1},{1}};
			return map2;
		case HORIZONTAL4:
			int map3[][] = {{1},{1},{1},{1}};
			return map3;
		case HORIZONTAL5:
			int map4[][] = {{1},{1},{1},{1},{1}};
			return map4;
		case SINGLE:
			int map5[][] = {{1}};
			return map5;
		case VERTICAL2:
			int map6[][] = {{1, 1}};
			return map6;
		case VERTICAL3:
			int map7[][] = {{1, 1, 1}};
			return map7;
		case VERTICAL4:
			int map8[][] = {{1, 1, 1, 1}};
			return map8;
		case VERTICAL5:
			int map9[][] = {{1, 1, 1, 1, 1}};
			return map9;
		case RIGHT_L5:
			int map11[][]= {{1, 1, 1}, {1, 0, 0}, {1, 0, 0}};
			return map11;
		case LEFT_L5:
			int map12[][]= {{0, 0, 1}, {0, 0, 1}, {1, 1, 1}};
			return map12;
		case DOWN_L5:
			int map13[][]= {{0, 0, 1}, {0, 0, 1}, {1, 1, 1}};
			return map13;
		case UP_L5:
			int map14[][]= {{0, 0, 1}, {0, 0, 1}, {1, 1, 1}};
			return map14;
		case LEFT_L3:
			int map15[][]= {{0, 1}, {1, 1}};
			return map15;
		case DOWN_L3:
			int map16[][]= {{1, 1}, {0, 1}};
			return map16;
		case RIGHT_L3:
			int map17[][]= {{1, 1}, {1, 0}};
			return map17;
		case UP_L3:
			int map18[][]= {{0, 1}, {1, 1}};
			return map18;
		case THREEXTHREE:
			int map19[][] = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
			return map19;
		default:
			break;
		}
		Gdx.app.debug("DEBUG: ", "Something happened in getShape: returned null");
		return null;
	}
	
	public int getShapeColor(){
		if(Configs.FACES){
			switch(this){
				case SQUARE:
					return 3;
				case SINGLE:
					return 6;
				case HORIZONTAL2:
				case HORIZONTAL3:
				case HORIZONTAL4:
				case HORIZONTAL5:
					return 1;
				case VERTICAL2:
				case VERTICAL3:
				case VERTICAL4:
				case VERTICAL5:
					return 2;
				case LEFT_L3:
				case DOWN_L3:
				case RIGHT_L3:
				case UP_L3:
					return 2;
				case LEFT_L5:
				case DOWN_L5:
				case RIGHT_L5:
				case UP_L5:
					return 4;
				default: return 5;
			}
		} else {
			switch (this) {
				case SQUARE:
					return 3;
				case VERTICAL2:
				case HORIZONTAL2:
					return 9;
				case VERTICAL3:
				case HORIZONTAL3:
					return 4;
				case VERTICAL4:
				case HORIZONTAL4:
					return 5;
				case VERTICAL5:
				case HORIZONTAL5:
					return 7;
				case LEFT_L3:
				case DOWN_L3:
				case RIGHT_L3:
				case UP_L3:
					return 2;
				case LEFT_L5:
				case DOWN_L5:
				case RIGHT_L5:
				case UP_L5:
					return 8;
                case THREEXTHREE:
                    return 1;
				default:
					return 9;
			}
		}
	}
}
