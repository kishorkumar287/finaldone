package com.project.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.model.AdminBean;
import com.project.model.LoginBean;
import com.project.model.RecoveryBean;

import com.project.service.AdminDao;
import com.project.service.AdminFunction;
import com.project.service.RecoveryDao;
import com.project.service.Validation;

@Controller
public class MainController {
	@Autowired
	AdminDao adminDao;

	@Autowired
	AdminFunction adminFunction;
	@Autowired
	RecoveryDao recoveryDao;
	
	@RequestMapping("/")
	public String chooseRole(HttpSession session) {
		String page = "choose";
		session.setAttribute("add", 0);
		return page;

	}
	@RequestMapping("/logout")
	public String logout(HttpSession session)
	{
		session.setAttribute("add",0);
		session.setAttribute("id", null);
		session.setAttribute("role", null);
		session.setAttribute("addm", null);
		
		return "choose";
	}
	
	@RequestMapping("/log")
	public String login(String role, @ModelAttribute("login") LoginBean login, HttpSession session, Model m) {
		String page = "login";
		m.addAttribute("err", 0);
		session.setAttribute("role", role);
//		System.out.println(role);
		return page;

	}

	@RequestMapping("/sign")
	public String signup(@ModelAttribute("signup") AdminBean ab) {
		String page = "signup";
		return page;
	}

	@RequestMapping("/home")
	public String home(@Valid @ModelAttribute("signup") AdminBean adminBean, BindingResult br, HttpSession session,
			Model m) {

		String page = "recovery";

		m.addAttribute("rb", new RecoveryBean());

		if (br.hasErrors()) {

			return "signup";

		} else {

			String role = (String) session.getAttribute("role");

			if (role.equals("ad")) {
				AdminBean ab = adminDao.findAdmin(adminBean.getEmailId());
				if (ab == null) {
					adminBean.setRole(1);
					adminBean.setPassword(adminFunction.encryption(adminBean.getPassword()));
					session.setAttribute("sign", adminBean);

				} else {

					br.addError(new FieldError("email", "emailId", "Email Already Exist"));

					return "signup";
				}
			} else {
				AdminBean ab = adminDao.findAdmin(adminBean.getEmailId());

				if (ab == null) {
					adminBean.setPassword(adminFunction.encryption(adminBean.getPassword()));
					adminBean.setRole(0);
					session.setAttribute("sign", adminBean);

				} else {
					br.addError(new FieldError("email", "emailId", "Email Already Exist"));
					return "signup";
				}

			}
			return page;
		}
	}

	@RequestMapping("/main")
	public String mainPage(@Valid @ModelAttribute("login") LoginBean login, BindingResult br, HttpSession session,
			Model model) {

		String role = (String) session.getAttribute("role");

		String page = "admin";
		session.setAttribute("addm", 0);
		if (br.hasErrors()) {
			model.addAttribute("err", 0);
			session.setAttribute("add", 0);
			return "login";
		}

		if (role.equals("us")) {

			AdminBean ub = adminDao.validateAdmin(login.getEmail().trim(),
					adminFunction.encryption(login.getPassword().trim()));
			if (ub == null || ub.getRole()==1) {
				model.addAttribute("err", 1);
//				System.out.println(login.getEmail()+" "+adminFunction.encryption(login.getPassword().trim()));
				return "login";
			}

			Optional<AdminBean> o = adminDao.findById(login.getEmail().trim());
			if (o.isPresent())
				session.setAttribute("id", o.get());

			page = "userHome";
		} else {
			AdminBean ab = adminDao.validateAdmin(login.getEmail().trim(),
					adminFunction.encryption(login.getPassword().trim()));
			if (ab == null || ab.getRole()==0) {
				model.addAttribute("err", 1);
//				System.out.println(1);
				session.setAttribute("add", 0);
				return "login";

			}
			Optional<AdminBean> o = adminDao.findById(login.getEmail().trim());
			if (o.isPresent())
				session.setAttribute("id", o.get());

		}
		session.setAttribute("add", 0);
		return page;

	}// main

	@PostMapping("/recoverymap")
	public String recovery(@Valid @ModelAttribute("rb") RecoveryBean recoveryBean, BindingResult bindingResult,
			HttpSession session, Model model) {
		String page = "recovery";
		if (bindingResult.hasErrors()) {
			return "recovery";
		}
		String role = (String) session.getAttribute("role");
		adminDao.save((AdminBean) session.getAttribute("sign"));
		recoveryBean.setDesgination(role);
		recoveryDao.save(recoveryBean);
		session.setAttribute("sign", null);
		model.addAttribute("err", 0);
		model.addAttribute("login", new LoginBean());
		session.setAttribute("add", 1);

		return "login";
	}

}
