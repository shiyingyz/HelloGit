package ̰���߻�ʳ����v621;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

//�ߡ�ʳ��ڵ�
public class SNode {
	public int x,y;
	public Rectangle rect;//�ڵ��������
	
	public Color color;
	
	public SNode(int x,int y,Color c) {
		this.x = x;
		this.y = y;
		this.color = c;
		this.rect = new Rectangle(x, y, Constant.FOOD_WIDTH, Constant.FOOD_HEIGHT);
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(this.color);//���û�����ɫ
		g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
	}
	
	public Rectangle getRect() {
		return rect;
	}


}
