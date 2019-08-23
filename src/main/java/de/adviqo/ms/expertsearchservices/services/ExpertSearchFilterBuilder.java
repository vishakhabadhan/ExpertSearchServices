package de.adviqo.ms.expertsearchservices.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import de.adviqo.ms.expertsearchservices.db.entities.Expert;
import de.adviqo.ms.expertsearchservices.domain.search.ExpertSearchFilter;

public class ExpertSearchFilterBuilder {
    private static final List<String> EXPERT_SEARCH_IGNORED_PROPERTY_LIST = Arrays.asList(new String[] { "id" });
    
    public Example<Expert> getExampleExpertListByExpertSearchFilter(final ExpertSearchFilter expertSearchFilter) {
        final Expert expert = forExpertSearchFilter(expertSearchFilter);
        return Example.of(expert, getExampleMatcher(EXPERT_SEARCH_IGNORED_PROPERTY_LIST));
    }

    //Protected for testing
    protected ExampleMatcher getExampleMatcher(final List<String> ignoredProperties) {
        return ExampleMatcher.matching().withIgnoreCase().withIgnoreNullValues().withIgnorePaths(ignoredProperties.toArray(new String[ignoredProperties.size()]));
    }
    
    //Protected for testing
    protected Expert forExpertSearchFilter(final ExpertSearchFilter expertSearchFilter) {
        return new Expert(expertSearchFilter.getName(), expertSearchFilter.getDescription(), expertSearchFilter.getAvailability(),
                expertSearchFilter.getLanguage(), expertSearchFilter.getPricePerMinute());
    }
}
