package com.coding.web.models;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="songsusers")
public class SongsUsersModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="song_id")
    private SongModel song;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private UserModel user;
    
    public SongsUsersModel() {
        
    }
   


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	

	public SongModel getSong() {
		return song;
	}



	public void setSong(SongModel song) {
		this.song = song;
	}



	public UserModel getUser() {
		return user;
	}



	public void setUser(UserModel user) {
		this.user = user;
	}

}
