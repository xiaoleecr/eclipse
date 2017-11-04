package org.tarena.note.Service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.tarena.note.dao.NoteMapperDao;
import org.tarena.note.entity.Note;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;
import org.tarena.note.util.NoteUtil;

@Service
public class NoteServiceImpl implements NoteService{
	@Resource
	private NoteMapperDao noteDao;
	
	public NoteResult loadNotes(String bookId) {
		NoteResult result = new NoteResult();
		if(bookId != null && !"".equals(bookId.trim())){
			List<Map<String, Object>> list = 
				noteDao.findNotesByBookId(bookId);
			result.setData(list);
		}
		result.setStatus(0);
		result.setMsg("查询成功");
		return result;
	}
	
	public NoteResult addNote(Note note) {
		NoteResult result = new NoteResult();
		// 笔记名,用户ID,笔记本ID请求传递过来
		//TODO 笔记名称是否为空,是否重名
		String noteId = NoteUtil.createId();
		note.setCn_note_id(noteId);//设置笔记ID
		note.setCn_note_status_id("1");//设置normal状态
		note.setCn_note_type_id("1");//设置normal类型
		note.setCn_note_body("");//设置内容为空
		long time = System.currentTimeMillis();
		note.setCn_note_create_time(time);//设置创建时间
		note.setCn_note_last_modify_time(time);//设置最后修改时间
		noteDao.save(note);//保存操作
		result.setStatus(0);
		result.setMsg("创建笔记成功");
		result.setData(note.getCn_note_id());//返回noteId值
		return result;
	}

}
