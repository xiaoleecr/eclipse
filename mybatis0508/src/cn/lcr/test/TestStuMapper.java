package cn.lcr.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.lcr.entity.Student;
public class TestStuMapper {
	@Test
	public void findStuById() throws IOException{
		String resource = "mybatis_config.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = sessionFactory.openSession();
//		Student stu = session.selectOne("test.findStuById", 1);
		Student stu = new Student();
		stu.setSname("Ç®°Ë");
		session.insert("test.addStu", stu);
		System.out.println(stu.toString());
		session.commit();
		session.close();
	}
}
