package day03;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FirstFrame {
	public static void main(String[] args) {
		JFrame 框 = new JFrame("第一个框");// 定义一个框
		// Java界面默认不可见
		框.setVisible(true);// 使窗口可见
		框.setSize(320, 280);
		框.setLocation(1440 / 2 - 320 / 2, 900 / 2 - 280 / 2);// 左上角的点为框的定位点
		框.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//彻底关闭该窗口以释放内存
		//准备画纸对象JPanel
		JPanel panel=new JPanel();
		panel.setBackground(Color.black);
		//将画纸添加到窗口
		框.add(panel);
	}
}
