package com.coding.web.services;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import com.coding.web.models.UserModel;
import com.coding.web.repositories.IUserRepository;

@Service
public class UserService {
	@Autowired
	IUserRepository userRepo;

	//CREATE
	public UserModel crearUsuario(UserModel newUser, BindingResult result) {
		UserModel userRegistrado = userRepo.findByEmail(newUser.getEmail());
		if (userRegistrado != null) {
			result.rejectValue("email", "Matches", "correo electrónico ya existe");
		}
		if (!newUser.getPassword().equals(newUser.getPasswordConfirmation())) {
			result.rejectValue("password", "Matches", "contraseñas no coinciden");
		}
		if (result.hasErrors()) {
			return null;
		}
		String hashed = BCrypt.hashpw(newUser.getPassword(), BCrypt.gensalt());
		newUser.setPassword(hashed);
		return userRepo.save(newUser);
	}
	
	//READ
	public UserModel findByEmail(String email) {
		return userRepo.findByEmail(email);
	}
	public UserModel findById(Long userId) {
		return userRepo.findById(userId).orElse(null);
	}
	
	public boolean authenticateUser(String email, String password, BindingResult result) {
		UserModel user = userRepo.findByEmail(email);
		if (user == null) {
			result.rejectValue("email", "Matches", "correo electronico no valido");
			return false;
		} else {
			// si el password coincide devolvemos true, sino, devolvemos false
			if (BCrypt.checkpw(password, user.getPassword())) {
				return true;
			} else {
				result.rejectValue("password", "Matches", "contraseña no valida");
				return false;
			}
		}
	}

}
