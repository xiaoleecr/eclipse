package cn.lcr.mapper;

import cn.lcr.entity.Student;

public interface StudentMapper {
	/**
	 * 根据ID查询学生
	 * @param id
	 * @return
	 */
	public Student findStuById(int id);
	
	/**
	 * 更新学生
	 * @param stu
	 */
	public void updateStu(Student student);
	
	/**
	 * 根据id删除学生
	 * @param id
	 */
	public void deleteStuById(int id);
	
	/**
	 * 新增单个学生
	 * @param stu
	 */
	public void insertStu(Student stu);
}
