package 贪吃蛇画食物蛇v621;

import java.awt.Rectangle;

public class Constant {
	public static final int L=1,R=2,U=3,D=4; //4个方位的常量
	
	public static final int FOOD_WIDTH = 15; // 食物的宽度和高度

	public static final int FOOD_HEIGHT = 15;

	public static final int GAME_WIDTH = 15 * 50; // 游戏的矩形区域

	public static final int GAME_HEIGHT = 15 * 35;

	public static int SLEEPTIME = 200;
	
	// 游戏的矩形区域
	public static final Rectangle rect = new Rectangle(Constant.FOOD_WIDTH, Constant.FOOD_HEIGHT * 2 + 6,
			Constant.GAME_WIDTH, Constant.GAME_HEIGHT);

	// JFrame的宽度和高度
	public static final int JFRAME_WIDTH = (rect.width + FOOD_WIDTH * 2); // 游戏的矩形区域

	public static final int JFRAME_HEIGHT = (rect.height + FOOD_HEIGHT * 3 + 6);

}
