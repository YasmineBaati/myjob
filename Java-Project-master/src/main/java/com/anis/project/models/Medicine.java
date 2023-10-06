package com.anis.project.models;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="medicines")
public class Medicine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "medicine is required!")
	@Size(min = 3, max = 30, message = "medicine must be between 3 and 30 characters")
	private String medicineName;
	
	@NotNull(message = "morning is required.")
	private Integer morning;
	
	@NotNull(message = "afternoon is required.")
	private Integer afternoon;
	
	@NotNull(message = "night is required.")
	private Integer night;
	
	// Prescription Many To One
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="prescription_id")
	private Prescription prescription;
	
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
	
	public Medicine () {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public Integer getMorning() {
		return morning;
	}

	public void setMorning(Integer morning) {
		this.morning = morning;
	}

	public Integer getAfternoon() {
		return afternoon;
	}

	public void setAfternoon(Integer afternoon) {
		this.afternoon = afternoon;
	}

	public Integer getNight() {
		return night;
	}

	public void setNight(Integer night) {
		this.night = night;
	}
	@JsonIgnore
	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
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
	
}
