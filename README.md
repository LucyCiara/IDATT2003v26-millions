# MILLIONS - IDATT2003 Portfolio Project Spring 2026 :octocat: 
---

[//]: # (TODO: Fill inn your name and student ID)
[//]: # (TODO: Mappe-2025-Marjoni-fj)

**TEAM 18 STUDENT NAMES**
<br>
**Fredrik Jonathan Marjoni**
<br>
**Lucy Ciara Herud-Thomassen**


## Project description💻
[//]: # (TODO: Write a short description of your project/product here.)
This Java-based application, developed using Maven allows players to engage in a simulated stock market environment where players have the ability to trade shares, manage a portfolio, and track transactions in a simplified, dynamic exchange.
### Key Features
- Starting a new game with player name, starting capital, and CSV stock data input
- Search/filter stocks and display historical statistics and weekly gainers/losers
- Buy and sell shares through a GUI showing holdings and transaction costs
- Maintain a searchable transaction history
- Advance trading weeks with updated stock prices
- Continuously display player net worth and status
- Allow players to liquidate portfolios and exit the application

## Project structure 📁
---
The project follows a standard Maven layout and is organized into clearly separated packages according to responsibility-driven design (RDD).
All source files are stored under the `src` directory.

### Main Package Structure (`src/main`)

<pre>
edu/ntnu/idi/idatt2003/group18v26/
├── model/
│   ├── Player.java
│   ├── Exchange.java
│   ├── GameObserver.java
│   ├── property/          (Portfolio, Stock, Share)
│   ├── transaction/       (Transaction, Purchase, Sale)
│   ├── persistence/ (Game saving)
│   └── filehandling/      (CsvStockReader, CsvStockWriter, JsonGamestateReader, JsonGamestateWriter)
├── control/
│   ├── GameController.java
│   └── NavigationController.java
└── view/
    ├── pages/            (TitlePage, GamePage, NewGamePanel)
    ├── components/       (UI components: buttons, panels, etc.)
    └── App.java
</pre>
[//]: # (TODO: Describe the structure of your project here. How have you used packages in your structure. Where are all sourcefiles stored. Where are all JUnit-test classes stored. etc.)

### 📦 Package Responsibilities

#### Models: Business logic and data entities
Core business logic: Player state, Exchange operations, Portfolio management, Stock/Share data structures, Transaction handling, CSV file I/O
* model/property: Financial entities: Exchange (market), Stock (individual securities), Share (portfolio holdings), Portfolio (player's holdings collection)
* model/transaction: Transaction operations: Purchase and Sale transactions, Transaction archive and history tracking
* model/filehandling: Data persistence: Reading/writing CSV stock data, managing external data sources

#### Controller:  Bridge between UI and business logic
Application coordination: GameController (game flow, model-view coordination), NavigationController (UI page switching/navigation)

#### View: JavaFX UI components
JavaFX UI: Pages (main screens), Components (reusable UI elements like buttons and panels), App (entry point)

#### Utils:  Helper functions
Input validation and defensive programming: Validates parameter types (String, Object, BigDecimal, int) before they're used in critical business logic;
throws IllegalArgumentException with descriptive messages for null, blank, or invalid values; prevents common errors like null pointer exceptions and invalid money amounts before they propagate through the application



### JUnit Tests (`src/test`)

The JUnit tests are stored under `src/test/java/edu/ntnu/idi/idatt2003/group18v26` and mirror the main package structure. These tests cover both positive and negative test of all classes (except `App.java` and UI classes) and their methods ensuring program reliability according to the specification given in the portofolie project descriptions
<pre>
    edu.ntnu.idi.idatt2003.group18v26/
├── AppTest.java                                (Integration entry point)
├── model/
│   ├── PlayerTest.java
│   ├── property/
│   │   ├── ExchangeTest.java
│   │   ├── StockTest.java
│   │   ├── ShareTest.java
│   │   └── PortfolioTest.java
│   ├── transaction/
│   │   ├── PurchaseTest.java
│   │   ├── SaleTest.java
│   │   ├── PurchaseCalculatorTest.java
│   │   ├── SaleCalculatorTest.java
│   │   └── TransactionArchiveTest.java
│   └── filehandling/
│       ├── CsvStockReaderTest.java
│       └── CsvStockWriterTest.java
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

**Requirements:**  

* Java JDK 25  
* Maven  
* IDE (Ideally IntelliJ or VSCode with Java Extension Pack)
* JavaFX SDK 25.0.1

**Run With Maven:**      (Windows + Mac + Linux)

1. **Download and Unzip Project:**
    Download project zip from the repository.

2. **Navigate to Project Folder:**
    Navigate to project folder in the terminal.

    ```bash
    cd path/to/project/     (linux + mac)
    cd path\to\project\     (windows)
    ```

3. **Run the Application:**
    Start the program by running the main class:

    ```bash
    mvn javafx:run
    ```

### Expected behavior:
<br>
The program allows the user to:

- Starting a new game with player name, starting capital, and CSV stock data input
- Search/filter stocks and display historical statistics and weekly gainers/losers
- Buy and sell shares through a GUI showing holdings and transaction costs
- Maintain a searchable transaction history
- Advance trading weeks with updated stock prices
- Continuously display player net worth and status
- Allow players to liquidate portfolios and exit the application

---

## How to run the tests 🧪

This project uses JUnit 5 for unit testing.
All test classes mirror the main package structure and are stored in `src/test`

### Open the Project
   Navigate to project folder in the terminal (containing `pom.xml`).

### Run all tests

   To execute the full test suite, run:

   ```bash
   mvn clean test
   ```

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
---
For more references and project details, kindly refer yourself to the project report and project description


