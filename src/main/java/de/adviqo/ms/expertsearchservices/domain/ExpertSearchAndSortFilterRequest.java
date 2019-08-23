package de.adviqo.ms.expertsearchservices.domain;

import de.adviqo.ms.expertsearchservices.domain.search.ExpertSearchFilter;
import de.adviqo.ms.expertsearchservices.domain.sort.ExpertSortFilter;

public class ExpertSearchAndSortFilterRequest {
	
	private ExpertSearchFilter expertSearchFilter;
	private ExpertSortFilter expertSortFilter;
	
	public ExpertSearchFilter getExpertSearchFilter() {
		return expertSearchFilter;
	}
	
	public void setExpertSearchFilter(ExpertSearchFilter expertSearchFilter) {
		this.expertSearchFilter = expertSearchFilter;
	}
	
	public ExpertSortFilter getExpertSortFilter() {
		return expertSortFilter;
	}
	
	public void setExpertSortFilter(ExpertSortFilter expertSortFilter) {
		this.expertSortFilter = expertSortFilter;
	}

	@Override
	public String toString() {
		return "ExpertSearchAndSortFilterRequest [expertSearchFilter=" + expertSearchFilter + ", expertSortFilter="
				+ expertSortFilter + "]";
	}
}
