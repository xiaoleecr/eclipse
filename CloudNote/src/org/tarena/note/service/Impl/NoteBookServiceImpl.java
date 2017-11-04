package org.tarena.note.Service.Impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tarena.note.dao.NoteBookMapperDao;
import org.tarena.note.entity.NoteBook;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteBookService;
import org.tarena.note.util.NoteUtil;

@Service
public class NoteBookServiceImpl  implements NoteBookService{

	@Resource
	private NoteBookMapperDao  bookDao;
	
	public NoteResult loadBooks(String userId) {
		NoteResult nr = new  NoteResult();
		if(userId==null||"".equals(userId)){
			nr.setStatus(1);
			nr.setMsg("没有相关数据");
			return nr;
		}
		List<NoteBook> list = bookDao.findBooksByUser(userId);
		nr.setStatus(0);
		nr.setMsg("查询成功");
		nr.setData(list);
		return nr;
	}


	public NoteResult addnotebook(String bookname, String userId) {
		NoteResult nr = new NoteResult();
		//参数数据检测
		if(userId==null || "".equals(userId.trim())){
			nr.setStatus(2);
			nr.setMsg("用户ID为空");
			return nr;
		}
		if(bookname==null || "".equals(bookname.trim())){
			nr.setStatus(1);
			nr.setMsg("笔记本名为空");
			return nr;
		}
		Map<String ,Object> map = new HashMap<String,Object>();
		map.put("bookname", bookname);
		map.put("userId",userId);
		NoteBook getbook = bookDao.findByNameAndUserId(map);
		if(getbook!=null){
			nr.setStatus(1);
			nr.setMsg("该书本已存在");
		}
	
		NoteBook notebook = new NoteBook();
		notebook.setCn_notebook_type_id("1");
		notebook.setCn_user_id(userId);
		notebook.setCn_notebook_name(bookname);
		String bookid = NoteUtil.createId();
		notebook.setCn_notebook_id(bookid);
		Timestamp time = 
			new Timestamp(System.currentTimeMillis());
		notebook.setCn_notebook_desc("");
		notebook.setCn_notebook_createtime(time);
		bookDao.addBook(notebook);
		nr.setStatus(0);
		nr.setMsg("创建书本成功");
		nr.setData(notebook.getCn_notebook_id());
		return nr;
	}}