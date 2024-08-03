# FitPeo Revenue Calculator Automation

This project automates the interaction with the FitPeo Revenue Calculator page using Selenium WebDriver in Java. The automation script adjusts the slider to a specific value, clicks on checkboxes, and validates the final amount.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Maven
- An IDE (e.g., IntelliJ IDEA, Eclipse)
- Google Chrome browser
- ChromeDriver

## Setup Instructions

### 1. Clone the Repository

Clone this repository to your local machine using the following command:

```bash
git clone https://github.com/Swathisp43/Assignment_FITPEO.git
```

### 2. Open in Eclipse and open the Assignment_FITPEO folder.

## Ensure the following dependencies are added to your pom.xml file:

```
<dependencies>
    <!-- Selenium Java -->
    <dependency>
        <groupId>org.seleniumhq.selenium</groupId>
        <artifactId>selenium-java</artifactId>
        <version>4.5.0</version>
    </dependency>
    <!-- WebDriver Manager -->
    <dependency>
        <groupId>io.github.bonigarcia</groupId>
        <artifactId>webdrivermanager</artifactId>
        <version>5.3.0</version>
    </dependency>
</dependencies>
```
### 3. Configure the Project
## Ensure the FitPeoAutomation.java file is included in your project under src/main/java.

### 4. Run the Tests
Open a terminal in your project directory.

Use Maven to run the tests.
