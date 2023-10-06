package com.anis.project.models;

public class MedicineRequest {
    private String medicineName;
    private Integer morning;
    private Integer afternoon;
    private Integer night;
	public MedicineRequest() {

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
    
    
}
