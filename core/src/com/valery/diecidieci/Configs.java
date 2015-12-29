package com.valery.diecidieci;

public class Configs {
	
	public static final int TABLE_SIZE = 10; //10x10
	public static final int WIDTH_MARGIN = 40;
	public static final int HEIGHT_MARGIN = 200;
	public static final int BLOCK_SIZE = 42;
	public static final int SMALL_BLOCK_SIZE = 26;
	public static final boolean FACES = false;
	public static final int GAME_WIDTH = TABLE_SIZE * BLOCK_SIZE + WIDTH_MARGIN*2;
	public static final int GAME_HEIGHT = TABLE_SIZE * BLOCK_SIZE + HEIGHT_MARGIN*2;

	//
	public static final int RETRYBUTTON_POSITION_X = Configs.GAME_WIDTH/2-20;
	public static final int RETRYBUTTON_POSITION_Y = (Configs.GAME_HEIGHT-Configs.HEIGHT_MARGIN)/2+60;
}
