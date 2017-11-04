package org.tarena.note.test;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Repository;
import org.tarena.note.dao.NoteMapperDao;


@Repository
public class Testone {
	
	@Resource//������ôʵ��ע��
	private NoteMapperDao umd;
	public void get(){
		
	    System.out.println(umd);
	    List<Map<String,Object>> list = umd.findNotesByBookId("1db556b9-d1dc-4ed9-8274-45cf0afbe859");
//	    List<User> users =(List<User>) umd.findAll();
//	    for(User u :users){
	    System.out.println(list.toString());
//	    }
	}
	public static void main(String[] args) {
	String xml="applicationContext.xml";
	ApplicationContext ac = new ClassPathXmlApplicationContext(xml);
	Testone me = ac.getBean("testone",Testone.class);
		me.get();
	}


}
