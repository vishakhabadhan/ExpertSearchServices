package de.adviqo.ms.expertsearchservices.ctrl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.adviqo.ms.expertsearchservices.db.entities.Expert;
import de.adviqo.ms.expertsearchservices.domain.ExpertSearchAndSortFilterRequest;
import de.adviqo.ms.expertsearchservices.domain.search.ExpertSearchFilter;
import de.adviqo.ms.expertsearchservices.domain.sort.ExpertSortFilter;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class ExpertSearchControllerIntTest {

	@Autowired
    private TestRestTemplate restTemplate;
    
    @LocalServerPort
    private int port;
    
	@Test
	public void listExpertByNameTest() {		
        String adr = getApiAdr("/expert/searchByName");
        ExpertSearchFilter expertSearchFilter = new ExpertSearchFilter();
        expertSearchFilter.setName("EXPERT_1");
        
        HttpEntity<String> requestEntity = makePostEntity(expertSearchFilter);
        ResponseEntity<List<Expert>> result = restTemplate.exchange(adr, HttpMethod.POST, requestEntity,
        		new ParameterizedTypeReference<List<Expert>>(){});
        
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
	}
	
	@Test
	public void listExpertByDescriptionTest() {		
        String adr = getApiAdr("/expert/searchByDescription");
        ExpertSearchFilter expertSearchFilter = new ExpertSearchFilter();
        expertSearchFilter.setDescription("DESCRIPTION_1");
        
        HttpEntity<String> requestEntity = makePostEntity(expertSearchFilter);
        ResponseEntity<List<Expert>> result = restTemplate.exchange(adr, HttpMethod.POST, requestEntity,
        		new ParameterizedTypeReference<List<Expert>>(){});
        
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
	}
	
	@Test
	public void listExpertTest() {		
        String adr = getApiAdr("/expert/searchByLanguage");
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
        
        HttpEntity<String> requestEntity = makeSearchAndSortFilterRequestEntity(filterRequest);
        ResponseEntity<List<Expert>> result = restTemplate.exchange(adr, HttpMethod.POST, requestEntity,
        		new ParameterizedTypeReference<List<Expert>>(){});
        
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertNotNull(result.getBody());
	}
	
	private HttpEntity<String> makeSearchAndSortFilterRequestEntity(ExpertSearchAndSortFilterRequest filterRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(data2json(filterRequest), headers);
	}

	private String getApiAdr(String apiPath) {
        return "http://localhost:" + port + apiPath;
    }

	private HttpEntity<String> makePostEntity(final ExpertSearchFilter expertSearchFilter) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(data2json(expertSearchFilter), headers);
    }
	   
    private String data2json(Object data) {
        try {
            return new ObjectMapper().writeValueAsString(data);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
