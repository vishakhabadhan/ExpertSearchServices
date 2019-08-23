# ExpertSearchServices

## Project Starter - ExpertSearchServiceStarter.java class
ResponseEntity exception handling (controller) - RestResponseEntityExceptionHandler.java

# Swagger api handler - 
SwaggerConfig.java can be connected on http://localhost:8080/swagger-ui.html when application is up and running. All api definitions are available on it.

## DESIGN -
### Decisions -

#  Search By Language
Spring data jpa (JpaRepository) is used to perform searching and sorting. 

Tried to create more generic searching and sorting mechanism using ExampleMatcher and Sort while performing searchByLanguage.
We can provide several search and sort parameters depends on requirement.

# Spring content negotiation -
# Set "Content-Type" and "Accept" as per xml and json input output requirements respectively.
## Content-Type (Request type) - application/xml OR application/json
## Accept (response type) - application/xml OR application/json

### localhost:8080/expert/searchByLanguage
##### JSON Request
	{
		"expertSearchFilter" : {	
				"language" :"JAVA"
			},
		"expertSortFilter" : {
			"sortProperties" :["pricePerMinute", "availability"],
			"sortOrder" :"ASC"
			}
	} 
	
##### XML Request
	<ExpertSearchAndSortFilterRequest>
	   <expertSearchFilter>
	      <language>JAVA</language>
	   </expertSearchFilter>
	   <expertSortFilter>
	      <sortOrder>ASC</sortOrder>
	      <sortProperties>
	         <element>pricePerMinute</element>
	         <element>availability</element>
	      </sortProperties>
	   </expertSortFilter>
	</ExpertSearchAndSortFilterRequest>

# Search By Description
SQL "like" query does the job while using "ContainingIgnoreCase" from JpaRepository.
	
### localhost:8080/expert/searchByDescription
##### JSON Request
	
	{	
		"description" :"developer"
	}
	
##### XML Request
	<ExpertSearchFilter>
		<description>developer</description>
	</ExpertSearchFilter>
	
# Search By Name
SQL "=" expression does the job while using spring jap repository.

### localhost:8080/expert/searchByName
##### JSON Request

	{	
		"name" :"vishakha badhan"
	}

##### XML Request
	<ExpertSearchFilter>
		<name>vishakha badhan</name>
	</ExpertSearchFilter>

There are several other wrapper objects written inside code, are used to provide request and response.

# An additional service "store" is created. It is used to store data, in order to perform search operations.

### localhost:8080/expert/store
##### JSON Request
	{
		"name": "vishakha badhan",
		"availability": "online",
		"description": "JAVA DEVELOPER",
		"pricePerMinute" : "0.70",
		"language" :"Java"
	}

##### XML Request
	<ExpertStoreRequest>
	   <availability>online</availability>
	   <description>JAVA DEVELOPER</description>
	   <language>Java</language>
	   <name>vishakha badhan</name>
	   <pricePerMinute>22.10</pricePerMinute>
	</ExpertStoreRequest>
# H2 DATABASE -
## Database can be connected on http://localhost:8080/h2-console 
### JDBC URL - jdbc:h2:mem:adviqo_expert_search_db 
### User name- sa 
### password No password required.

DB configuration is available in application.yml inside src/main/resources folder structure.

#TESTING Folder - 

## Integration testing - 
#### src/testint/java
### (Project prepared in Eclipse due to that the folder structure src/testint/java may or may not be available as source folder. In case if not, then just find it in project directory and configure them as select java folder (from src/testint/java) and "Build Path -> Use as a source folder).

Spring runner is used in "Transactional" mode.

ExpertSearchServiceIntTest.java (Service) - SpringRunner is used.
ExpertSearchControllerIntTest.java (Controller) - RestTemplate is used.

## Unit testing -
#### src/test/java

For unit testing mockito is used to provide mock. Unit testing is performed layer by layer (bottom to up) which means, once service layer testing is performed, mock service is used in controller to evaluate desired outcome.

ExpertSearchControllerTest.java
ExpertTest.java
ExpertSearchFilterBuilderTest.java
ExpertSearchServiceTest.java
ExpertSortFilterBuilderTest.java

# POM.XML

1) H2 DB dependency.
2) Springforx for swagger api.
3) Jackson databind for xml and json request and response.
4) Spring boot jpa, starter, tomcat etc.

# Project build -

a) mvn package OR mvn clean install.
It will create ExpertSearchServices.jar in target folder.

b) java -jar ExpertSearchServices.jar
Execute it using command prompt. OR a)It is a SpringBoot project and can be simply executed as a java project from ExpertSearchServiceStarter.java class.

