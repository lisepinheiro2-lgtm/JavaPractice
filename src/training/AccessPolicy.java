package training;

public class AccessPolicy {

	public static boolean canViewIntervention(String requesterName, String role, Intervention intervention) {

		if (requesterName == null || requesterName.isBlank() || intervention == null || role == null) {
			return false;
		}

		boolean adminRole = role.equalsIgnoreCase("admin");
		boolean employeeRole = role.equalsIgnoreCase("employee");

		if (!adminRole && !employeeRole) {
			return false;
		}

		if (adminRole) {
			return true;
		}

		String employee = intervention.getFirstName();

		if (requesterName.equalsIgnoreCase(employee) && employeeRole) {
			return true;
		}

		return false;
	}

	public static boolean canEditIntervention(String requesterName, String role, Intervention intervention) {

		if (requesterName == null || requesterName.isBlank() || intervention == null || role == null) {
			return false;
		}

		boolean adminRole = role.equalsIgnoreCase("admin");
		
		if (adminRole) {
			return true;
		}
		
		return false;
	}
}
