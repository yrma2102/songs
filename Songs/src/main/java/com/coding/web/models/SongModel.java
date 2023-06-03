package com.coding.web.models;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@DynamicUpdate
@Table(name = "songs")
public class SongModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Por favor ingresa el nombre")
	private String title;
	@NotBlank(message="Por favor ingresa el genero")
	private String genre;
	
	@NotBlank(message="Por favor ingresa lyrics")
	private String lyrics;
	
	@Column(updatable = false)
	private Date createdAt;
	private Date updatedAt;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id_p")
    private UserModel propietario;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "songsusers", 
	joinColumns = @JoinColumn(name = "song_id"), 
	inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<UserModel> users;

	public SongModel() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<UserModel> getUsers() {
		return users;
	}

	public void setUsers(List<UserModel> users) {
		this.users = users;
	}
	public UserModel getPropietario() {
		return propietario;
	}

	public void setPropietario(UserModel propietario) {
		this.propietario = propietario;
	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PostPersist
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

}
