package br.com.sicredi.voting.domain;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.sicredi.voting.feature.ScheduleScenarioFactory;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

@RunWith(MockitoJUnitRunner.class)
public class ScheduleTest {
    
    @Test
    public void EqualAndHashCode_ExpectedEquals() {
        Schedule a = ScheduleScenarioFactory.SCHEDULE;
        Schedule b = ScheduleScenarioFactory.SCHEDULE_NEW;

        assertNotSame(a, b);
		assertEquals(a.hashCode(), b.hashCode());
		assertEquals(a.toString(), b.toString());
		assertTrue(a.hashCode() == b.hashCode());
		assertTrue(a.equals(b));
    }

    @Test
    public void simpleEqualsContact() {
        EqualsVerifier.simple().forClass(Schedule.class).suppress(Warning.SURROGATE_KEY);
    }

    @Test
    public void schedule_ExpectedBuild(){
        assertNotNull(ScheduleScenarioFactory.SCHEDULE_BUILDER);
    }

    @Test
    public void schedule_ExpectedNoArgs() {
        new Schedule();
    }

    @Test
    public void schedule_ExpectedSetObjects() {
        assertNotNull(ScheduleScenarioFactory.SCHEDULE_SET);
    }

    @Test
    public void schedule_ExpectedGetObjects() {
        assertNotNull(ScheduleScenarioFactory.SCHEDULE_GET);
    }

    @Test
    public void schedule_toString() {
        Schedule.ScheduleBuilder builder = Schedule.builder();
        String expected = Schedule.builder().toString();
        assertEquals(expected, builder.toString());
    }

    @Test
    public void schedule_toDto() {
        assertNotNull(ScheduleScenarioFactory.SCHEDULE_RESPONSE);
    }
    
    @Test
    public void schedule_of() {
        assertNotNull(ScheduleScenarioFactory.SCHEDULE_OF);
    }

}
