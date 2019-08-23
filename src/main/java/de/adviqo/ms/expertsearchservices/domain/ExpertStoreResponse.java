package de.adviqo.ms.expertsearchservices.domain;

public class ExpertStoreResponse {

	private Long dbId;
	
	public ExpertStoreResponse(Long dbId) {
		this.dbId = dbId;
	}

	public Long getDbId() {
		return dbId;
	}

	public void setDbId(Long dbId) {
		this.dbId = dbId;
	}

	@Override
	public String toString() {
		return "ExpertStoreResponse [dbId=" + dbId + "]";
	}
	
	
}
