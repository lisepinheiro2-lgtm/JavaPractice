package training;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InterventionServiceTest {

	private ArrayList<Intervention> interventions;
	private InterventionService test;

	@BeforeEach
	void setUp() {
		interventions = new ArrayList<>();

		interventions.add(new Intervention("Anna", "09:00", "10:00"));
		interventions.add(new Intervention("Lucas", "10:00", "11:00"));
		interventions.add(new Intervention("Anna", "14:00", "15:00"));
		interventions.add(new Intervention("Emma", "16:00", "17:00"));

		test = new InterventionService(interventions);
	}

	@Test
	void testEmployeeAccess() {
		ArrayList<Intervention> testedInterventions = test.getVisibleInterventions("anna", "employee");
		for (Intervention intervention : testedInterventions) {

			assertTrue(intervention.getFirstName().equalsIgnoreCase("anna"));
		}

		assertEquals(2, testedInterventions.size());
	}

	@Test
	void testAdminAccess() {
		ArrayList<Intervention> testedInterventions = test.getVisibleInterventions("Emma", "admin");

		assertEquals(4, testedInterventions.size());
		assertEquals(interventions, testedInterventions);
	}

	@Test
	void testVisitorAccess() {
		ArrayList<Intervention> testedInterventions = test.getVisibleInterventions("Hugo", "visitor");

		assertTrue(testedInterventions.isEmpty());
	}

	@Test
	void testUnauthorizedUpdate() {

		Intervention currentIntervention = interventions.getFirst();

		boolean result = test.updateInterventionHours("Anna", "employee", currentIntervention, LocalTime.parse("11:00"),
				LocalTime.parse("12:00"));

		assertFalse(result);
		assertEquals("09:00", currentIntervention.getStartHour());
		assertEquals("10:00", currentIntervention.getEndHour());
	}
}