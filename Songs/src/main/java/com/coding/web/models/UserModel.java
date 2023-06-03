package com.coding.web.models;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
@Entity
@Table(name = "users")
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "nombre no puede estar vacío")
	@Size(min = 3, max = 30, message = "Nombre debe contener entre 3 y 30 caracteres")
	private String firstName;

	@Email(message = "email no válido")
	@NotBlank(message = "campo email no puede estar vacío")
	private String email;

	@NotBlank(message = "por favor ingresa una contraseña")
	@Size(min = 8, max = 64, message = "password debe tener entre 8 y 20 caracteres")
	private String password;

	/* Transient evita que el campo que tenga eso se guarde en la DB */
	@Transient
	@NotBlank(message = "confirma la contraseña por favor")
	private String passwordConfirmation;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	@OneToMany(mappedBy="propietario", fetch = FetchType.LAZY)
    private List<SongModel> songs_l;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "songsusers", 
	joinColumns = @JoinColumn(name = "user_id"), 
	inverseJoinColumns = @JoinColumn(name = "song_id"))
	private List<SongModel> songs;

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirmation() {
		return passwordConfirmation;
	}

	public void setPasswordConfirmation(String passwordConfirmation) {
		this.passwordConfirmation = passwordConfirmation;
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


	public List<SongModel> getSongs() {
		return songs;
	}

	public void setPiscinas(List<SongModel> songs) {
		this.songs = songs;
	}

	public UserModel() {

	}

	public UserModel(String firstName, String email, String password) {
		this.firstName = firstName;
		this.email = email;
		this.password = password;
	}

}
