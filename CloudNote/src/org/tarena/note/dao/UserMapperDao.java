package org.tarena.note.dao;

import java.util.List;
import java.util.Map;

import org.tarena.note.entity.User;

public interface UserMapperDao {
	
   public User findByName(String name);
   public List<User> findAll();
   public int updateToken(Map<String,Object> params);
   public int regist(User user);
}
