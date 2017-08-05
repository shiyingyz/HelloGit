package 贪吃蛇画食物蛇v621;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

//蛇、食物节点
public class SNode {
	public int x,y;
	public Rectangle rect;//节点矩形区域
	
	public Color color;
	
	public SNode(int x,int y,Color c) {
		this.x = x;
		this.y = y;
		this.color = c;
		this.rect = new Rectangle(x, y, Constant.FOOD_WIDTH, Constant.FOOD_HEIGHT);
	}
	
	public void draw(Graphics2D g2d) {
		g2d.setColor(this.color);//设置画笔颜色
		g2d.fillRect(rect.x, rect.y, rect.width, rect.height);
	}
	
	public Rectangle getRect() {
		return rect;
	}


}
