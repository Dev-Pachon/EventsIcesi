package model;

import java.time.LocalDateTime;

public class Event{
	//Attributes
	private String name;
	private LocalDateTime startDate;
	private LocalDateTime finalDate;
	private String teacher;
	private String faculty;
	//Relation
	private Auditorium auditorios[];
	//Constructor
	public Event(String name, LocalDateTime startDate, LocalDateTime finalDate, String teacher, String faculty, Auditorium auditorios[]){
		this.name = name;
		this.finalDate = finalDate;
		this.startDate = startDate;
		this.teacher = teacher;
		this.faculty = faculty;
		this.auditorios = new Auditorium[auditorios.length];
		this.auditorios = auditorios;
	}
	//Getters
	public Auditorium [] getAuditorios() {
		return auditorios;
	}
	
	public String getName(){
		return name;
	}
	
	public LocalDateTime getStartDate(){
		return startDate;
	}
	
	public LocalDateTime getFinalDate(){
		return finalDate;
	}
	
	public String getTeacher(){
		return teacher;
	}
	
	public String getFaculty(){
		return faculty;
	}
	
	//Method to print
	public String toString() {
		String msj = "";
			msj += 	"Name: "+name+"\n"+
					"Start date: "+startDate.toString()+"\n"+
					"Final date: "+finalDate.toString()+"\n"+
					"Teacher: "+teacher+"\n"+
					"Faculty: "+faculty+"\n"+
					"Auditorium(s):\n";
			for(int i = 0; i < auditorios.length; i++) {
				if(auditorios[i]!=null) {
					msj += "Name: "+auditorios[i].getName()+"\n";
				}
			}
			
		return msj;
	}
}