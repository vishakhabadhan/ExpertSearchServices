package de.adviqo.ms.expertsearchservices.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import de.adviqo.ms.expertsearchservices.domain.sort.ExpertSortCriteria;
import de.adviqo.ms.expertsearchservices.domain.sort.ExpertSortFilter;

public class ExpertSortFilterBuilder {
    
    public Sort forExpertSortFilter(final ExpertSortFilter expertSortFilter) {
        List<String> sortProperties = new ArrayList<>();
        
        expertSortFilter.getSortProperties().stream().forEachOrdered(p -> Arrays.asList(ExpertSortCriteria.values()).forEach(q -> {
            if (q.toString().equalsIgnoreCase(p)) {
                sortProperties.add(q.toString());
            }
        }));
        Sort.Direction SORT_DIRECTION = toSortDirection(expertSortFilter);
        
        if(sortProperties.isEmpty()) {
        	sortProperties.add("id");
        }
        
        return new Sort(SORT_DIRECTION, sortProperties);
    }
    
    //protected for testing
    protected Direction toSortDirection(final ExpertSortFilter expertSortFilter) {
        return expertSortFilter.getSortOrder() != null && !expertSortFilter.getSortOrder().isEmpty()
                && !"ASC".equalsIgnoreCase(expertSortFilter.getSortOrder())
                ? Sort.Direction.DESC : Sort.Direction.ASC;
    }
}
