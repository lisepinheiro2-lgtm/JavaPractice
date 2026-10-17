package training;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class AccessPolicyTest {

	@ParameterizedTest
	@CsvSource({ "Anna, EMPLOYEE, Anna, true", "Anna, EMPLOYEE, Lucas, false", "Lucas, ADMIN, Anna, true",
			"Emma, VISITOR, Emma, false", "ANNA, employee, Anna, true" })
	void testAccess(String requesterName, String role, String interventionOwner, boolean expected) {

		Intervention intervention = new Intervention(interventionOwner, "09:00", "10:00");

		boolean result = AccessPolicy.canViewIntervention(requesterName, role, intervention);

		assertEquals(expected, result);
	}

	@Test
	void testNullAccess() {

		Intervention intervention = new Intervention("Anna", "09:00", "10:00");

		assertAll(() -> assertFalse(AccessPolicy.canViewIntervention(null, "ADMIN", intervention)),
				() -> assertFalse(AccessPolicy.canViewIntervention("Anna", null, intervention)),
				() -> assertFalse(AccessPolicy.canViewIntervention("Anna", "ADMIN", null)));
	}

	@ParameterizedTest
	@CsvSource({ "Emma, ADMIN, Anna, true", "Anna, EMPLOYEE, Anna, false", "Lucas, VISITOR, Lucas, false",
			"Emma, admin, Lucas, true", "Hugo, MANAGER, Anna, false" })
	void testEditAccess(String requesterName, String role, String owner, boolean expected) {

		Intervention intervention = new Intervention(owner, "09:00", "10:00");

		assertEquals(expected, AccessPolicy.canEditIntervention(requesterName, role, intervention));
	}
	
	@Test
	void testNullEditRole() {
		
		Intervention intervention = new Intervention("Anna", "09:00", "10:00");

		assertAll(() -> assertFalse(AccessPolicy.canEditIntervention(null, "ADMIN", intervention)),
				() -> assertFalse(AccessPolicy.canEditIntervention("Anna", null, intervention)),
				() -> assertFalse(AccessPolicy.canEditIntervention("Anna", "ADMIN", null)));
	}
}
