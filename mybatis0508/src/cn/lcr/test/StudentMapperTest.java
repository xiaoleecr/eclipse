package cn.lcr.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import cn.lcr.entity.Student;
import cn.lcr.mapper.StudentMapper;

public class StudentMapperTest {
	SqlSessionFactory sessionFactory;
	@Before
	public void setup() throws IOException{
		String resource = "mybatis_config.xml";
		InputStream is = Resources.getResourceAsStream(resource);
		this.sessionFactory = new SqlSessionFactoryBuilder().build(is);
	}

	@Test
	public void testFindStuById() {
		SqlSession session = sessionFactory.openSession();
		StudentMapper stuMapper = session.getMapper(StudentMapper.class);
		Student student = stuMapper.findStuById(29);
		System.out.println(student.toString());
	}

}
