package day02;

//����Java����

public class TestVir {
	public static void main(String[] args) {
		// ����һ����ʾ���������
		byte age;// ����һ����ʾ���������
		age = 127;// ��������ֵ����ʼ����ע�����䷶Χ�ڸ�ֵ
		System.out.println("����" + age);
		System.out.println(age + 1);
		// ֱ��д��������Ĭ��Ϊint�ͣ���������ȿ����ͣ��Է�Χ���Ϊ�������
		short money = 32767;// ����һ����������ֵ
		System.out.println(money + 1);
		int i = Integer.MAX_VALUE;// int�������ֵ
		System.out.println(i);
		System.out.println(i + 1);// ���-2147483648
		System.out.println(i + 2);// ���-2147483647��������Χ��ѭ������Сֵ�����
		// �������㾡�������ڱ߽�ֵ���㣬����г�����Χ�������ѡ����������
		long l=Long.MAX_VALUE;
		System.out.println(l);
		double d=Double.MAX_VALUE;
		System.out.println(d);
		d=3.1;//ֱ��������ֵĬ��Ϊint�ͣ�ע�ⲻҪ����int�ͷ�Χ
		System.out.println(d);
		System.out.println(d-1.9);
		//double,float�ǲ���ȷ��
		//�����������h�����		
		float f=1.0f;
		//float f=1.0�Ǵ���д��;����ֱ��С����ֵĬ��Ϊdouble����
		System.out.println(f);
		boolean isGirl=true;
		System.out.println(isGirl);
		boolean isTrue=1>=1;//�ù�ϵ���ʽ�ж�
		System.out.println(isTrue); 
		char c=20013;
		System.out.println(c);//'��'
		c=50000;
		System.out.println(c);//?������
		System.out.println('��'+'��');
		c='a';
		System.out.println((int)c+"start");//29233
		int a='v';
		System.out.println((int)a);
	
	}
}
