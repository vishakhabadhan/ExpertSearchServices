package de.adviqo.ms.expertsearchservices.db.entities;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

public class ExpertTest {

	private Expert expert = null;
    
    @Before
    public void setUpBefore() {
    	expert = new Expert("EXPERT_1","DESCRIPTION_1", "ONLINE", "JAVA", new BigDecimal(0.99));
    }
    
    @Test
    public void expertTest() {
        assertNotNull(expert);
        assertEquals(expert.getAvailability(), "ONLINE");
        assertEquals(expert.getLanguage(), "JAVA");
    }   
}
