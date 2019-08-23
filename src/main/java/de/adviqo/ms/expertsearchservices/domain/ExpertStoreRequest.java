package de.adviqo.ms.expertsearchservices.domain;

import java.math.BigDecimal;
import java.util.Arrays;

public class ExpertStoreRequest {

	private String name;
    private String description;
    private String availability;
    private String language;
    private BigDecimal pricePerMinute;
    
    public ExpertStoreRequest() {
	}

	public ExpertStoreRequest(String name, String description, String availability, String language,
			BigDecimal pricePerMinute) {
		this.name = name;
		this.description = description;
		this.availability = availability;
		this.language = language;
		this.pricePerMinute = pricePerMinute;
	}

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
    	Arrays.asList(Availability.values()).stream().forEach(p -> {
    		if(availability.equalsIgnoreCase(p.toString())) {
    			this.availability = availability;
    		}
    	});       
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
