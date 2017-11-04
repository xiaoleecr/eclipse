package org.tarena.note.test;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.tarena.note.Service.Impl.UserServiceImpl;
import org.tarena.note.entity.NoteResult;

public class UserServiceImplTest {
	
	
	public void get(){
		String xml="applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(xml);
		UserServiceImpl usi = ac.getBean("userServiceImpl",UserServiceImpl.class);
		NoteResult nr = usi.checkLogin("nzq","123456");
		System.out.println(nr.toString());
		
	}
	
	public static void main(String[] args) {
		UserServiceImplTest usit = new UserServiceImplTest();
		usit.get();
	}

}
