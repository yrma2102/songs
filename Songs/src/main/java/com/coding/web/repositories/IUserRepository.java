package com.coding.web.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.coding.web.models.SongModel;
import com.coding.web.models.UserModel;

public interface IUserRepository  extends CrudRepository<UserModel, Long> {
	List<UserModel> findAll();
	List<UserModel> findBySongsNotContains(SongModel song);
	UserModel findByEmail(String email);

}
