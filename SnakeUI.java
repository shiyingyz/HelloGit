package 贪吃蛇画食物蛇v621;

//http://www.zuidaima.com/share/3426176754355200.htm
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import 贪吃蛇游戏实践.Constant;

public class SnakeUI extends JFrame implements KeyListener {
	//uiImg大小必须是JFrame的宽带和高度，不然画不下JFrame
	private BufferedImage uiImg = new BufferedImage(Constant.JFRAME_WIDTH, Constant.JFRAME_HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
	private Rectangle rect ; //游戏区域
	private SNode food;      //食物对象
	private Snake snake;
	private Snake snake2;
	
	//读取ui的食物对象
	public SNode getFood() {
		return food;
	}

	public SnakeUI() {
		this.setTitle("贪吃蛇V0.1");
		this.setLocation(300, 100);
		this.setSize(Constant.JFRAME_WIDTH, Constant.JFRAME_HEIGHT);		
	    this.rect = Constant.rect;
	    this.creatFood();
	    this.snake = new Snake(this);
	    this.snake2 = new Snake(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addKeyListener(this); //键盘按压的侦听事件，只要键盘控制后，就能触发该事件方法
		new Thread(new UIUpdate()).start();		
		this.setVisible(true);
	}
	
	//食物的随机坐标由游戏的起始坐标加上一个【0-50】的整数倍节点长度
	private void creatFood() {
		int foodx = this.rect.x+(int)(Math.random()*49)*15;
		int foody = this.rect.y+(int)(Math.random()*34)*15;
		this.food = new SNode(foodx, foody, Color.BLUE);
	}
	
	@Override  //所有对象的绘入必须重写的方法，自动调用
	public void paint(Graphics g) {
		// 设置矩形的颜色，并绘入到uiImg图片，将此图片直接写入到内存
		Graphics2D g2d = (Graphics2D) uiImg.getGraphics();
		//在缓存图片画白色背景
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0,this.getWidth(), this.getHeight());	
		
		//刻上作者名字
		String out=new String("@copyright reserved by 严志！");  	
		Color c= new Color(25, 2, 112);
		g2d.setColor(c);// 文字颜色
		g2d.setFont(new Font("宋体", Font.ITALIC, 16));// 设置字体
		g2d.drawString(out, 500, 500);
				
		//画金色网格
		g2d.setColor(Color.CYAN);
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 35; j++) {
				g2d.drawRect(rect.x + i * 15, rect.y + j * 15, 15, 15);
			}
		}
		
		//画边框
		g2d.setColor(Color.black);
		g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
		
		//画食物
		this.food.draw(g2d);
		
		//画蛇
		this.snake.draw(g2d);
		this.snake2.draw(g2d);
		
		//蛇与食物碰撞后，重新初始化食物
		if (this.snake.hit(food)) {
			this.creatFood();
		}
		
		if (this.snake2.hit(food)) {
			this.creatFood();
		}
		g.drawImage(uiImg, 0, 0, null); //写入到内存
	}
		
	//内部类
	class UIUpdate implements Runnable{

		@Override
		public void run() {
			while (true) {
				repaint();	//repaint方法实质是重新执行paint方法	
				try {
					Thread.sleep(Constant.SLEEPTIME);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}					
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		   case KeyEvent.VK_LEFT:
				snake.dir = Constant.L;
				System.out.println("dddl");
				break;
			case KeyEvent.VK_RIGHT:
				snake.dir = Constant.R;
				System.out.println("dddr");
				break;
			case KeyEvent.VK_UP:
				snake.dir = Constant.U;
				System.out.println("dddu");
				break;
			case KeyEvent.VK_DOWN:
				snake.dir = Constant.D;
				break;
		  }	
		Constant.SLEEPTIME-=50;
		if (Constant.SLEEPTIME<=100) {
			Constant.SLEEPTIME=1000;
		}
		
	}


	@Override
	public void keyTyped(KeyEvent e) {
	   
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		Constant.SLEEPTIME=200;
		
	}
	
	public static void main(String[] args) {
		new SnakeUI();
	}

}
