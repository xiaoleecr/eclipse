package org.tarena.note.controller;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tarena.note.entity.NoteResult;
import org.tarena.note.entity.User;
import org.tarena.note.service.UserService;

@Controller
@RequestMapping("/user")
public class UserServiceController {
   @Resource
   private UserService us;
	
	public NoteResult checkLogin(HttpServletRequest request) throws Exception{
		request.setCharacterEncoding("utf-8");
		NoteResult nr = new NoteResult();
		String author = request.getHeader("Authorization");
		nr = us.checkLogin(author);
		return nr;
	}
	
	@RequestMapping("/checklogin.do")
	@ResponseBody
	public NoteResult execute(
			HttpServletRequest request) throws Exception{
		request.setCharacterEncoding("utf-8");
	String author = request.getHeader("Authorization");
	NoteResult result = us.checkLogin(author);
	return result;
	}
	
	

	@RequestMapping("/checkregist.do")
	@ResponseBody
	public NoteResult checkregist(User user){
		NoteResult result = us.checkregist(user);
		return result;
	} 
}
