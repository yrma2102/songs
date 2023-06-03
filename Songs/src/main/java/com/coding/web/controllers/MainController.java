package com.coding.web.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.coding.web.models.SongModel;
import com.coding.web.models.SongsUsersModel;
import com.coding.web.models.UserModel;
import com.coding.web.services.SongService;
import com.coding.web.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class MainController {
	@Autowired
	private UserService userServ;
	@Autowired
	private SongService songServ;


	@GetMapping("/home")
	public String index(HttpSession sesion, Model viewModel) {
		// Validar si el usuario tiene la sesion activa
		Long userLog = (Long) sesion.getAttribute("userId");
		if (userLog == null) {
			return "Loginreg.jsp";
		}
		UserModel user = userServ.findById(userLog);
		viewModel.addAttribute("user", user);
		viewModel.addAttribute("songs", songServ.todosSongs( ));
		return "Home.jsp";
	}
	
	@GetMapping("/songs/new")
	public String formularioNuevaSong(@ModelAttribute("song") SongModel song, HttpSession sesion,
			Model viewModel) {
		Long userId = (Long) sesion.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		}
		UserModel usuarioLog = userServ.findById(userId);
		viewModel.addAttribute("usuario", usuarioLog);
		return "NewSong.jsp";
	}

	@PostMapping("/songs/new")
	public String crearSong(@Valid @ModelAttribute("song") SongModel song, BindingResult resultado,
			HttpSession sesion, Model viewModel) {
		Long userId = (Long) sesion.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		}
		UserModel usuarioLog = userServ.findById(userId);
		if (resultado.hasErrors()) {
			viewModel.addAttribute("usuario", usuarioLog);
			return "NewSong.jsp";
		}
		song.setPropietario(usuarioLog);
		songServ.crearSong(song);
		return "redirect:/home";
	}
	@GetMapping("/songs/{idSong}")
	public String mostrarSong(@PathVariable("idSong") Long idSong,HttpSession sesion,Model viewModel) {
		Long userId = (Long) sesion.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		}
		UserModel usuarioLog = userServ.findById(userId);
		viewModel.addAttribute("usuario", usuarioLog);
		SongModel song = songServ.getSong(idSong);

		viewModel.addAttribute("song", song);

		return "Detail.jsp";
	}
	
	@GetMapping("/songs/{idSong}/edit")
	public String editSong(@PathVariable("idSong") Long idSong,HttpSession sesion,@ModelAttribute("song") SongModel song,
			Model viewModel) {
		Long userId = (Long) sesion.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		}
		SongModel song_ = songServ.getSong(idSong);
		viewModel.addAttribute("old", song_.getLyrics());
		song_.setLyrics("");
		viewModel.addAttribute("song", song_);
		if(song_.getPropietario().getId() == userId) {
			viewModel.addAttribute("succesDelete", "Delete"); 	
		}
		return "Edit.jsp";
	}
	
	@PostMapping("/songs/edit")
	public String editarSong(@Valid @ModelAttribute("song") SongModel song, BindingResult resultado,
			HttpSession sesion, Model viewModel) {
		Long userId = (Long) sesion.getAttribute("userId");
		if (userId == null) {
			return "redirect:/";
		}
		UserModel usuarioLog = userServ.findById(userId);
		if (resultado.hasErrors()) {
			viewModel.addAttribute("usuario", usuarioLog);
			return "Edit.jsp";
		}
		SongModel song_ = songServ.getSong(song.getId());
		
		List <UserModel> lis  = song_.getUsers();
		lis.add(usuarioLog);
		song.setUsers(lis);
		song.setPropietario(song_.getPropietario());
		song.setUpdatedAt(new Date());
		song.setLyrics(song_.getLyrics()+ song.getLyrics());
		SongsUsersModel union = new SongsUsersModel();
		union.setSong(song_);
		union.setUser(usuarioLog);
		songServ.crearVinculo(union);
		
		songServ.actaulizarSong(song);
		

		//songServ.inserData(song_.getId(), userId);
		return "redirect:/home";
	}
	
		@GetMapping("/songs/delete/{id}")
		public String deleteSong(@PathVariable("id") Long id, HttpSession sesion) {
			Long userLog = (Long) sesion.getAttribute("userId");
			if (userLog == null) {
				return "redirect:/";
			}

			SongModel song = songServ.getSong(id);
			if(userLog == song.getPropietario().getId()) {
				songServ.deleteSong(id);
				return"redirect:/home";
			}
			return"redirect:/home";
		}
	

}
