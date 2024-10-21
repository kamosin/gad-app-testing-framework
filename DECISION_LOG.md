## Attaching to project external dependencies to serialize and deserialize JAVA objects to JSON format - Jackson Databind

**ID**: 003

**Status**: Decided

**Date**: 2024/09/24

**Context**:  In the process of building and testing a web application, it became necessary to convert Java objects to JSON format and vice versa for api requests and responses. We needed a robust and flexible library to handle serialization and deserialization

**Proposed solution**: Adopt the Jackson Databind library for object-to-JSON mapping and JSON-to-object deserialization

**Pros**:
- Ease of use: Simple api (ObjectMapper) to convert between Java objects and JSON.
- Performance: Efficient processing of JSON data at scale.
- Wide adoption: Well-maintained and widely used in the Java ecosystem.

**Cons**
- Learning curve: Some additional complexity when dealing with more advanced configurations, such as custom serializers/deserializers.
- Module dependency: Requires adding extra modules (e.g., jackson-datatype-jsr310) to handle certain types, like Java 8 date/time.

**Decision**: Decided

**Creator**: Kamil O

## Attaching to project external dependencies - Selenium + RestAssured

**ID**: 002

**Status**: Decided

**Date**: 2024/09/22

**Context**: The project requires a robust framework for automating tests for both web applications and APIs. Selenium is chosen for web UI automation, while RestAssured is selected for api testing. Integrating both into the test framework is essential to achieve comprehensive test coverage for both layers of the application. This decision will ensure unified test management and reporting across both types of tests.

**Proposed solution**: Integrate Selenium for automating browser-based UI tests and RestAssured for automating REST api tests within the same project. Dependencies for both tools will be added to the project via Gradle, allowing them to be used in the respective test cases.

**Pros**:
- Selenium is a widely adopted tool for UI automation, with strong community support.
- RestAssured simplifies api testing by providing a fluent interface for REST services.
- Both tools integrate well with existing Java-based test framework like TestNG
- Allows unified reporting and test execution for both UI and api tests in the same test suite.

**Cons**
- Increases the project’s external dependencies, leading to potential version conflicts or maintenance overhead.
- Requires managing two different testing paradigms (UI and api) within the same project.
- Chosen technologies might lack in some helpful features that are available for example by Playwright

**Decision**: Decided

**Creator**: Kamil O

## Setting up a project with Gradle build system with  'Gradle init' command

**ID**: 001

**Status**: Decided

**Date**: 2024/09/21

**Context**: In order to initialize the project and easily manage dependencies using build system might be beneficial to set up the project and resolve dependencies as well as managing tasks such as code compilation, test running etc.

**Proposed solution**: Using a gradle build system with use of 'gradle init' command. The tool allows to easily set up a project and manage dependencies with ease. Skeleton for directory structure is set up as well.

**Pros**:
- performance - Incremental builds and build cache significantly reduce build times by only compiling changed parts of the code. Gradle supports parallel task execution, speeding up the process for large projects.
- flexibility - Gradle is highly flexible and extensible, allowing custom build processes tailored to project needs by giving full control over tasks.
- Dependency management - Built-in dependency management with support for repositories like Maven Central simplifies the configuration by automatically fetching libraries and tools.

**Cons**
- Learning curve - Gradle’s complexity can be challenging for new users, especially when compared to simpler tools like Maven. While it offers flexibility, this comes with more intricate configurations.
- Complexity of scripts- Gradle scripts can become complex and hard to maintain as the project grows, requiring more effort to keep build files clean and understandable in large projects.
- Smaller community support compared to Maven

**Decision**: Decided
**Creator**: Kamil O