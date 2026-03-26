# IDATT2003v26-millions Portofolio Project Spring 2026 :octocat:

[//]: # (TODO: Fill inn your name and student ID)
[//]: # (TODO: Mappe-2025-Marjoni-fj)

**STUDENT NAME =**
<br>
**STUDENT ID =**

## Project description💻

[//]: # (TODO: Write a short description of your project/product here.)
This Java-based application, developed using Maven...

## Project structure 📁

---
The project follows a standard Maven layout and is organized into clearly separated packages according to responsibility-driven design (RDD).
All source files are stored under the `src` directory.

### Main Package Structure (`src/main`)

<pre>

</pre>
[//]: # (TODO: Describe the structure of your project here. How have you used packages in your structure. Where are all sourcefiles stored. Where are all JUnit-test classes stored. etc.)

### 📦 Package Responsibilities

#### Models

#### Controller

#### View

#### Utils

### JUnit Tests (`src/test`)

The JUnit tests are stored under `src/test/java/edu/ntnu/idi/idatt2003/group18v26` and mirror the main package structure. These tests cover both positive and negative test of all classes (except `App.java` and UI classes) and their methods ensuring program reliability according to the specification given in the portofolie project descriptions
<pre>
</pre>

### Maven Layout

The project uses the standard Maven directory structure, which ensures:

* clean separation of source and test files
* compatibility with IDEs such as IntelliJ, VS Code, and Eclipse
* maintainability and easy future extensions (e.g., persistence or additional views)

---

## Link to repository📚

[//]: # (TODO: Include a link to your GitHub repository here.)
[GitHub Repository - IDATT2003 Mappevurdering 2026](https://github.com/LucyCiara/IDATT2003v26-millions)

---

## How to run the project📝

[//]: # (TODO: Describe how to run your project here. What is the main class? What is the main method?
What is the input and output of the program? What is the expected behaviour of the program?)

**Requirements:**  

* Java JDK 25  
* Maven  
* IDE (Ideally IntelliJ or VSCode with Java Extension Pack)
**Steps:**

1. **Clone repository**
   Clone the Repository from GitHub

2. **Open the Project:**  
   Open VS Code and select **File > Open Folder**, navigating to the root folder of the project (containing `pom.xml`).

3. **Build the Project:**  
   Open the terminal in VS Code (`Ctrl + ~`) and run:  

   ```bash
   mvn clean compile
   
4. **Run the Application:**
  Start the program by running the main class:

    ```bash
   mvn javafx:run
   
5. **Input and Output**

* Input:
* Output:
  
1. **Excpected behavior:**
The program allows the user to:

---

## How to run the tests 🧪

This project uses JUnit 5 for unit testing.
All test classes mirror the main package structure and are stored in `src/test`

* ### Open the Project

   Open VS Code and select **File > Open Folder**, navigating to the root folder of the project (containing `pom.xml`).

* ### Run all tests

   To execute the full test suite, run:

   ```bash
   mvn clean test

This command:

   1. Cleans old build files
   2. Compiles the main source code
   3. Compiles the tests
   4. Runs all JUnit tests

---

* ### Viewing test results

After the tests finish, Maven creates detailed reports here:
`target/surefire-reports/`

Each report includes:

   1. Test class summaries
   2. Stack traces for any failures
   3. Execution times
   4. Running tests in an IDE

[//]: # (TODO: Describe how to run the tests here.)

## References 🔗

[//]: # (TODO: Include references here, if any. For example, if you have used code from the course book, include a reference to the chapter.
Or if you have used code from a website or other source, include a link to the source.)
References are included in the project report.

---
"""
