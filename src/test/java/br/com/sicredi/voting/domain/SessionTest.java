package br.com.sicredi.voting.domain;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.sicredi.voting.feature.SessionScenarioFactory;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

@RunWith(MockitoJUnitRunner.class)
public class SessionTest {

    @Test
    public void EqualAndHashCode_ExpectedEquals() {
        Session a = SessionScenarioFactory.SESSION;
        Session b = SessionScenarioFactory.SESSION_NEW;

        assertNotSame(a, b);
		assertEquals(a.hashCode(), b.hashCode());
		assertEquals(a.toString(), b.toString());
		assertTrue(a.hashCode() == b.hashCode());
		assertTrue(a.equals(b));
    }

    @Test
    public void simpleEqualsContact() {
        EqualsVerifier.simple().forClass(Session.class).suppress(Warning.SURROGATE_KEY);
    }
    
    @Test
    public void session_ExpectedBuild(){
        assertNotNull(SessionScenarioFactory.SESSION_BUILDER);
    }

    @Test
    public void session_ExpectedNoArgs() {
        new Associate();
    }

    @Test
    public void session_ExpectedSetObjects() {
        assertNotNull(SessionScenarioFactory.SESSION_SET);
    }

    @Test
    public void session_ExpectedGetObjects() {
        assertNotNull(SessionScenarioFactory.SESSION_GET);
    }

    @Test
    public void session_toString() {
        Session.SessionBuilder builder = Session.builder();
        String expected = Session.builder().toString();
        assertEquals(expected, builder.toString());
    }

    
    @Test
    public void session_toDto() {
        assertNotNull(SessionScenarioFactory.SESSION_RESPONSE);
    }

    
    @Test
    public void session_Of() {
        assertNotNull(SessionScenarioFactory.SESSION_OF);
    }

}
