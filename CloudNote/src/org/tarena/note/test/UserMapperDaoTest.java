package org.tarena.note.test;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.tarena.note.dao.UserMapperDao;
import org.tarena.note.entity.User;

@Repository
public class UserMapperDaoTest {
	
	@Resource//������ôʵ��ע��
	private UserMapperDao umd;
	
//	@Test
	public void get(){
		
	    System.out.println(umd);
	    User user = umd.findByName("nzq");
//	    List<User> users =(List<User>) umd.findAll();
//	    for(User u :users){
	    System.out.println(user.toString());
//	    }
	}
	public static void main(String[] args) {
	String xml="applicationContext.xml";
	ApplicationContext ac = new ClassPathXmlApplicationContext(xml);
	UserMapperDaoTest me = ac.getBean("userMapperDaoTest",UserMapperDaoTest.class);
	
		me.get();
	}

}
