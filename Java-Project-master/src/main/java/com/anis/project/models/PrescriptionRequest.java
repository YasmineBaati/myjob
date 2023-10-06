package com.anis.project.models;

import java.util.List;

public class PrescriptionRequest {
	
    private List<MedicineRequest> medicines;

	public PrescriptionRequest() {
		
	}

	public List<MedicineRequest> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<MedicineRequest> medicines) {
		this.medicines = medicines;
	}
	
    

}
