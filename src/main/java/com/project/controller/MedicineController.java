package com.project.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.model.MedicineBean;
import com.project.model.MedicineFunction;
import com.project.service.MedicineDao;


@Controller
public class MedicineController {
	
	@Autowired
	MedicineDao md;
	
	@Autowired
	MedicineFunction mf;
	
	
	@RequestMapping("/view")
	public String view()
	{
		return "userHome";
	}
	
	@RequestMapping("/viewmedicine")
	public String viewmedicine()
	{
		return "search";
	}
	
	@RequestMapping("/medicine")
	public String medicine(String type,String name,HttpSession session,Model m)
	{
		session.setAttribute(type, type);
		session.setAttribute(name, name);
		 	
		List <MedicineBean> ls=mf.retrivedetails(type, name);
		
		m.addAttribute("details", ls);
		
		//System.out.println(type+" "+name);
		return "searchedmedicine";
	}
	
	

}
