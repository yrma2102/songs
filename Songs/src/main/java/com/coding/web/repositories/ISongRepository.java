package com.coding.web.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.coding.web.models.SongModel;

public interface ISongRepository  extends CrudRepository<SongModel, Long>{
	List<SongModel> findAll();
	
}
