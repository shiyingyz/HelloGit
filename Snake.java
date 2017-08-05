package 贪吃蛇画食物蛇v621;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import 贪吃蛇画食物蛇v621.Constant;
import 贪吃蛇画食物蛇v621.SNode;

public class Snake {
	private List<SNode> nodes = new ArrayList<SNode>();
	public int dir=Constant.R; //蛇的一点方位,初始方向向右
	private SnakeUI ui; //蛇运行的界面
	
	public Snake(SnakeUI ui) { //确保食物蛇必须是在同一个UI对象
		this.ui = ui;
		this.noOverride(this.ui.getFood());	//产生不重复的蛇	
	}
	
	//蛇与食物永远不会重合
	private void noOverride(SNode food) {
		this.generateSnake();
		while(this.hit(food)) { //蛇与食物不相交，退出
			this.generateSnake();
		}
	}
	
	//随机产生一条蛇
	private void generateSnake() {		
		nodes.clear();
		//第一个节点
		int foodx = Constant.rect.x+(int)(Math.random()*49)*15;
		int foody = Constant.rect.y+(int)(Math.random()*34)*15;
		SNode s = new SNode(foodx, foody, Color.GREEN);
		nodes.add(s);
        this.addNode();
	}
	
	//根据方向在末尾添加节点,获得蛇头，根据蛇的移动方向添加节点
	public synchronized void addNode() {
		SNode start = nodes.get(0);
		switch (dir) {
		case Constant.L: // 向左边走越界判断，
			if (start.x <= 15) {
				System.out.println(start.x);				
				start = new SNode(15 + 15 * 50, start.y, Color.GREEN); // 如果越界则从最右边出来,将蛇头从右边出来
			}
			// 左移，在蛇原来的基础上添加一个节点，增加的节点充当蛇头
			nodes.add(0, new SNode(start.x - Constant.FOOD_WIDTH, start.y,Color.GREEN));
			break;
		case Constant.R:			
			if (start.x >= 15 * 50) {
				System.out.println("right:"+start.x);
			   	start = new SNode(15 - Constant.FOOD_WIDTH, start.y,Color.GREEN);
			}
			nodes.add(0, new SNode(start.x + Constant.FOOD_WIDTH, start.y,Color.GREEN));
			break;
		case Constant.U: // 上越界
			if (start.y <= (Constant.FOOD_HEIGHT * 2 + 6)) {
				start = new SNode(start.x,(Constant.FOOD_HEIGHT * 2 + 6) + 15 * 35, Color.GREEN);
			}
			nodes.add(0, new SNode(start.x, start.y - Constant.FOOD_HEIGHT,	Color.GREEN));
			break;
		case Constant.D:
			if (start.y >= 36 + 15 * 35 - Constant.FOOD_HEIGHT) {
				start = new SNode(start.x, 36 - Constant.FOOD_HEIGHT,Color.GREEN);
			}
			nodes.add(0, new SNode(start.x, start.y + Constant.FOOD_HEIGHT,	Color.GREEN));
			break;
		}
	}
	
	//true:蛇于食物重合，即发生相互碰撞【1.初始化蛇，2.吃食物】
	public boolean hit(SNode food) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getRect().intersects(food.getRect())) {
				this.addNode(); //吃食物
				return true;
			}
		}
		return false;
	}
	
	//画蛇方法
	public void draw(Graphics2D g2d) {
		for (int i = 0; i < nodes.size(); i++) {
			SNode s = nodes.get(i);
			s.draw(g2d);
		}
		this.move();//蛇的运动也画入
	}
	
	//在画蛇运动时候用
	private void move() {
		nodes.remove(nodes.size()-1);//移除最后一个点
		this.addNode();//再添加一个头结点，完成蛇移动
	}

}
