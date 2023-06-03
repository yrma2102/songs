package com.coding.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.coding.web.models.SongModel;
import com.coding.web.models.SongsUsersModel;
import com.coding.web.models.UserModel;

public interface ISongsUsersRepo extends CrudRepository<SongsUsersModel, Long>{
		List<SongsUsersModel> findAll();

		List<SongsUsersModel> findAllByUser(UserModel usuario);
		List<SongsUsersModel> findBySong(SongModel song);
		/*
		 * @Modifying
		 * 
		 * @Query("insert into Person (song_id,user_id) select :id_song,:id_user from Dual"
		 * ) public void insertData(Long id_song,Long id_user);
		 */
}
