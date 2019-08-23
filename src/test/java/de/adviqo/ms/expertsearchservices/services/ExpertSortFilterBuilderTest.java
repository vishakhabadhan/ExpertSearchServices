package de.adviqo.ms.expertsearchservices.services;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import de.adviqo.ms.expertsearchservices.domain.sort.ExpertSortFilter;

public class ExpertSortFilterBuilderTest {

	ExpertSortFilterBuilder expertSortFilterBuilder = null;
	
	@Before
    public void setUpBefore() {
		expertSortFilterBuilder = new ExpertSortFilterBuilder();
    }
	
	@Test
	public void toSortDirectionTest() {
		ExpertSortFilter expertSortFilter = new ExpertSortFilter();
	
		assertNotNull(expertSortFilterBuilder.toSortDirection(expertSortFilter));
		assertEquals(expertSortFilterBuilder.toSortDirection(expertSortFilter), Direction.ASC);
		
		expertSortFilter.setSortOrder("ASC");
		assertNotNull(expertSortFilterBuilder.toSortDirection(expertSortFilter));
		assertEquals(expertSortFilterBuilder.toSortDirection(expertSortFilter), Direction.ASC);
		
		expertSortFilter.setSortOrder("DESC");
		assertNotNull(expertSortFilterBuilder.toSortDirection(expertSortFilter));
		assertEquals(expertSortFilterBuilder.toSortDirection(expertSortFilter), Direction.DESC);	
	}

	@Test
	public void forExpertSortFilterTest() {
		ExpertSortFilter expertSortFilter = new ExpertSortFilter();
		List<String> sortProperties = new ArrayList<>();
		sortProperties.add("AVAILABILITY");
		expertSortFilter.setSortProperties(sortProperties);
		expertSortFilter.setSortOrder("DESC");
	
		assertNotNull(expertSortFilterBuilder.forExpertSortFilter(expertSortFilter));
		assertTrue(expertSortFilterBuilder.forExpertSortFilter(expertSortFilter) instanceof Sort);
		assertTrue(expertSortFilterBuilder.forExpertSortFilter(expertSortFilter).isSorted());
		expertSortFilterBuilder.forExpertSortFilter(expertSortFilter).stream().forEach(p -> assertEquals(p.getDirection(),Direction.DESC));
		expertSortFilterBuilder.forExpertSortFilter(expertSortFilter).stream().forEach(p -> assertTrue(p.getProperty().equalsIgnoreCase("AVAILABILITY")));		
	}
}
