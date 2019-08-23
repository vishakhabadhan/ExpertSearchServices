package de.adviqo.ms.expertsearchservices.domain.sort;

import java.util.List;

public class ExpertSortFilter {
    
    private List<String> sortProperties;
    private String sortOrder;
    
    public List<String> getSortProperties() {
        return sortProperties;
    }
    
    public void setSortProperties(List<String> sortProperties) {
        this.sortProperties = sortProperties;
    }
    
    public String getSortOrder() {
        return sortOrder;
    }
    
    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }
    
    @Override
    public String toString() {
        return "ExpertSortFilter [sortProperties=" + sortProperties + ", sortOrder=" + sortOrder + "]";
    }
}
