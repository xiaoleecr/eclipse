package day03;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class FirstFrame {
	public static void main(String[] args) {
		JFrame �� = new JFrame("��һ����");// ����һ����
		// Java����Ĭ�ϲ��ɼ�
		��.setVisible(true);// ʹ���ڿɼ�
		��.setSize(320, 280);
		��.setLocation(1440 / 2 - 320 / 2, 900 / 2 - 280 / 2);// ���Ͻǵĵ�Ϊ��Ķ�λ��
		��.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���׹رոô������ͷ��ڴ�
		//׼����ֽ����JPanel
		JPanel panel=new JPanel();
		panel.setBackground(Color.black);
		//����ֽ��ӵ�����
		��.add(panel);
	}
}
