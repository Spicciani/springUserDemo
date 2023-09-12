package it.spicciani.userdemo.user;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(name = "UserUniqueEmail", columnNames = { "email" }) })
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotBlank(message = "Name can't be null or empty")
	@Size(max=64, message = "Name must be less then 64 characters")
	@Column(nullable = false)
	private String name;
	
	@NotBlank(message = "Surname can't be null or empty")
	@Size(max=64, message = "Surname must be less then 64 characters")
	@Column(nullable = false)
	private String surname;
	
	@Past(message = "Birth date must be in the past")
	@NotNull(message = "Birth date can't be null")
	private LocalDate birthDate;
	
	@NotBlank(message = "Email can't be null or empty")
	@Email(message = "Email must be a valid email address")
	@Column(nullable = false)
	private String email;
	
	@Size(max=255, message = "Address must be less then 255 characters")
	@Column(columnDefinition = "varchar(255) default ''")
	private String address;
	
	@Size(max=255, message = "Note must be less then 255 characters")
	@Column(columnDefinition = "varchar(255) default ''")
	private String note;
	
	
	
	public User() {
		super();
	}

	
	
	public User(long id,
			@NotBlank @Size(max = 64) String name,
			@NotBlank @Size(max = 64) String surname,
			@Past @NotNull LocalDate birthDate,
			@NotBlank @Email String email,
			@Size(max = 255) String address,
			@Size(max = 255) String note) {
		super();
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.email = email;
		this.address = address;
		this.note = note;
	}



	public User(@NotBlank @Size(max = 64) String name,
			@NotBlank @Size(max = 64) String surname,
			@Past @NotNull LocalDate birthDate,
			@NotBlank @Email String email,
			@Size(max = 255) String address,
			@Size(max = 255) String note) {
		super();
		this.name = name;
		this.surname = surname;
		this.birthDate = birthDate;
		this.email = email;
		this.address = address;
		this.note = note;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public LocalDate getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", surname=" + surname + ", birthDate=" + birthDate + ", email="
				+ email + ", address=" + address + ", note=" + note + "]";
	}
	
	
	

}
