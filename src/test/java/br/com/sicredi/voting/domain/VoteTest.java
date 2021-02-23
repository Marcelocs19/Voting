package br.com.sicredi.voting.domain;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.sicredi.voting.feature.VoteScenarioFactory;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

@RunWith(MockitoJUnitRunner.class)
public class VoteTest {

    @Test    
    public void EqualAndHashCode_ExpectedEquals() {
        Vote a = VoteScenarioFactory.VOTE;
        Vote b = VoteScenarioFactory.VOTE_NEW;

        assertNotSame(a, b);
		assertEquals(a.hashCode(), b.hashCode());
		assertEquals(a.toString(), b.toString());
		assertTrue(a.hashCode() == b.hashCode());
		assertTrue(a.equals(b));
    }
    
    @Test
    public void simpleEqualsContact() {
        EqualsVerifier.simple().forClass(Vote.class).suppress(Warning.SURROGATE_KEY);
    }
    
    @Test
    public void vote_ExpectedBuild(){
        assertNotNull(VoteScenarioFactory.VOTE_BUILDER);
    }

    @Test
    public void vote_ExpectedNoArgs() {
        new Vote();
    }

    @Test
    public void vote_ExpectedSetObjects() {
        assertNotNull(VoteScenarioFactory.VOTE_SET);
    }

    @Test
    public void vote_ExpectedGetObjects() {
        assertNotNull(VoteScenarioFactory.VOTE_GET);
    }

    @Test
    public void vote_toString() {
        Vote.VoteBuilder builder = Vote.builder();
        String expected = Vote.builder().toString();
        assertEquals(expected, builder.toString());
    }
}
