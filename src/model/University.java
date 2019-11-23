package model;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class University{
	
	//Attributes
	private String name;
	
	//Relations
	private Auditorium auditoriums[];
	private ArrayList <Event> events;
	
	//Constructor
	public University(String name){
		
		this.name = name;
		
		auditoriums = new Auditorium[8];
		
		auditoriums[0] = new Auditorium("auditorio Manuelita","");
		auditoriums[1] = new Auditorium("auditorio Argos","");
		auditoriums[2] = new Auditorium("auditorio Varela","");
		auditoriums[3] = new Auditorium("auditorio Manuelita","");
		auditoriums[4] = new Auditorium("auditorio Manuelita","");
		auditoriums[5] = new Auditorium("auditorio Manuelita","");
		auditoriums[6] = new Auditorium("auditorio Manuelita","");
		auditoriums[7] = new Auditorium("auditorio Manuelita","");
		
		events = new ArrayList <Event>();
	
	}
	
	//Getters
	public String getName() {
		return name;
	}
	
	//Methods
	
	/**
	 * This method returns a object auditorium with the attribute of the parameter of the array auditoriums.</br>
	 * <b>Pre:</b> The array auditoriums need to exists. 
	 * @param name Need to be a String valid name !=null
	 * @return An Auditorium or null, if the Auditorium is in the array.
	 */
	public Auditorium getAuditorium(String name) {
		Auditorium auditorio = null;
		
		for (int i = 0; i < auditoriums.length; i++) {
			if(auditoriums[i].getName().equalsIgnoreCase(name)) {
				auditorio = auditoriums[i];
			}
		}
		
		return auditorio;
	}
	/**
	 * This method checks if the event was created before, and if it wasn't then create the event 
	 * @param name
	 * @param startDate
	 * @param finalDate
	 * @param teacher
	 * @param faculty
	 * @param NumAuditoriums
	 * @return
	 */
	public String addEvent(String name, LocalDateTime startDate, LocalDateTime finalDate, String teacher, String faculty, int NumAuditoriums[]){
		
		Auditorium auditoriums[] = new Auditorium[NumAuditoriums.length];
		for(int i = 0; i < NumAuditoriums.length; i++) {
			auditoriums[i] = this.auditoriums[NumAuditoriums[i]];
		}
		
		String msj = "The event can't be created, because it was created before";
		boolean found = false;
		
			for(int i = 0;i < events.size()&&!found;i++){
				if(events.get(i).getName().equalsIgnoreCase(name)){
					found = true;
				}
			}
		
			if (!found) {
				events.add(new Event(name,startDate,finalDate,teacher,faculty,auditoriums));
				msj = "The event was created";
			}
		
		return msj;
	}
	
	public String eraseEvent(String name){
		String msj = "The event doesn't exist";
			boolean erased = false;
			for(int i = 0; i < events.size()&&!erased; i++){
				if(events.get(i).getName().equalsIgnoreCase(name)){
					events.remove(i);
					erased = true;
					msj = "The event was erased correctly!";
				}
			}
		return msj;
	}

	public String showEventsNextFiveDays() {
		String msj = (events.isEmpty()) ? "Don't are events": "";
		LocalDateTime dateNow = LocalDateTime.now();
		LocalDateTime dateAfter = dateNow.plusDays(6);
		
		for(int i = 0; i < events.size();i++) {
			if(events.get(i).getFinalDate().isBefore(dateAfter)&&events.get(i).getFinalDate().isAfter(dateNow)) {
				msj += events.get(i).toString();
			}
		}
		
		return msj;
	}
	
	public void createChair(int auditorium, int rows, int maxColums, int []colums) {
		auditoriums[auditorium].createChairs(rows, maxColums, colums);
	}
	
	public String showAuditoriums() {
		String msj = "";
		
		for (int i = 0; i < auditoriums.length; i++) {
			msj += (i+1)+", "+auditoriums[i].getName()+"\n";
		}
		
	return msj;
	}
	
	public String reportDefectiveChair(int auditorium, char row, int chair, String description) {
		String msj = "";
		
		msj = auditoriums[auditorium].reportDefectiveChair(row, chair, description);
		
		return msj;
	}
	
	public boolean checkHour(int[] NumAuditoriums, LocalDateTime startDate, LocalDateTime finalDate) {
		Auditorium auditoriums[] = new Auditorium[NumAuditoriums.length];
		for(int i = 0; i < NumAuditoriums.length; i++) {
			auditoriums[i] = this.auditoriums[NumAuditoriums[i]];
		}
		
		boolean bool = true;
		
		for(int i = 0; i < events.size()&&bool;i++) {
			for(int j = 0; j < events.get(i).getAuditorios().length; j++) {
				for(int k = 0; k < auditoriums.length; k++) {
					if(auditoriums[k].equals((events.get(i).getAuditorios())[j])) {
						if(events.get(i).getStartDate().getYear()==startDate.getYear()) {
							if(events.get(i).getStartDate().getMonth().equals(startDate.getMonth())) {
								if(events.get(i).getStartDate().getDayOfMonth()==startDate.getDayOfMonth()) {
									if(startDate.isAfter(events.get(i).getStartDate())&&startDate.isBefore(events.get(i).getFinalDate())) {
										bool = false;
									}else if(finalDate.isAfter(events.get(i).getStartDate())&&startDate.isBefore(events.get(i).getFinalDate())) {
										bool = false;
									}else if(finalDate.equals(events.get(i).getFinalDate())||startDate.equals(events.get(i).getStartDate())) {
										bool = false;
									}
								}
							}
						}
					}
				}	
			}
		}
		
		return bool;
	}
	
	public String calculatePercentageDefectiveChairs(int auditorium) {
		return auditoriums[auditorium].calculatePercentageDefectiveChairs();
	}
	
}