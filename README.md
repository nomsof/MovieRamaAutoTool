Introduction
------------------
The scope of this project is the validation of functional requirements of a web app. This procedure was developed using the following:
 1. java
 2. maven
 3. selenium
 4. testng

Testing procedure divided in 4 different test cases:
 1. A simple check-test about the way that the movies are present
 2. A test that checks the behavior of a search procedure in the page
 3. A navigation test that checks the navigation procedure in the page
 4. Finally, an end-to-end test that checks the whole functionality of the page

Tests-Description
------------------

**MovieElementsTest:**<br />
Checks if every movie in the page contains the appropriate information.

**MovieSearchTest:**<br />
Executes searches on the page and checks if the results are the expected.

**PageNavigationTest:**<br />
 * Navigation functionality is tested by selecting the next/previous page or a random page and checks the results. 
 * A random page size is selected and check if the results size is correct.

**MovieRamaTest:**<br />
An end-to-end test which executes the below actions:
 1. search 
 2. clear search
 3. goToNextPage
 4. goToPreviousPage
 5. selectRandomPage
 6. selectPageSize

Execution
------------------

To run the automation tool the following command must be executed in the sourcefile of the project

```bash
mvn clean install -p selenium
```

where selenium is the profile which : 
 1. downloads the chromedriver, that is required in order to run the selenium,
 2. runs the testng tests in the order defined in testng.xml file under the src/test/resources.

