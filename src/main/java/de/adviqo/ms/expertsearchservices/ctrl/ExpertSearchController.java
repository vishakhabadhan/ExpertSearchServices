package de.adviqo.ms.expertsearchservices.ctrl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import de.adviqo.ms.expertsearchservices.db.entities.Expert;
import de.adviqo.ms.expertsearchservices.domain.ExpertSearchAndSortFilterRequest;
import de.adviqo.ms.expertsearchservices.domain.ExpertStoreRequest;
import de.adviqo.ms.expertsearchservices.domain.ExpertStoreResponse;
import de.adviqo.ms.expertsearchservices.domain.search.ExpertSearchFilter;
import de.adviqo.ms.expertsearchservices.services.ExpertSearchService;

@Controller
@RestController
@RequestMapping("/expert")
public class ExpertSearchController {
    
    private ExpertSearchService expertSearchService;
    
    private static final Logger log = LoggerFactory.getLogger(ExpertSearchController.class);
    
    @Autowired
    public ExpertSearchController(ExpertSearchService expertSearchService) {
        this.expertSearchService = expertSearchService;
    }
    
    @RequestMapping(path = "searchByLanguage", method = RequestMethod.POST)
    public ResponseEntity<List<Expert>> listExpert(@RequestBody ExpertSearchAndSortFilterRequest request) {
        log.info("Search Experts");
        List<Expert> expertList = expertSearchService.getExpertListByExpertSeaarchFilterAndExpertSortFilter(request.getExpertSearchFilter(), request.getExpertSortFilter());
        log.info("got templates, returning list");
        return ResponseEntity.ok().body(expertList);
    }
    
    @RequestMapping(path = "searchByName", method = RequestMethod.POST)
    public ResponseEntity<List<Expert>> listExpertByName(@RequestBody ExpertSearchFilter filter) {
        log.info("Search Experts by Name");
        List<Expert> expertList = expertSearchService.getExpertListByName(filter.getName());
        log.info("got templates, returning list");
        return ResponseEntity.ok().body(expertList);
    }
    
    @RequestMapping(path = "searchByDescription", method = RequestMethod.POST)
    public ResponseEntity<List<Expert>> listExpertByDescription(@RequestBody ExpertSearchFilter filter) {
    	log.info("Search Experts by Description");
        List<Expert> expertList = expertSearchService.getExpertListByDescription(filter.getDescription());
        log.info("got templates, returning list");
        return ResponseEntity.ok().body(expertList);
    }
    
    @RequestMapping(path = "store", method = RequestMethod.POST)
    public ResponseEntity<ExpertStoreResponse> storeExpert(@RequestBody ExpertStoreRequest request) {
        log.info("Store Experts");
        return ResponseEntity.ok().body(new ExpertStoreResponse(expertSearchService.saveExpert(request).getId()));
    }
}
