package org.tarena.note.Service.Impl;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.tarena.note.dao.UserMapperDao;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.User;
import org.tarena.note.service.UserService;
import org.tarena.note.util.NoteUtil;

@Service
public class UserServiceImpl implements UserService{
	@Resource
	private UserMapperDao userDao;
	

	
	public NoteResult checkLogin(
			String username, String password) {
		NoteResult result = new NoteResult();
		//判断用户名
		User user = userDao.findByName(username);
		if(user == null){
			result.setStatus(1);
			result.setMsg("用户不存在");
			return result;
		}
		//判断密码
		String md5_password = NoteUtil.md5(password);
		if(!user.getCn_user_password().equals(md5_password)){
			result.setStatus(2);
			result.setMsg("密码不正确");
			return result;
		}
		//成功
		//生成一个令牌号
		String token = NoteUtil.createToken();
		Map<String,Object> params = 
				new HashMap<String, Object>();
		params.put("userToken", token);
		params.put("userId", user.getCn_user_id());
		String userName = user.getCn_user_name();
		params.put("userName",userName);
		userDao.updateToken(params);//更新db的令牌号
		result.setStatus(0);//0正确
		result.setMsg("登录成功");
		result.setData(params);//传递到客户端
		return result;
	}



	public NoteResult checkLogin(String author) throws Exception {
		String base64_msg = author.substring(author.indexOf(' '));
		byte[] output = Base64.decodeBase64(base64_msg.getBytes());
		String msg = new String(output,"UTF-8");
		String[] msg_arr = msg.split(":");
		String username = msg_arr[0];
		String password = msg_arr[1];
		return checkLogin(username,password);
	}



	public NoteResult checkregist(User user) {
		NoteResult result = new NoteResult();
		//用户名,昵称,密码从请求接收
		//数据检查(格式检查,唯一性检查)
		if("".equals(user.getCn_user_name()) 
				||user.getCn_user_name()==null){
			result.setStatus(1);
			result.setMsg("用户名不能为空");
			return result;
		}
		//TODO 检测密码2，昵称3是否为空
		
		//用户名唯一性检查
		User usr = userDao.findByName(
					user.getCn_user_name());
		if(usr != null){
			result.setStatus(1);
			result.setMsg("用户名已被占用");
			return result;
		}
		//生成ID
		String userId = NoteUtil.createId();
		user.setCn_user_id(userId);
		//密码加密
		String md5_pwd = NoteUtil.md5(
						user.getCn_user_password());
		user.setCn_user_password(md5_pwd);
		//执行cn_user添加操作
		userDao.regist(user);
		result.setStatus(0);
		result.setMsg("注册成功");
		return result;
	}



}