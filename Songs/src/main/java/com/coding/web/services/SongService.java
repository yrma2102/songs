package com.coding.web.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.web.models.SongModel;
import com.coding.web.models.SongsUsersModel;
import com.coding.web.repositories.ISongRepository;
import com.coding.web.repositories.ISongsUsersRepo;
@Service
public class SongService {
	@Autowired 
	private ISongRepository songRepo;
	@Autowired 
	private ISongsUsersRepo unionRepo;

	@Autowired
	private ISongsUsersRepo songsUsersRepo;
	// SERVICIOS PARA song
		public SongModel crearSong(SongModel song) {
			return songRepo.save(song);
		}
		public SongModel actaulizarSong(SongModel song) {
			SongModel ac = new SongModel();
			ac.setTitle(song.getTitle());
			ac.setLyrics(song.getLyrics());
			ac.setGenre(song.getGenre());
			ac.setUpdatedAt(song.getUpdatedAt());
			return songRepo.save(song);
		}
		public List<SongModel> todosSongs(){
			return songRepo.findAll();
		}
		public SongModel getSong(Long idSong) {
			return songRepo.findById(idSong).orElse(null);
		}
		
		//SERVICIOS TABLA INTERMEDIA
		public SongsUsersModel crearVinculo(SongsUsersModel songsusersModel) {
			return songsUsersRepo.save(songsusersModel);
		}
		
//		public void inserData(Long id_song, Long user_id) {
//			unionRepo.insertData(id_song, user_id);
//		}
		public List<SongsUsersModel> contribuidores(SongModel m){
			return unionRepo.findBySong(m);
		}
		
		public void deleteSong(Long id) {
			songRepo.deleteById(id);
			
		}

}
