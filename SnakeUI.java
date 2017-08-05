package ̰���߻�ʳ����v621;

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

import ̰������Ϸʵ��.Constant;

public class SnakeUI extends JFrame implements KeyListener {
	//uiImg��С������JFrame�Ŀ���͸߶ȣ���Ȼ������JFrame
	private BufferedImage uiImg = new BufferedImage(Constant.JFRAME_WIDTH, Constant.JFRAME_HEIGHT, BufferedImage.TYPE_3BYTE_BGR);
	private Rectangle rect ; //��Ϸ����
	private SNode food;      //ʳ�����
	private Snake snake;
	private Snake snake2;
	
	//��ȡui��ʳ�����
	public SNode getFood() {
		return food;
	}

	public SnakeUI() {
		this.setTitle("̰����V0.1");
		this.setLocation(300, 100);
		this.setSize(Constant.JFRAME_WIDTH, Constant.JFRAME_HEIGHT);		
	    this.rect = Constant.rect;
	    this.creatFood();
	    this.snake = new Snake(this);
	    this.snake2 = new Snake(this);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.addKeyListener(this); //���̰�ѹ�������¼���ֻҪ���̿��ƺ󣬾��ܴ������¼�����
		new Thread(new UIUpdate()).start();		
		this.setVisible(true);
	}
	
	//ʳ��������������Ϸ����ʼ�������һ����0-50�����������ڵ㳤��
	private void creatFood() {
		int foodx = this.rect.x+(int)(Math.random()*49)*15;
		int foody = this.rect.y+(int)(Math.random()*34)*15;
		this.food = new SNode(foodx, foody, Color.BLUE);
	}
	
	@Override  //���ж���Ļ��������д�ķ������Զ�����
	public void paint(Graphics g) {
		// ���þ��ε���ɫ�������뵽uiImgͼƬ������ͼƬֱ��д�뵽�ڴ�
		Graphics2D g2d = (Graphics2D) uiImg.getGraphics();
		//�ڻ���ͼƬ����ɫ����
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0,this.getWidth(), this.getHeight());	
		
		//������������
		String out=new String("@copyright reserved by ��־��");  	
		Color c= new Color(25, 2, 112);
		g2d.setColor(c);// ������ɫ
		g2d.setFont(new Font("����", Font.ITALIC, 16));// ��������
		g2d.drawString(out, 500, 500);
				
		//����ɫ����
		g2d.setColor(Color.CYAN);
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 35; j++) {
				g2d.drawRect(rect.x + i * 15, rect.y + j * 15, 15, 15);
			}
		}
		
		//���߿�
		g2d.setColor(Color.black);
		g2d.drawRect(rect.x, rect.y, rect.width, rect.height);
		
		//��ʳ��
		this.food.draw(g2d);
		
		//����
		this.snake.draw(g2d);
		this.snake2.draw(g2d);
		
		//����ʳ����ײ�����³�ʼ��ʳ��
		if (this.snake.hit(food)) {
			this.creatFood();
		}
		
		if (this.snake2.hit(food)) {
			this.creatFood();
		}
		g.drawImage(uiImg, 0, 0, null); //д�뵽�ڴ�
	}
		
	//�ڲ���
	class UIUpdate implements Runnable{

		@Override
		public void run() {
			while (true) {
				repaint();	//repaint����ʵ��������ִ��paint����	
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
