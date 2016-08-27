Introduction
------------------
The scope of this project is the validation of functional requirements of a web app. This procedure was developed using the following:
 * java
 * maven
 * selenium
 * testng

Testing procedure divided in 4 different test cases that checks:
 * The way movies are presented
 * The behavior of a search procedure in the page
 * The navigation procedure in the page
 * An end-to-end test that checks the whole functionality of the page

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

