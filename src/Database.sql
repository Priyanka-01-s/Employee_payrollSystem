-- UC_1 : Ability to create a payroll service database

create database payroll_service;

show databases;

-- OUTPUT
-- h2h_schema
-- information_schema
-- mysql
-- payroll_service
-- performance_schema
-- sys

use payroll_service;

-- UC_2 : Ability to create a employee payroll table in the payroll service database to manage employee payrolls

CREATE TABLE EMPLOYEE_PAYROLL_DB (
    EMP_ID INT AUTO_INCREMENT PRIMARY KEY,
    NAME VARCHAR(255),
    SALARY DECIMAL(10, 2),
    START_DATE DATE
);

DESC EMPLOYEE_PAYROLL_DB;

-- OUTPUT
-- EMP_ID	int	NO	PRI		auto_increment
-- NAME	varchar(255)	YES			
-- SALARY	decimal(10,2)	YES			
-- START_DATA	date	YES			

-- UC_3 : Ability to create employee payroll data in the payroll service database as part of CURD Operation - Use payroll_service database

INSERT INTO EMPLOYEE_PAYROLL_DB(NAME, SALARY, START_DATE)
VALUES
    ('Priyanka', 15000, '2023-02-01'),
    ('Bob', 10000, '2023-09-11'),
    ('John', 20000, '2023-03-10'),
    ('Doe', 5000, '2023-04-07');

-- UC_4 : Ability to retrieve all the employee payroll data that is added to payroll service database

SELECT * FROM EMPLOYEE_PAYROLL_DB;

-- OUTPUT
-- 1	Priyanka	15000.00	2023-02-01
-- 2	Bob	10000.00	2023-09-11
-- 3	John	20000.00	2023-03-10
-- 4	Doe	5000.00	2023-04-07
			
-- UC_5 : Ability to retrieve salary data for a particular employee as well as all employees who have joined in a particular data range 
-- from the payroll service database

SELECT * FROM EMPLOYEE_PAYROLL_DB WHERE NAME = 'Priyanka';

-- OUTPUT
-- 1	Priyanka	15000.00	2023-02-01
			
SELECT * FROM EMPLOYEE_PAYROLL_DB 
WHERE START_DATE BETWEEN CAST('2023-03-15' AS DATE) AND CURDATE()
AND SALARY BETWEEN 10000 AND 20000; 

-- OUTPUT
-- 2	Bob	10000.00	2023-09-11
		
-- UC_6 : Ability to add Gender to Employe Payroll Table and Update the Rows to reflect the correct Employee Gender

UPDATE EMPLOYEE_PAYROLL_DB
SET GENDER = 'M'
WHERE NAME IN ('Bob', 'John');

DESC EMPLOYEE_PAYROLL_DB;

SELECT * FROM EMPLOYEE_PAYROLL_DB;

-- OUTPUT
-- 1	Priyanka		15000.00	2023-02-01
-- 2	Bob	M	10000.00	2023-09-11
-- 3	John	M	20000.00	2023-03-10
-- 4	Doe		5000.00	2023-04-07

-- UC_7 : Ability to find sum, average, min, max and number of male and female employees

--FINDING THE TOTAL SALARY
SELECT NAME, GENDER, SUM(SALARY) AS TOTAL_SALARY
FROM EMPLOYEE_PAYROLL_DB
GROUP BY NAME, GENDER;

-- OUTPUT
-- Priyanka		15000.00
-- Bob	M	10000.00
-- John	M	20000.00
-- Doe		5000.00

--FINDING THE AVERAGE SALARY
SELECT NAME, GENDER, AVG(SALARY) AS AVERAGE_SALARY
FROM EMPLOYEE_PAYROLL_DB
GROUP BY NAME, GENDER;

-- OUTPUT
-- Priyanka		15000.000000
-- Bob	M	10000.000000
-- John	M	20000.000000
-- Doe		5000.000000

--FINDING THE MINIMUM SALARY
SELECT NAME, GENDER, MIN(SALARY) AS MINIMUM_SALARY
FROM EMPLOYEE_PAYROLL_DB
GROUP BY NAME, GENDER
ORDER BY MINIMUM_SALARY;

-- OUTPUT
-- Doe		5000.00
-- Bob	M	10000.00
-- Priyanka		15000.00
-- John	M	20000.00

--FINDING THE DESC SALARY
SELECT NAME, GENDER, MIN(SALARY) AS MINIMUM_SALARY
FROM EMPLOYEE_PAYROLL_DB
GROUP BY NAME, GENDER
ORDER BY MINIMUM_SALARY DESC;

-- OUTPUT
-- John	M	20000.00
-- Priyanka		15000.00
-- Bob	M	10000.00
-- Doe		5000.00

--COUNTING THE GENDER OF EMPLOYEES
SELECT GENDER, COUNT(*) AS NUMBER_EMPLOYEES
FROM EMPLOYEE_PAYROLL_DB
GROUP BY GENDER;

-- OUTPUT
-- F	2
-- M	2