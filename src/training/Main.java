package training;

import java.util.ArrayList;

public class Main {

	private static ArrayList<Intervention> interventions;
	private static CSV csv;
	

	public static void main(String[] args) {
		
		Swing practice = new Swing();
		practice.createPanel();
		practice.createButtonPanel();
		practice.createFrame();
		interventions = new ArrayList<>();
		csv = new CSV(interventions);
			

		
		
		for(Intervention intervention : interventions) {
			
			System.out.println(intervention.getFirstName() + " " + intervention.getStartHour() + " " + intervention.getEndHour());			
		}
	}

}
