package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.PhoneDao;
import com.javaex.vo.PersonVo;

@Controller
@RequestMapping("/phone")
public class PhoneController {
	//필드
	//생성자
	//g/s
	//일반메소드
	
	
	//전화번호부 목록
	@RequestMapping("/list" )
	public String list(Model model) {
		System.out.println("/phone/list");
		
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> pList = phoneDao.getPersonList();
		
		
		model.addAttribute("pList",pList);
		
		return "/WEB-INF/view/list.jsp";
	}
	
	//내용추가 폼 이동
	@RequestMapping("/writeForm" )
	public String writeForm() {
		
		return "/WEB-INF/view/writeForm.jsp";
	}
	
	
//	@RequestMapping("/write" )
//	public String write(@RequestParam("name") String name,
//						@RequestParam("hp") String hp,
//						@RequestParam( value = "company", required=false, defaultValue="000") String company) {
//		System.out.println("/phone/write");		
//		System.out.println(name+","+hp+","+company);	
//		return "";
//	}
	
	//내용추가
	@RequestMapping("/write" )
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("/phone/write");	
		
		System.out.println(personVo.toString());
		
		PhoneDao pDao = new PhoneDao();
		pDao.personInsert(personVo);
		return "redirect:/phone/list";
	}
	
	@RequestMapping("/updateForm" )
	public String updateForm(Model model,@RequestParam("personId") int personId) {
		System.out.println("/phone/updateForm");
		
		PhoneDao pDao = new PhoneDao();
		PersonVo vo = pDao.getPerson(personId);
		
		model.addAttribute("uVo",vo);
		
		return"/WEB-INF/view/updateForm.jsp";
		
	}
	
	@RequestMapping("/update" )
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("/phone/update");
		
		System.out.println(personVo.toString());
		
		PhoneDao pDao = new PhoneDao();
		pDao.personUpdate(personVo);
		
		return "redirect:/phone/list";
		
	}
	
	@RequestMapping("/delete" )
	public String delete(@RequestParam("personId") int personId) {
		
		PhoneDao pDao = new PhoneDao();
		pDao.personDelete(personId);
		
		
		
		return "redirect:/phone/list";
		
	}
	
	
	
	
	
}
