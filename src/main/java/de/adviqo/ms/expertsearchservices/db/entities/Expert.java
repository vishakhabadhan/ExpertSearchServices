package de.adviqo.ms.expertsearchservices.db.entities;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import de.adviqo.ms.expertsearchservices.domain.ExpertStoreRequest;

@Entity
@Table(name = "TBL_MS_EXPERT")
public class Expert {
    
    @Id
    @GeneratedValue(generator = "ExpertSeq")
    @SequenceGenerator(name = "ExpertSeq", sequenceName = "SEQ_TBL_MS_EXPERT", allocationSize = 1)
    @Column(name = "ID", nullable = false, updatable = false)
    private long id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "availability")
    private String availability;
    
    @Column(name = "language")
    private String language;
    
    @Column(name = "pricePerMinute")
    private BigDecimal pricePerMinute;
    
    public Expert() {
        
    }
    
    public Expert(ExpertStoreRequest expertStoreRequest) {
        this.name = expertStoreRequest.getName();
        this.description = expertStoreRequest.getDescription();
        this.availability = expertStoreRequest.getAvailability();
        this.language = expertStoreRequest.getLanguage();
        this.pricePerMinute = expertStoreRequest.getPricePerMinute();
    }
    
    public Expert(String name, String description, String availability, String language, BigDecimal pricePerMinute) {
        this.name = name;
        this.description = description;
        this.availability = availability;
        this.language = language;
        this.pricePerMinute = pricePerMinute;
    }
    
    public long getId() {
        return id;
    }
    
    public void setId(long id) {
        this.id = id;
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
        return "Expert [id=" + id + ", name=" + name + ", description=" + description + ", availability=" + availability + ", language=" + language
                + ", pricePerMinute=" + pricePerMinute + "]";
    }
}