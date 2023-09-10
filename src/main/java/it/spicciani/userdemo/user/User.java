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

@Entity
//
@Table(uniqueConstraints = { @UniqueConstraint(name = "UserUniqueEmail", columnNames = { "email" }) })
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max=64)
	@Column(nullable = false)
	private String name;
	
	@NotNull
	@Size(max=64)
	@Column(nullable = false)
	private String surname;
	
	@Past
	@NotNull
	private LocalDate birthDate;
	
	@NotNull
	@Email
	private String email;
	
	@Column(columnDefinition = "varchar(255) default ''")
	private String address;
	
	@Column(columnDefinition = "varchar(255) default ''")
	private String note;
	
	
	
	public User() {
		super();
	}

	public User(long id, @NotNull @Size(max = 64) String name, @NotNull @Size(max = 64) String surname,
			@Past @NotNull LocalDate birthDate, @NotNull String email, String address, String note) {
		this(name, surname, birthDate, email, address, note);
		this.id = id;
	}
	
	public User(@NotNull @Size(max = 64) String name, @NotNull @Size(max = 64) String surname,
			@Past @NotNull LocalDate birthDate, @NotNull String email, String address, String note) {
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
