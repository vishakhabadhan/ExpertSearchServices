package de.adviqo.ms.expertsearchservices.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import de.adviqo.ms.expertsearchservices.db.entities.Expert;
import de.adviqo.ms.expertsearchservices.db.repos.ExpertRepository;
import de.adviqo.ms.expertsearchservices.domain.ExpertStoreRequest;
import de.adviqo.ms.expertsearchservices.domain.search.ExpertSearchFilter;
import de.adviqo.ms.expertsearchservices.domain.sort.ExpertSortFilter;

@Service
public class ExpertSearchService {

    ExpertRepository expertRepository;
    
    @Autowired
    public ExpertSearchService(ExpertRepository expertRepository) {
        this.expertRepository = expertRepository;
    }
    
    public List<Expert> getExpertListByName(final String name) {
        return expertRepository.findExpertByName(name);
    }
    
    public List<Expert> getExpertListByDescription(final String description) {
        return expertRepository.findExpertByDescriptionContainingIgnoreCase(description);
    }
    
    public List<Expert> getExpertListByExpertSeaarchFilterAndExpertSortFilter(final ExpertSearchFilter expertSearchFilter,
            final ExpertSortFilter expertSortFilter) {
        ExpertSearchFilterBuilder expertSearchFilterBuilder = new ExpertSearchFilterBuilder();
        Example<Expert> expertExample = expertSearchFilterBuilder.getExampleExpertListByExpertSearchFilter(expertSearchFilter);
        
        ExpertSortFilterBuilder expertSortFilterBuilder = new ExpertSortFilterBuilder();
        Sort expertSort = expertSortFilterBuilder.forExpertSortFilter(expertSortFilter);
        
        return expertRepository.findAll(expertExample, expertSort);
    }
    
    public Expert saveExpert(final ExpertStoreRequest expertStoreRequest) {
    	return expertRepository.save(new Expert(expertStoreRequest));
    }
}
