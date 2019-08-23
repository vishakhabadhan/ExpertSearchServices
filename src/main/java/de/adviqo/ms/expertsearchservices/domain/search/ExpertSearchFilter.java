package de.adviqo.ms.expertsearchservices.domain.search;

import java.math.BigDecimal;

public class ExpertSearchFilter {
    
    private String name;
    private String description;
    private String availability;
    private String language;
    private BigDecimal pricePerMinute;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getAvailability() {
        return availability;
    }
    
    public void setAvailability(String availability) {
        this.availability = availability;
    }
    
    public String getLanguage() {
        return language;
    }
    
    public void setLanguage(String language) {
        this.language = language;
    }
    
    public BigDecimal getPricePerMinute() {
        return pricePerMinute;
    }
    
    public void setPricePerMinute(BigDecimal pricePerMinute) {
        this.pricePerMinute = pricePerMinute;
    }
    
    @Override
    public String toString() {
        return "ExpertSearchFilter [name=" + name + ", description=" + description + ", availability=" + availability + ", language=" + language
                + ", pricePerMinute=" + pricePerMinute + "]";
    }
}
