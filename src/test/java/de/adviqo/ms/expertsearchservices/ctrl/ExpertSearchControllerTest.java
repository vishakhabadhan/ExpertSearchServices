package de.adviqo.ms.expertsearchservices.ctrl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import de.adviqo.ms.expertsearchservices.db.entities.Expert;
import de.adviqo.ms.expertsearchservices.domain.ExpertSearchAndSortFilterRequest;
import de.adviqo.ms.expertsearchservices.domain.search.ExpertSearchFilter;
import de.adviqo.ms.expertsearchservices.domain.sort.ExpertSortFilter;
import de.adviqo.ms.expertsearchservices.services.ExpertSearchService;


public class ExpertSearchControllerTest {

	ExpertSearchController controller = null;
	ExpertSearchService service = null;
	
	@Before
    public void setUpBefore() {    
		service = mock(ExpertSearchService.class);
		controller = new ExpertSearchController(service);
	}
	 
	@Test
	public void listExpertByNameTest() {
	    ExpertSearchFilter filter = new ExpertSearchFilter();
	    filter.setName("EXPERT_1");
	    
	    Expert expert = new Expert("EXPERT_1","DESCRIPTION_1", "ONLINE", "JAVA", new BigDecimal(0.99));
		
		List<Expert> expertList = new ArrayList<>();
		expertList.add(expert);
	        
		when(service.getExpertListByName(filter.getName())).thenReturn(expertList);
		
		ResponseEntity<List<Expert>> result = controller.listExpertByName(filter);
		
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());
	}
	
	@Test
	public void getExpertListByDescriptionTest() {
	    ExpertSearchFilter filter = new ExpertSearchFilter();
	    filter.setDescription("DESCRIPTION_1");
	    
	    Expert expert = new Expert("EXPERT_1","DESCRIPTION_1", "ONLINE", "JAVA", new BigDecimal(0.99));
		
		List<Expert> expertList = new ArrayList<>();
		expertList.add(expert);
	        
		when(service.getExpertListByDescription(filter.getDescription())).thenReturn(expertList);
		
		ResponseEntity<List<Expert>> result = controller.listExpertByName(filter);
		
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());		
	}
	
	@Test
	public void getExpertListByExpertSeaarchFilterAndExpertSortFilterTest() {		
		ExpertSearchAndSortFilterRequest filterRequest = new ExpertSearchAndSortFilterRequest();
        
        ExpertSearchFilter expertSearchFilter = new ExpertSearchFilter();
        expertSearchFilter.setLanguage("JAVA");
        
        ExpertSortFilter expertSortFilter = new ExpertSortFilter();
        
        List<String> sortProperties = new ArrayList<>();
        sortProperties.add("pricePerMinute");
        
        expertSortFilter.setSortProperties(sortProperties);
        expertSortFilter.setSortOrder("DESC");
        
        filterRequest.setExpertSearchFilter(expertSearchFilter);
        filterRequest.setExpertSortFilter(expertSortFilter);
        
        Expert expert = new Expert("EXPERT_1","DESCRIPTION_1", "ONLINE", "JAVA", new BigDecimal(0.99));
		
		List<Expert> expertList = new ArrayList<>();
		expertList.add(expert);
		
        when(service.getExpertListByExpertSeaarchFilterAndExpertSortFilter(expertSearchFilter, expertSortFilter)).thenReturn(expertList);
		
		ResponseEntity<List<Expert>> result = controller.listExpert(filterRequest);
		
        assertNotNull(result);
        assertEquals(HttpStatus.OK, result.getStatusCode());	        
	}
}
