package ui;
import java.time.LocalDateTime;
import java.util.Scanner;

import model.*;

public class UserInterface{
	//Attributes
	private static Scanner in;
	private static Scanner inNum;
	//Relation
	private static University university;
	//Main method
	public static void main(String args[]){
		in = new Scanner(System.in);
		inNum = new Scanner(System.in);
		university = new University("ICESI");
		
		System.out.println("Welcome to "+university.getName()+" university!");
		
		while(showMenu());
	
		System.out.println("Thanks for use our app :)");
	}
	
	//Methods
	
	/**
	 * This method shows the options of the menu, and calls the options that the user choose, returns true always if the choose isn't exit.
	 * <b>Pre: The methods called into this method need to exists</b>
	 * @return return a boolean that indicates if the methods continues calling or the program ends.
	 */
	public static boolean showMenu() {
		boolean bool = true;
		System.out.println("Type a option:");
		System.out.println("1. Create the auditorium's chairs");
		System.out.println("2. Report a defective chair");
		System.out.println("3. Report the percentage of defective chairs");
		System.out.println("4. Create a event");
		System.out.println("5. Erase a event");
		System.out.println("6. Show the events in the next 5 days");
		System.out.println("7. Exit");
		
		int choose = inNum.nextInt();
		
		switch(choose) {
			case 1:
				createChairs();
				break;
			case 2:
				reportDefectiveChair();
				break;
			case 3:
				calculatePercentageDefectiveChairs();
				break;
			case 4:
				createEvent();
				break;
			case 5:
				System.out.println("Type The event's name to erase");
				String name = in.nextLine();
				System.out.println(university.eraseEvent(name));
				break;
			case 6:
				System.out.println(university.showEventsNextFiveDays());
				break;
			case 7:
				System.out.println("Exiting");
				bool = false;
				break;
		}
		
		
		return bool;
	}
	/**
	 * This method give to the program the info that need to create the chairs of an auditorium, 
	 * then the method calls another method in university that continue with the request.</br>
	 * 
	 * The method get the auditorium, the # of rows, the maximum of columns, and an array with the # of chairs per row.
	 */
	public static void createChairs() {
		System.out.println("Type the auditorium");
		System.out.println(university.showAuditoriums());
		int auditorium = inNum.nextInt()-1;
		
		System.out.println("Type # of rows:");
		int rows = inNum.nextInt();
		
		int colums [] = new int [rows];
		int maxColums = 0;
		
		for(int i = 0; i< rows;i++) {
			System.out.println("Type the # of chairs of row: "+(i+1));
			colums[i] = inNum.nextInt();
			if (colums[i]>maxColums) {
				maxColums = colums[i];
			}
		}
		
		university.createChair(auditorium, rows, maxColums, colums);
		
	}
	/**
	 * This method give to the program the info that need to report a defective chair of an auditorium, 
	 * then the method calls another method in university that continue with the request.</br>
	 * 
	 * The method get the auditorium, the row, chair of the row and the description of the defective.  
	 */
	public static void reportDefectiveChair() {
		System.out.println("Type the # of the auditorium");
		System.out.println(university.showAuditoriums());
		int auditorium = inNum.nextInt()-1;
		
		System.out.println("Type the character of the row");
		char row = (in.nextLine().toUpperCase()).charAt(0);
		
		System.out.println("Type the # of the chair");
		int chair = inNum.nextInt()-1;
		
		System.out.println("Type the description of the defective");
		String description = in.nextLine();
		
		System.out.println(university.reportDefectiveChair(auditorium,row,chair,description));
		
	}
	/**
	 * This method give to the program the info that need to calculate the percentage a defective chairs in an auditorium, 
	 * then the method calls another method in university that continue with the request.</br>
	 * 
	 * The method get the auditorium.
	 */
	public static void calculatePercentageDefectiveChairs() {
		System.out.println("Type the # of the auditorium");
		System.out.println(university.showAuditoriums());
		int auditorium = inNum.nextInt()-1;
		
		System.out.println(university.calculatePercentageDefectiveChairs(auditorium));
	} 
	
	/**
	 * This method give to the program the info that need to create a event, 
	 * then the method calls another method in university that continue with the request.</br>
	 * 
	 * The method get the event's name, the event's date, a # of auditoriums and the auditoriums.
	 * 
	 * The method prints a message indicating if the event was created or not.
	 */
	public static void createEvent() {
		System.out.println("Type the event's name: ");
		String name = in.nextLine();
		System.out.println("Type the event's date ");
		System.out.println("Type the year");
		int year = inNum.nextInt();
		System.out.println("Type the mounth");
		int month = inNum.nextInt();
		while(!(month>0&&month<13)) {
			System.out.println("Type a valid month");
			month = inNum.nextInt();
		}
		System.out.println("Type the day");
		int day = inNum.nextInt();
		while(!(day>0&&day<31)) {
			System.out.println("Type a valid day");
			day = inNum.nextInt();
		}
		
		
		System.out.println("Type the start hour from 7 to 18 hours");
		int startHour = inNum.nextInt();
		while(startHour< 7 || startHour >18) {
			System.out.println("Invalid hour. Type the start hour from 7 to 18 hours");
			startHour = inNum.nextInt();
		}
		
		LocalDateTime startDate = LocalDateTime.of(year, month, day, startHour, 0);
		
		System.out.println("Type the final hour from "+(startHour+2)+" to "+(startHour+12)+" hours");
		int finalHour = inNum.nextInt();
		while(finalHour < startHour+2 || finalHour > ((startHour+12 > 20 ) ? 20 : startHour+12)) {
			System.out.println("Invalid hour. Type the final hour from "+(startHour+2)+" to "+(startHour+12)+" hours");
			startHour = inNum.nextInt();
		}
		
		LocalDateTime finalDate = LocalDateTime.of(year, month, day, finalHour, 0);
		
		System.out.println("Type a teacher's name");
		String teacher = in.nextLine();
		
		System.out.println("Type the faculty's name");
		String faculty = in.nextLine();
		
		System.out.println("Type the number of auditoriums that the event will use");
		int numAuditorium = inNum.nextInt();
		
		int auditorios[] = new int[numAuditorium];
		
		for (int i = 0; i < numAuditorium;i++) {
			System.out.println(university.showAuditoriums());
			System.out.println("Type the # of the auditorium");
			auditorios[i] =  inNum.nextInt();
		}
		
		if (university.checkHour(auditorios, startDate,finalDate)) {
			System.out.println(university.addEvent(name, startDate, finalDate, teacher, faculty, auditorios));
		}else {
			System.out.println("The event can't be created, another event was created to this date");
		}
	}
}