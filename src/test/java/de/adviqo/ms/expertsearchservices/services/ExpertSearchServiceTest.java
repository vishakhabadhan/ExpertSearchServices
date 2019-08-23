package de.adviqo.ms.expertsearchservices.services;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import de.adviqo.ms.expertsearchservices.db.entities.Expert;
import de.adviqo.ms.expertsearchservices.db.repos.ExpertRepository;
import de.adviqo.ms.expertsearchservices.domain.ExpertStoreRequest;
import de.adviqo.ms.expertsearchservices.domain.search.ExpertSearchFilter;
import de.adviqo.ms.expertsearchservices.domain.sort.ExpertSortFilter;

import static org.mockito.Mockito.mock;

public class ExpertSearchServiceTest {
	
    ExpertSearchService expertSearchService;	
    ExpertRepository expertRepository;
	ExpertStoreRequest expertStoreRequest = null;
	
	
	@Before
    public void setUpBefore() {    
		expertRepository = mock(ExpertRepository.class);
		expertSearchService = new ExpertSearchService(expertRepository);
	}
	 
	@Test
	public void getExpertListByNameTest() {
		Expert expert = new Expert("EXPERT_1","DESCRIPTION_1", "ONLINE", "JAVA", new BigDecimal(0.99));
		
		List<Expert> expertList = new ArrayList<>();
		expertList.add(expert);
		
		when(expertRepository.findExpertByName("EXPERT_2")).thenReturn(expertList);
		
		List<Expert> experts = expertSearchService.getExpertListByName("EXPERT_1");
		
		assertNotNull(experts);
		expertList.stream().forEach( q -> assertEquals(q.getDescription(), "DESCRIPTION_1"));
		expertList.stream().forEach( q -> assertEquals(q.getAvailability(), "ONLINE"));		
	}
	
	@Test
	public void getExpertListByDescriptionTest() {
		Expert expert = new Expert("EXPERT_1","DESCRIPTION_1", "ONLINE", "JAVA", new BigDecimal(0.99));
		
		List<Expert> expertList = new ArrayList<>();
		expertList.add(expert);
		
		when(expertRepository.findExpertByDescriptionContainingIgnoreCase("EXPERT_2")).thenReturn(expertList);
		
		List<Expert> experts = expertSearchService.getExpertListByDescription("DESCRIPTION_1");
		
		assertNotNull(experts);
		expertList.stream().forEach( q -> assertEquals(q.getDescription(), "DESCRIPTION_1"));
		expertList.stream().forEach( q -> assertEquals(q.getAvailability(), "ONLINE"));		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void getExpertListByExpertSeaarchFilterAndExpertSortFilterTest() {		
		Expert expert = new Expert("EXPERT_1","DESCRIPTION_1", "ONLINE", "JAVA", new BigDecimal(0.99));
		
		List<Expert> expertList = new ArrayList<>();
		expertList.add(expert);
		
		ExpertSearchFilter expertSearchFilter = new ExpertSearchFilter();
        expertSearchFilter.setLanguage("JAVA");
        
        ExpertSortFilter expertSortFilter = new ExpertSortFilter();		
        List<String> sortProperties = new ArrayList<String>();
        sortProperties.add("availability");
        expertSortFilter.setSortProperties(sortProperties);
        expertSortFilter.setSortOrder("DESC");
		
        ExpertSearchFilterBuilder expertSearchFilterBuilder = mock(ExpertSearchFilterBuilder.class);
        Example<Expert> expertExample = mock(Example.class);
        
        ExpertSortFilterBuilder expertSortFilterBuilder = mock(ExpertSortFilterBuilder.class);
        Sort expertSort = mock(Sort.class);
        
        when(expertSearchFilterBuilder.getExampleExpertListByExpertSearchFilter(expertSearchFilter)).thenReturn(expertExample);
        when(expertSortFilterBuilder.forExpertSortFilter(expertSortFilter)).thenReturn(expertSort);
        when(expertRepository.findAll(expertExample, expertSort)).thenReturn(expertList);
        
		List<Expert> experts = expertSearchService.getExpertListByExpertSeaarchFilterAndExpertSortFilter(expertSearchFilter,expertSortFilter);
		
		assertNotNull(experts);
		expertList.stream().forEach( q -> assertEquals(q.getLanguage(), "JAVA"));	
		assertEquals(expertList.get(0).getAvailability(),"ONLINE");
	}
}
