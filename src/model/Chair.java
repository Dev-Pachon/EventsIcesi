package model;

public class Chair {
	//Constants
	public static final String OPE = "Operative";
	public static final String DEF = "Defective";
	//Attributes
	private String status;
	private String description;
	//Constructor
	public Chair() {
		 status = OPE;
		 description = ""; 
	}
	//Getters
	public String getStatus() {
		return status;
	}
	
	public String getDescription() {
		return description;
	}
	//Setters
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
}
