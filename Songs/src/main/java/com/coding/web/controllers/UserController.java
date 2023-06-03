package com.coding.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.coding.web.models.LoginModel;
import com.coding.web.models.UserModel;
import com.coding.web.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String index(Model viewModel, HttpSession sesion) {
		Long userId = (Long) sesion.getAttribute("userId");
		if (userId != null) {
			return "redirect:/home";
		}
		viewModel.addAttribute("user", new UserModel());
		viewModel.addAttribute("login", new LoginModel());
		return "Loginreg.jsp";
	}
	
	@PostMapping("/registration")
	public String register(@Valid @ModelAttribute("user") UserModel usuario, 
			BindingResult resultado, Model viewModel) {
		if(resultado.hasErrors()) {
			viewModel.addAttribute("login", new LoginModel());
			return "Loginreg.jsp";
		}
		
		UserModel usuarioRegistrado = userService.crearUsuario(usuario, resultado);
		viewModel.addAttribute("login", new LoginModel());
		if(usuarioRegistrado != null) {
			viewModel.addAttribute("succesRegister", "Gracias por registrarte, por favor login"); 	
		}
		return "Loginreg.jsp";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute("login") LoginModel loginuser, 
			BindingResult resultado, Model viewModel, HttpSession sesion) {
		if(resultado.hasErrors()) {
			viewModel.addAttribute("user", new UserModel());
			return "Loginreg.jsp";
		}
		if(userService.authenticateUser(loginuser.getEmail(), 
				loginuser.getPassword(), resultado)) {
			UserModel usuarioLog = userService.findByEmail(loginuser.getEmail());
			sesion.setAttribute("userId",  usuarioLog.getId());
			return "redirect:home";
		}else {
			viewModel.addAttribute("user", new UserModel());
			return "Loginreg.jsp";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession sesion) {
		sesion.setAttribute("userId", null);
		return"redirect:/";
	}
}
