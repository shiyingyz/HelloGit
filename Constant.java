package ̰���߻�ʳ����v621;

import java.awt.Rectangle;

public class Constant {
	public static final int L=1,R=2,U=3,D=4; //4����λ�ĳ���
	
	public static final int FOOD_WIDTH = 15; // ʳ��Ŀ�Ⱥ͸߶�

	public static final int FOOD_HEIGHT = 15;

	public static final int GAME_WIDTH = 15 * 50; // ��Ϸ�ľ�������

	public static final int GAME_HEIGHT = 15 * 35;

	public static int SLEEPTIME = 200;
	
	// ��Ϸ�ľ�������
	public static final Rectangle rect = new Rectangle(Constant.FOOD_WIDTH, Constant.FOOD_HEIGHT * 2 + 6,
			Constant.GAME_WIDTH, Constant.GAME_HEIGHT);

	// JFrame�Ŀ�Ⱥ͸߶�
	public static final int JFRAME_WIDTH = (rect.width + FOOD_WIDTH * 2); // ��Ϸ�ľ�������

	public static final int JFRAME_HEIGHT = (rect.height + FOOD_HEIGHT * 3 + 6);

}
