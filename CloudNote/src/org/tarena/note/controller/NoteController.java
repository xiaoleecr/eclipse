package org.tarena.note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.service.NoteService;


@Controller
@RequestMapping("/note")
public class NoteController {

	@Resource
	private NoteService nsi;
	@RequestMapping("/showNotes.do")
	@ResponseBody
	public NoteResult findNotes(String bookId){
		NoteResult nr = new NoteResult();
		nr = nsi.loadNotes(bookId);
		return nr ;
	}
	
}
