package com.anis.project.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name="consultations")
public class Consultation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 3, max = 30, message = "status must be between 3 and 30 characters")
	private String status;
	
	private String link;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private Date startTime;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
	private Date endTime;
	
	@NotEmpty(message = "concerns are required!")
	@Size(min = 3, max = 30, message = "concerns must be between 3 and 30 characters")
	private String concerns;
	
	@Size(min = 3, max = 30, message = "diseases must be between 3 and 30 characters")
	private String diseases;
	
    private String previous;
    
//    private Long idDoctor;
    
//    private Long idPatient;

    
//    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="doctor_id")
    private Doctor doctor;
    
//    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="patient_id")
    private Patient patient;
  
    // Note One To Many
    @OneToMany(mappedBy="consultation", fetch=FetchType.LAZY)
    private List<Note> notes;
	
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
	
	public Consultation () {
        // Set default value for status
        this.status = "pending";


	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getConcerns() {
		return concerns;
	}

	public void setConcerns(String concerns) {
		this.concerns = concerns;
	}

	public String getDiseases() {
		return diseases;
	}

	public void setDiseases(String diseases) {
		this.diseases = diseases;
	}
	
	
//	public Long getIdDoctor() {
//		return idDoctor;
//	}
//
//	public void setIdDoctor(Long idDoctor) {
//		this.idDoctor = idDoctor;
//	}
//
//	public Long getIdPatient() {
//		return idPatient;
//	}
//
//	public void setIdPatient(Long idPatient) {
//		this.idPatient = idPatient;
//	}

	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public Doctor getDoctor() {
		return doctor;
	}
	
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}
	@JsonIgnore
	public List<Note> getNotes() {
		return notes;
	}

	public void setNotes(List<Note> notes) {
		this.notes = notes;
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
