# GAD Test Automation Framework

This project is an automated testing framework for the **GAD** application, an open-source application with a user interface (GUI) and REST API. GAD enables creating various resources, such as users, articles, comments, simple games, and more.
Link to original application in section "References".

## Project Goal

The goal of tehe project is to build a flexible test automation framework that can be easily extended with new tests. The project includes:
- Data models and page objects.
- A directory structure that supports organizing different types of tests (API, GUI, E2E).
- Test result reporting (including automatic screenshots for failed tests).
- A retry mechanism for tests that did not pass successfully.
- A simple CI/CD pipeline using GitHub Actions.

## Technologies

The project utilizes:
- **Programming language**: Java
- **Libraries**: RestAssured (API testing), Selenium (GUI testing), TestNG (test framework), ExtentReports (report generation)
- **Build tool**: Gradle
- **CI/CD tool**: Github Actions

## Project Structure

The project has the following directory structure:
- `src/test` — contains subdirectories for different types of tests:
  - `apitests` — API tests
  - `guitests` — user interface tests
  - `e2etests` — end-to-end tests
- `src/test/resources` — contains `testng.xml` files defining various test suites, e.g., for API, GUI, or E2E tests, with corresponding profiles configured in Gradle. The file also configures running tests in parallel.
- `src/main` — includes data models and page objects used in the tests.

## Running Tests


### Prerequisites

Before running the tests, make sure you have the following installed (for both linux and windows):
- **Docker** — to run the tested application.
- **Gradle** — for building the project and running tests.

### Linux

1. Make sure GAD application is running on docker. It can be executed using command:
    ```
    docker run -p 3001:3000 -d jaktestowac/gad
    ```

2. Open a terminal and navigate to the project directory.
3. Run the following command to execute the tests:
    ```
    ./gradlew clean allTests -i
    ```

### Windows

1. Similarly as in Linux.
2. Open a Command Prompt or Powershell and navigate to the project directory.
3. Run the following command to execute the tests:
    ```
    gradle.bat clean allTests -i
    ```

To run specific test suites, you can use the corresponding profiles defined in `build.gradle.kts`
- `allTests` - runs all Tests
- `apiTests` - runs only group of api tests
- `guiTests` - runs only group of GUI tests
- `e2eTests` - runs only group of end to end tests

## Types of Tests

The framework supports various types of tests:
- **UI Tests** — validate interactions with the user interface.
- **API Tests** — test functions available through the REST API.
- **End-to-End (E2E) Tests** — cover entire user workflows.

Examples of test scenarios can be found in the [SCENARIOS.md](SCENARIOS.md) file.

## CI/CD

The project uses GitHub Actions for CI/CD automation:
- The pipeline starts by executing E2E tests.
- After completing the E2E tests, API tests and other GUI scenarios are executed.

## Future Development

Plans for future development include increasing test coverage by adding new scenarios across additional areas of the application.


## Documentation and References

- [Decision Log](DECISION_LOG.md) — documents key technical choices and reasons behind them.
- [Test Scenarios](SCENARIOS.md) — contains examples of test scenarios.
- [GAD Application Repository](https://github.com/jaktestowac/gad-gui-api-demo) — link to the original GAD application on GitHub.