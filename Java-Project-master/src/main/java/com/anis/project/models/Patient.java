package com.anis.project.models;

import java.util.Date;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name="patients")
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "first name is required!")
	@Size(min = 3, max = 30, message = "First name must be between 3 and 30 characters")
	private String firstName;
	
	private String image;
	
	@NotEmpty(message = "last name is required!")
	@Size(min = 3, max = 30, message = "First name must be between 3 and 30 characters")
	private String lastName;
	
	@NotEmpty(message = "Email is required!")
	@Email(message = "Please enter a valid email!")
	private String email;
	
	@NotEmpty(message = "Password is required!")
	@Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters")
	private String password;
	
	@Transient
	@NotEmpty(message = "Confirm Password is required!")
	@Size(min = 8, max = 128, message = "Confirm Password must be between 8 and 128 characters")
	private String confirmPassword;
	
	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;
	
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}
	
	// Review One To Many 
//	@JsonBackReference
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
	private List<Review> patientReviews;
	
	// Consultation One To Many 
//	@JsonBackReference
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)

	private List<Consultation> patientConsultations;
	
	// Prescription One To Many 
	
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
//	@JsonBackReference

	private List<Prescription> patientPrescriptions;
	
	// Discussion One To Many 
	
	@OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
	private List<Discussion> patientDiscussions;

	@OneToOne(mappedBy = "patient")
    private PatientInformation patientInformation;
	
	public Patient() {
		
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirm) {
		this.confirmPassword = confirm;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	public PatientInformation getPatientInformation() {
		return patientInformation;
	}
	public void setPatientInformation(PatientInformation patientInformation) {
		this.patientInformation = patientInformation;
	}
	@JsonIgnore
	public List<Consultation> getPatientConsultations() {
		return patientConsultations;
	}
	public void setPatientConsultations(List<Consultation> patientConsultations) {
		this.patientConsultations = patientConsultations;
	}
	@JsonIgnore
	public List<Prescription> getPatientPrescriptions() {
		return patientPrescriptions;
	}

	public void setPatientPrescriptions(List<Prescription> patientPrescriptions) {
		this.patientPrescriptions = patientPrescriptions;
	}
	@JsonIgnore
	public List<Review> getPatientReviews() {
		return patientReviews;
	}

	public void setPatientReviews(List<Review> patientReviews) {
		this.patientReviews = patientReviews;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Discussion> getPatientDiscussions() {
		return patientDiscussions;
	}

	public void setPatientDiscussions(List<Discussion> patientDiscussions) {
		this.patientDiscussions = patientDiscussions;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
}
