# L3T08-PoisedPMS
Project management system for a small structural engineering firm called “Poised”.

## Setup

# PoisePMS - Project Management System

PoisePMS is a Java-based application designed to manage projects and associated personnel using JDBC for database interactions. This system allows users to add, update, delete, and view project information, as well as manage people associated with these projects.

## Features

- **Project Management**: Add, update, delete, and view projects.
- **People Management**: Manage architects, contractors, customers, and structural engineers associated with projects.
- **Project Finalization**: Mark projects as finalized and record completion dates.
- **Project Search**: Find projects by project number or name.
- **Project Status**: View all projects, incomplete projects, and overdue projects.

## Prerequisites

Before you begin, ensure you have the following installed:

- Java Development Kit (JDK) 8 or later
- MySQL Database
- MySQL JDBC Driver

## Setup

### Database Setup

1. **Create Database**:
   - Create a MySQL database named `poisepms`.

2. **Set Up Tables**:
   - Use the following SQL scripts to set up the necessary tables:

```sql
CREATE TABLE Projects (
    project_id INT AUTO_INCREMENT PRIMARY KEY,
    project_number VARCHAR(255) NOT NULL,
    project_name VARCHAR(255) NOT NULL,
    building_type VARCHAR(255),
    physical_address VARCHAR(255),
    erf_number VARCHAR(255),
    total_fee DOUBLE,
    total_paid DOUBLE,
    deadline DATE,
    is_finalised BOOLEAN DEFAULT FALSE,
    completion_date DATE,
    architect_id INT,
    contractor_id INT,
    customer_id INT,
    structural_engineer_id INT,
    project_manager_id INT
);

CREATE TABLE Architects (
    architect_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    telephone_number VARCHAR(255),
    email_address VARCHAR(255),
    physical_address VARCHAR(255)
);

CREATE TABLE Contractors (
    contractor_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    telephone_number VARCHAR(255),
    email_address VARCHAR(255),
    physical_address VARCHAR(255)
);

CREATE TABLE Customers (
    customer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    telephone_number VARCHAR(255),
    email_address VARCHAR(255),
    physical_address VARCHAR(255)
);

CREATE TABLE StructuralEngineers (
    structural_engineer_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    telephone_number VARCHAR(255),
    email_address VARCHAR(255),
    physical_address VARCHAR(255)
);


3. **Configuration**:
   - Update the database connection details in the `DatabaseConnection` class.

## Usage

1. To compile and run the application, use the following commands:

```bash
javac -cp "mysql-connector-java-9.3.0.jar;C:\Program Files\java\jdk-21\lib\*" taskL3T08\*.java
java -cp "mysql-connector-java-9.3.0.jar;C:\Program Files\java\jdk-21\lib\*" taskL3T08.PoisePMSManager

