package org.tarena.note.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteBookService;

@Controller
@RequestMapping("/notebook")
public class BookController {
	@Resource
	private NoteBookService bookService;
	
	@RequestMapping("/loadbooks.do")
	@ResponseBody
	public NoteResult loadbooks(String userId){
		NoteResult result = 
			bookService.loadBooks(userId);
		return result;
	}
	
	
	@RequestMapping("/addnotebook.do")
	@ResponseBody
	public NoteResult  addnotebook(HttpServletRequest request){
		String bookname = request.getParameter("bookname");
		String userId = request.getParameter("userId");
		NoteResult result = 
			bookService.addnotebook(bookname,userId);
	return result;	
	}
	
}