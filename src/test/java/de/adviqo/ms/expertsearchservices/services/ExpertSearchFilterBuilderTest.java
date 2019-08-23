package de.adviqo.ms.expertsearchservices.services;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import de.adviqo.ms.expertsearchservices.db.entities.Expert;
import de.adviqo.ms.expertsearchservices.domain.search.ExpertSearchFilter;

public class ExpertSearchFilterBuilderTest {

	ExpertSearchFilterBuilder expertSearchFilterBuilder = null;
	
	@Before
    public void setUpBefore() {
		expertSearchFilterBuilder = new ExpertSearchFilterBuilder();
    }
	
	@Test
	public void forExpertSearchFilterTest() {
		ExpertSearchFilter expertSearchFilter = new ExpertSearchFilter();
		expertSearchFilter.setAvailability("ONLINE");
		
		assertNotNull(expertSearchFilterBuilder.forExpertSearchFilter(expertSearchFilter));
		assertTrue(expertSearchFilterBuilder.forExpertSearchFilter(expertSearchFilter) instanceof Expert );
		assertEquals(expertSearchFilterBuilder.forExpertSearchFilter(expertSearchFilter).getAvailability(), "ONLINE");	
	}
	
	@Test
	public void getExampleMatcherTest() {
		List<String> ignoredProperties = new ArrayList<>();
		ignoredProperties.add("id");
		
		ExampleMatcher exampleMatcher = expertSearchFilterBuilder.getExampleMatcher(ignoredProperties);
		
		assertNotNull(exampleMatcher);
		assertEquals(new ArrayList<>(exampleMatcher.getIgnoredPaths()).get(0), ignoredProperties.get(0));	
	}
	
	@Test
	public void getExampleExpertListByExpertSearchFilterTest() {
		ExpertSearchFilter expertSearchFilter = new ExpertSearchFilter();
		expertSearchFilter.setAvailability("ONLINE");
		
		Example<Expert> exampleExpert = expertSearchFilterBuilder.getExampleExpertListByExpertSearchFilter(expertSearchFilter);
		
		assertNotNull(exampleExpert);
		assertNotNull(exampleExpert.getProbe());
		assertEquals(exampleExpert.getProbe().getAvailability(), expertSearchFilterBuilder.forExpertSearchFilter(expertSearchFilter).getAvailability());
		assertEquals(new ArrayList<>(exampleExpert.getMatcher().getIgnoredPaths()).get(0), "id");
	}
}
