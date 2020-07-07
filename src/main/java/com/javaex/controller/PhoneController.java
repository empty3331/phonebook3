package com.javaex.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	PhoneDao phoneDao;
	
	
	//전화번호부 목록
	@RequestMapping("/list" )
	public String list(Model model) {
		System.out.println("/phone/list");
				
		List<PersonVo> pList = phoneDao.getPersonList();
		
		
		model.addAttribute("pList",pList);
		
		return "list";
	}
	
	

	//내용추가 폼 이동
	@RequestMapping("/writeForm" )
	public String writeForm() {
		System.out.println("/phone/writeForm");
		return "writeForm";
	}
//	
//	
////	@RequestMapping("/write" )
////	public String write(@RequestParam("name") String name,
////						@RequestParam("hp") String hp,
////						@RequestParam( value = "company", required=false, defaultValue="000") String company) {
////		System.out.println("/phone/write");		
////		System.out.println(name+","+hp+","+company);	
////		return "";
////	}
//	
	//내용추가
	@RequestMapping("/write" )
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("/phone/write");	
		
		
		phoneDao.personInsert(personVo);
		return "redirect:/phone/list";
	}
	
	@RequestMapping("/write2" )
	public String write2(@RequestParam("name") String name,
			             @RequestParam("hp") String hp, @RequestParam("company") String company) {
		System.out.println("/phone/write2");	
		
		
		phoneDao.personInsert2(name,hp,company);
		return "redirect:/phone/list";
	}
	
	
	
	
	@RequestMapping("/updateForm" )
	public String updateForm(Model model,@RequestParam("personId") int personId) {
		System.out.println("/phone/updateForm");
		PersonVo vo = phoneDao.getPerson(personId);
		model.addAttribute("uVo",vo);
		return"updateForm";
	}
	
	@RequestMapping("/updateForm2" )
	public String updateForm2(Model model,@RequestParam("personId") int personId) {
		System.out.println("/phone/updateForm2");
		Map<String,Object> personMap = phoneDao.getPerson2(personId);
		model.addAttribute("personMap", personMap);
		return"updateForm2";
	}
	
//	
//	
//	/*
//	@RequestMapping("/updateForm/{personId}" )
//	public String updateForm(Model model,@PathVariable("personId") int personId) {
//		System.out.println("/phone/updateForm");
//		
//		PhoneDao pDao = new PhoneDao();
//		PersonVo vo = pDao.getPerson(personId);
//		
//		model.addAttribute("uVo",vo);
//		
//		return"/WEB-INF/view/updateForm.jsp"; 
//	 */
//	
	@RequestMapping("/update" )
	public String update(@ModelAttribute PersonVo personVo) {
		System.out.println("/phone/update");		
		System.out.println(personVo.toString());
		
		phoneDao.personUpdate(personVo);		
		return "redirect:/phone/list";
		
	}
	
	@RequestMapping("/delete" )
	public String delete(@RequestParam("personId") int personId) {
		System.out.println("/delete");
		phoneDao.personDelete(personId);
		
		return "redirect:/phone/list";
		
	}
	
	
}
