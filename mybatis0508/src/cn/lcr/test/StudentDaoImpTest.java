package cn.lcr.test;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.lcr.dao.imp.StudentDaoImp;
import cn.lcr.entity.Student;

public class StudentDaoImpTest {
	SqlSessionFactoryBuilder builder;
	SqlSessionFactory factory;
	@Before
	public void setup() throws IOException{
		String resource = "mybatis_config.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		this.factory = new SqlSessionFactoryBuilder().build(is);
	}
//	@Test
	public void testFindStuById() {
		StudentDaoImp studao = new StudentDaoImp(factory);
		Student student  = studao.findStuById(27);
		System.out.println(student.toString());
	}

//	@Test
	public void testDeleteStuById() {
		StudentDaoImp studao = new StudentDaoImp(factory);
		
	}

//	@Test
	public void testInsertStu() {
		StudentDaoImp studao = new StudentDaoImp(factory);
		Student student = new Student();
		student.setSname("¿œ¿Ó");
		student.setSscore(86);
		studao.insertStu(student);
		System.out.println(student.toString());
	}

	@Test
	public void testUpdateStu() {
		StudentDaoImp studao = new StudentDaoImp(factory);
		Student student = new Student();
		student.setSno(2);
		student.setSname("zhangsan");
		student.setSscore(87);
		studao.updateStu(student);
		System.out.println(student.toString());
	}

}
