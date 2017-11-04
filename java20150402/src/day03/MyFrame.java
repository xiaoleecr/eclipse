package day03;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyFrame {
	public static void main(String[] args) {
		JFrame kuang = new JFrame("这是一个框");
		kuang.setVisible(true);
		kuang.setSize(500, 400);
		kuang.setLocation(1440 / 2 - 500 / 2, 900 / 2 - 400 / 2);
		kuang.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 创建一个自定义面板
		MyPanel panel = new MyPanel();
		kuang.add(panel);

	}
}

// 自定义面板的模版
class MyPanel extends JPanel {// 继承JPanel的画画功能
	public void paint(Graphics g) {// 画画的动作g:画笔
		g.setColor(Color.blue);
		g.fillOval(500 / 2 - 300 / 2, 400 / 2 - 300 / 2, 300, 300);// head
		g.setColor(Color.white);// face
		g.fillOval(120, 100, 260, 260);
		g.setColor(Color.red);
		g.fillOval(500 / 2 - 230 / 2, 110, 230, 200);// mouth
		g.setColor(Color.white);// cover
		g.fillRect(500 / 2 - 240 / 2, 180, 240, 50);
		g.fillOval(500/2-230/2, 130, 230, 90);
		g.fillOval(500/2-176/2, 104, 176, 90);
		g.setColor(Color.white);//eyes
		g.fillOval(500/2-110/2, 65, 55, 70);//lefteye
		g.fillOval((500/2-110/2)+55, 65, 55, 70);//righteye
		g.setColor(Color.black);//lreyes'line
		g.drawOval(500/2-110/2, 65, 55, 70);
		g.drawOval((500/2-110/2)+55, 65, 55, 70);
		g.fillOval((500/2-7/2)+10, 95, 13, 15);
		g.fillOval((500/2-7/2)-20, 95, 13, 15);
		g.drawOval((500/2-7/2)-20, 95, 13, 15);
	}
}
