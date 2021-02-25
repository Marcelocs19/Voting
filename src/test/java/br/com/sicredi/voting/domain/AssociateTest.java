package br.com.sicredi.voting.domain;

import static org.junit.Assert.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import br.com.sicredi.voting.feature.AssociateScenarioFactory;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

@RunWith(MockitoJUnitRunner.class)
public class AssociateTest {
    
    @Test
    public void EqualAndHashCode_ExpectedEquals() {
        Associate a = AssociateScenarioFactory.ASSOCIATE;
        Associate b = AssociateScenarioFactory.ASSOCIATE_NEW;

        assertNotSame(a, b);
		assertEquals(a.hashCode(), b.hashCode());
		assertEquals(a.toString(), b.toString());
		assertTrue(a.hashCode() == b.hashCode());
		assertTrue(a.equals(b));
    }

    @Test
    public void simpleEqualsContact() {
        EqualsVerifier.simple().forClass(Associate.class).suppress(Warning.SURROGATE_KEY);
    }

    @Test
    public void associate_ExpectedBuild(){
        assertNotNull(AssociateScenarioFactory.ASSOCIATE_BUILDER);
    }

    @Test
    public void associate_ExpectedNoArgs() {
        new Associate();
    }

    @Test
    public void associate_ExpectedSetObjects() {
        assertNotNull(AssociateScenarioFactory.ASSOCIATE_SET);
    }

    @Test
    public void associate_ExpectedGetObjects() {
        assertNotNull(AssociateScenarioFactory.ASSOCIATE_GET);
    }

    @Test
    public void associate_toString() {
        Associate.AssociateBuilder builder = Associate.builder();
        String expected = Associate.builder().toString();
        assertEquals(expected, builder.toString());
    }

}
