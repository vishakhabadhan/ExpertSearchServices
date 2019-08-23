package de.adviqo.ms.expertsearchservices.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import de.adviqo.ms.expertsearchservices.db.entities.Expert;
import de.adviqo.ms.expertsearchservices.domain.ExpertStoreRequest;
import de.adviqo.ms.expertsearchservices.domain.search.ExpertSearchFilter;
import de.adviqo.ms.expertsearchservices.domain.sort.ExpertSortFilter;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class ExpertSearchServiceIntTest {
	
	@Autowired
    ExpertSearchService expertSearchService;
	
	ExpertStoreRequest expertStoreRequest = null;
	 
	@Test
	public void getExpertListByNameTest() {
		expertStoreRequest = new ExpertStoreRequest("EXPERT_1","DESCRIPTION_1", "ONLINE", "DOTNET", new BigDecimal(59.9));
		expertSearchService.saveExpert(expertStoreRequest);
		
		expertStoreRequest = new ExpertStoreRequest("EXPERT_2","DESCRIPTION_2", "OFFLINE", "DOTNET", new BigDecimal(60.99));
		expertSearchService.saveExpert(expertStoreRequest);
		
		List<Expert> expertList = expertSearchService.getExpertListByName("EXPERT_2");
		
		assertNotNull(expertList);
		assertEquals(expertList.stream().count(), 1);
		expertList.stream().forEach( q -> assertEquals(q.getDescription(), "DESCRIPTION_2"));
		expertList.stream().forEach( q -> assertEquals(q.getAvailability(), "OFFLINE"));		
	}
	
	@Test
	public void getExpertListByDescriptionTest() {
		expertStoreRequest = new ExpertStoreRequest("EXPERT_1","DESCRIPTION_1", "ONLINE", "JAVA", new BigDecimal(59.9));
		expertSearchService.saveExpert(expertStoreRequest);
		
		expertStoreRequest = new ExpertStoreRequest("EXPERT_2","DESCRIPTION_2", "OFFLINE", "DOTNET", new BigDecimal(60.99));
		expertSearchService.saveExpert(expertStoreRequest);
		
		List<Expert> expertList = expertSearchService.getExpertListByDescription("_2");
		
		assertNotNull(expertList);
		assertEquals(expertList.stream().count(), 1);
		expertList.stream().forEach( q -> assertEquals(q.getDescription(), "DESCRIPTION_2"));
		expertList.stream().forEach( q -> assertEquals(q.getAvailability(), "OFFLINE"));		
	}
	
	@Test
	public void getExpertListByExpertSeaarchFilterAndExpertSortFilterTest() {
		expertStoreRequest = new ExpertStoreRequest("EXPERT_1","DESCRIPTION_1", "ONLINE", "DOTNET", new BigDecimal(0.70));
		expertSearchService.saveExpert(expertStoreRequest);
		
		expertStoreRequest = new ExpertStoreRequest("EXPERT_2","DESCRIPTION_2", "OFFLINE", "DOTNET", new BigDecimal(0.60));
		expertSearchService.saveExpert(expertStoreRequest);
		
		expertStoreRequest = new ExpertStoreRequest("EXPERT_3","DESCRIPTION_3", "OFFLINE", "JAVA", new BigDecimal(0.50));
		expertSearchService.saveExpert(expertStoreRequest);
		
		ExpertSearchFilter expertSearchFilter = new ExpertSearchFilter();
        expertSearchFilter.setLanguage("DOTNET");
        
        ExpertSortFilter expertSortFilter = new ExpertSortFilter();		
        List<String> sortProperties = new ArrayList<String>();
        sortProperties.add("availability");
        expertSortFilter.setSortProperties(sortProperties);
        expertSortFilter.setSortOrder("DESC");
        
		List<Expert> expertList = expertSearchService.getExpertListByExpertSeaarchFilterAndExpertSortFilter(expertSearchFilter,expertSortFilter);
		
		assertNotNull(expertList);
		assertEquals(expertList.stream().count(), 2);
		expertList.stream().forEach( q -> assertEquals(q.getLanguage(), "DOTNET"));	
		assertEquals(expertList.get(0).getAvailability(),"ONLINE");
		assertEquals(expertList.get(1).getAvailability(),"OFFLINE");
	}
}
