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
	 * 
	 * @param rows
	 * @param maxColums
	 * @param colums
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
	 * 
	 * @param row
	 * @param chair
	 * @param description
	 * @return
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
	 * 
	 * @return
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
		
		msj = "The percentage of defective chairs is: "+((numChairsDefective/numChairs)*100)+"%";
		
	return msj;
	}
	
}