package de.adviqo.ms.expertsearchservices.db.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import de.adviqo.ms.expertsearchservices.db.entities.Expert;


public interface ExpertRepository extends JpaRepository<Expert, Long> {
    
    public List<Expert> findExpertByName(final String name);
    
    public List<Expert> findExpertByDescriptionContainingIgnoreCase(String description);
    
}