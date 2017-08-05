package ̰���߻�ʳ����v621;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import ̰���߻�ʳ����v621.Constant;
import ̰���߻�ʳ����v621.SNode;

public class Snake {
	private List<SNode> nodes = new ArrayList<SNode>();
	public int dir=Constant.R; //�ߵ�һ�㷽λ,��ʼ��������
	private SnakeUI ui; //�����еĽ���
	
	public Snake(SnakeUI ui) { //ȷ��ʳ���߱�������ͬһ��UI����
		this.ui = ui;
		this.noOverride(this.ui.getFood());	//�������ظ�����	
	}
	
	//����ʳ����Զ�����غ�
	private void noOverride(SNode food) {
		this.generateSnake();
		while(this.hit(food)) { //����ʳ�ﲻ�ཻ���˳�
			this.generateSnake();
		}
	}
	
	//�������һ����
	private void generateSnake() {		
		nodes.clear();
		//��һ���ڵ�
		int foodx = Constant.rect.x+(int)(Math.random()*49)*15;
		int foody = Constant.rect.y+(int)(Math.random()*34)*15;
		SNode s = new SNode(foodx, foody, Color.GREEN);
		nodes.add(s);
        this.addNode();
	}
	
	//���ݷ�����ĩβ��ӽڵ�,�����ͷ�������ߵ��ƶ�������ӽڵ�
	public synchronized void addNode() {
		SNode start = nodes.get(0);
		switch (dir) {
		case Constant.L: // �������Խ���жϣ�
			if (start.x <= 15) {
				System.out.println(start.x);				
				start = new SNode(15 + 15 * 50, start.y, Color.GREEN); // ���Խ��������ұ߳���,����ͷ���ұ߳���
			}
			// ���ƣ�����ԭ���Ļ��������һ���ڵ㣬���ӵĽڵ�䵱��ͷ
			nodes.add(0, new SNode(start.x - Constant.FOOD_WIDTH, start.y,Color.GREEN));
			break;
		case Constant.R:			
			if (start.x >= 15 * 50) {
				System.out.println("right:"+start.x);
			   	start = new SNode(15 - Constant.FOOD_WIDTH, start.y,Color.GREEN);
			}
			nodes.add(0, new SNode(start.x + Constant.FOOD_WIDTH, start.y,Color.GREEN));
			break;
		case Constant.U: // ��Խ��
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
	
	//true:����ʳ���غϣ��������໥��ײ��1.��ʼ���ߣ�2.��ʳ�
	public boolean hit(SNode food) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getRect().intersects(food.getRect())) {
				this.addNode(); //��ʳ��
				return true;
			}
		}
		return false;
	}
	
	//���߷���
	public void draw(Graphics2D g2d) {
		for (int i = 0; i < nodes.size(); i++) {
			SNode s = nodes.get(i);
			s.draw(g2d);
		}
		this.move();//�ߵ��˶�Ҳ����
	}
	
	//�ڻ����˶�ʱ����
	private void move() {
		nodes.remove(nodes.size()-1);//�Ƴ����һ����
		this.addNode();//�����һ��ͷ��㣬������ƶ�
	}

}
