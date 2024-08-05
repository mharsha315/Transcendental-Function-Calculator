# Transcendental Function Calculator

## Overview
The Transcendental Function Calculator is a Java-based GUI application designed to compute functions of the form \(ab^x\). This project incorporates semantic versioning, comprehensive unit testing, and adheres to coding standards through tools like Checkstyle and SonarLint.

## Features
- 🎨 **GUI Interface:** Built with Swing for intuitive user interaction.
- 🔢 **Semantic Versioning:** Utilizes a `Version.java` class to manage application versioning.
- 🧪 **JUnit Testing:** Includes robust test cases to ensure functionality across various scenarios.
- 📏 **Coding Standards:** Enforced through Checkstyle and SonarLint for consistent code quality.
- 🔍 **Debugging:** Uses Java Debugger (JDB) for effective troubleshooting.

## Prerequisites
Ensure you have the following installed on your machine:
- **Java Development Kit (JDK) 17 or later:**
- **IntelliJ IDEA (Community Edition or Ultimate):** [Download IntelliJ IDEA](https://www.jetbrains.com/idea/download/)
- **Git for version control:** [Download Git](https://git-scm.com/downloads)

## Installation

### 1. Clone the Repository
First, clone the repository to your local machine:

```bash
git clone https://github.com/your-username/your-repo-name.git
```

### 2. Set Up in IntelliJ IDEA

#### Without Gradle or Maven
**Open IntelliJ IDEA:**

1. Launch IntelliJ IDEA.
2. Go to **File > New > Project from Existing Sources...**.
3. Select the project directory where you cloned the repository.

**Configure the JDK:**

1. Go to **File > Project Structure > Project**.
2. Set the Project SDK to JDK 17 or later. If it’s not listed, click **New...** and navigate to the JDK installation directory.

**Add Libraries for JUnit and TestNG:**

1. Go to **File > Project Structure > Libraries**.
2. Click **+** and select **From Maven...**.
3. Add the following dependencies:
   - JUnit: `org.junit.jupiter:junit-jupiter:5.8.1`
   - TestNG: `org.testng:testng:7.4.0`
4. Ensure these libraries are added to your project classpath.

**Install Checkstyle and SonarLint Plugins:**

1. Go to **File > Settings > Plugins**.
2. Search for "Checkstyle" and "SonarLint" and install them.
3. Configure Checkstyle using the `sun_checks.xml` file provided in the repository.

### 3. Running the Application
- Open `WelcomePage.java`.
- Click the **Run** button to execute the application.
- Use the GUI to input values for `a`, `b`, and `x`, then calculate the function \(ab^x\).

### 4. Running Tests
- Open `WelcomePageTest.java`.
- Right-click on the file and select **Run 'WelcomePageTest'** to execute the JUnit tests.

### 5. Debugging with JDB
- In IntelliJ, set breakpoints by clicking in the gutter next to the line numbers.
- Right-click on the `WelcomePage.java` file and select **Debug 'WelcomePage'**.
- Use the debugging controls to step through the code and inspect variables.

## Java Files

- **`WelcomePage.java`:** Handles GUI creation, user input, and validation.
- **`Version.java`:** Manages semantic versioning of the application.
- **`WelcomePageTest.java`:** Contains JUnit test cases to validate core functionalities across scenarios.
- **`package-info.java`:** Provides package-level documentation.

## Tools Used

- **JUnit and TestNG:** Used for creating and running unit tests. Ensure you have added these as dependencies in IntelliJ.
- **Checkstyle:** Used for ensuring coding standards are followed. Configured using `sun_checks.xml`.
- **SonarLint:** Provides real-time feedback on code quality within IntelliJ.
- **Java Debugger (JDB):** Used for step-by-step debugging of the application.
