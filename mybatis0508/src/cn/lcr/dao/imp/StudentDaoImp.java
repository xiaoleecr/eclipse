package cn.lcr.dao.imp;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import cn.lcr.dao.StudentDao;
import cn.lcr.entity.Student;

public class StudentDaoImp implements StudentDao{
	public SqlSessionFactory sessionFactory;
	
	public StudentDaoImp(SqlSessionFactory factory) {
		this.sessionFactory = factory;
	}

	@Override
	public Student findStuById(int id) {
		SqlSession session = sessionFactory.openSession();
		Student student = session.selectOne("test.findStuById", id);
		session.close();
		return student;
	}

	@Override
	public void deleteStuById(int id) {
		SqlSession session = sessionFactory.openSession();
		session.delete("test.deleteStuById",id );
		session.commit();
		session.close();
	}

	@Override
	public void insertStu(Student student) {
		SqlSession session = sessionFactory.openSession();
		session.insert("test.addStu", student);
		session.commit();
		session.close();
	}

	@Override
	public void updateStu(Student student) {
		SqlSession session = sessionFactory.openSession();
		session.update("test.updateStu",student );
		session.commit();
		session.close();
	}

}
