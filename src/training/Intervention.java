package training;

public class Intervention {

	private String startHour;
	private String endHour;
	private String firstName;

	public Intervention(String firstName, String startHour, String endHour) {

		this.firstName = firstName;
		this.startHour = startHour;
		this.endHour = endHour;
	}


	public String getStartHour() {
		return startHour;
	}

	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}

	public String getEndHour() {
		return endHour;
	}

	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
