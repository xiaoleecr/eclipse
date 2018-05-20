package cn.lcr.mapper;

import cn.lcr.entity.Student;

public interface StudentMapper {
	/**
	 * ����ID��ѯѧ��
	 * @param id
	 * @return
	 */
	public Student findStuById(int id);
	
	/**
	 * ����ѧ��
	 * @param stu
	 */
	public void updateStu(Student student);
	
	/**
	 * ����idɾ��ѧ��
	 * @param id
	 */
	public void deleteStuById(int id);
	
	/**
	 * ��������ѧ��
	 * @param stu
	 */
	public void insertStu(Student stu);
}
