# QA Automation Pet Project

Automated API (and future UI) testing project for [automationexercise.com](https://automationexercise.com/).  
Demonstrates end-to-end test automation using Java, Rest Assured, TestNG, and Selenium WebDriver.

## Table of Contents

- [About](#about)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Setup and Installation](#setup-and-installation)
- [Running Tests](#running-tests)
- [Reporting](#reporting)

## About

This project currently focuses on **API testing**:

- User account management
- Authentication
- Products and brands endpoints

UI automation is in progress, Selenium WebDriver will be used for future browser-based tests.

## Technologies Used

- **Java** – programming language
- **Rest Assured** – API testing framework
- **TestNG** – test framework
- **Selenium WebDriver** – for future UI tests
- **Allure** – test reporting

## Project Structure
- **pom.xml** – Maven project configuration.
- **README.md** – Project documentation.

- **src/main/java/**
    - **base/** – Base classes and utilities.
    - **pageobjects/api/** – API models and page objects.
        - **account/** – User-related classes (User, UserApiManager, etc.).
        - **brands/** – Brand-related classes (Brand, ResponseBrands).
        - **products/** – Product-related classes (Category, Product, ResponseProducts, UserType).
    - **utils/** – Utility classes (Utils.java).

- **src/test/java/**
    - **base/** – Base test classes, listeners, and custom annotations.
    - **testUtils/** – Helper classes for tests.
    - **tests/api/** – API test cases.
    - **tests/ui/** – UI test cases (in progress).

- **src/test/resources/**
    - **GlobalData.properties** – Test configuration data.
    - **testng/testng.xml** – TestNG configuration file.

- **target/** – Compiled classes, test reports, and output files.


## Setup and Installation

1. Clone the repository:

bash
git clone https://github.com/VGebrych/qa-automation-petproject.git
cd qa-automation-petproject

2. Ensure Java 11 or higher is installed. 
3. Install Maven if not already installed. 
4. Install dependencies: mvn clean install

## Running Tests
All Tests: mvn test

## Reporting
After tests execution, generate Allure report:
mvn allure:serve 

Or if you have Allure installed locally:
allure serve target/allure-results

