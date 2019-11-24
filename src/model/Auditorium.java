package model;

public class Auditorium{
	
	//Constants
	public static final String AVA = "Available";
	public static final String OCC = "Occupied";
	
	//Attributes
	private String name;
	private String location;
	private String status;
	private char[] nameRows;
	
	//Relation
	private Chair [][] chairs;
	
	//Constructor
	public Auditorium(String name, String location){
		this.name = name;
		this.location = location;
		status = AVA;
	}
	
	//Getters
	public String getName(){
		return name;
	}
	
	public String getLocation() {
		return location;
	}
	
	public String getStatus() {
		return status;
	}
	
	//Setter
	public void setStatus(String status) {
		this.status = status;
	}
	
	//Methods
	
	/**
	 * This method create a matriz of chairs depending of the number of rows and the number of chairs per row, the rows where are  
	 * @param rows is a integer that indicates the number of rows
	 * @param maxColums is a integer that indicates the maximum number of columns
	 * @param colums is a array with the number of chairs per row
	 */
	public void createChairs(int rows, int maxColums, int [] colums) {
		chairs = new Chair[rows][maxColums];
		
		for(int i = 0; i < rows; i++) {
			for(int j = 0; j < colums[i];j++) {
				chairs[i][j] = new Chair();
			}
		}
		
		nameRows = new char[rows];
		
		for (int i = 0; i < nameRows.length;i++) {
			nameRows[i] = (char) (65+i);
		}
	}
	/**
	 * This method set the status of a chair to defective, and set a description about the defective chair</br>
	 * <p>Pre:</p> The array nameRows and the matriz chairs need to was created before, nameRows&&chairs != null 
	 * @param row is a char that indicates a row in the auditorium
	 * @param chair is a integer that indicates the defective chair in the row
	 * @param description is a string that contains the description about the defective chair
	 * @return a string that indicates if the chair was reported correctly or not
	 */
	public String reportDefectiveChair(char row, int chair, String description) {
		String msj = "Invalid Chair";

		boolean reported = false;
		for (int i = 0; i < nameRows.length&&!reported; i++) {
			if(nameRows[i]==row&&chairs[i][chair]!=null) {
				chairs[i][chair].setStatus(Chair.DEF);
				chairs[i][chair].setDescription(description);
				msj = "reported correctly";
				reported = true;
			}
		} 
		
		return msj;
	}
	/**
	 * This method calculate the percentage of defective chairs in the auditorium</br> 
	 * <b>Pre:</b> the matriz chairs need to was created before, chairs != null
	 * @return a string that indicates the percentage of defective chairs in the auditorium 
	 */
	public String calculatePercentageDefectiveChairs() {
		String msj = "";
		double numChairs = 0;
		double numChairsDefective = 0;
		
		for(int i = 0; i < chairs.length;i++) {
			for(int j = 0; j <chairs[0].length;j++) {
				if(chairs[i][j]!=null) {
					numChairs++;
					if(chairs[i][j].getStatus().equalsIgnoreCase(Chair.DEF)) {
						numChairsDefective++;
					}
				}
			}
		}
		
		if(numChairs!=0) {
		msj = "The percentage of defective chairs is: "+((numChairsDefective/numChairs)*100)+"%";
		}else {
		msj = "Don't are any chair in the auditorium";
		}
	return msj;
	}
	
}