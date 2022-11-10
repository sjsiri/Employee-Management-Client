# Design Document

## _AmazonContactStorageService_ Design

## 1. Problem Statement

Amazon Elite Contact Service is a highly advanced contact book that will make communication throughout the workplace a lot more efficient.
We currently have an entire list of all the employee contacts but admins and employees have requested for a more streamlined view of all the contacts based on the users role and job department.

This design document describes the main use cases and functionality of Amazon Elite Contact Service, a new, native AWS service that will provide users a cleaner, more informative means to view contact information.
It is designed to interact with the Employee Management Client(which allows admins or employees to view contact information based on their role and which department they are in).


## 2. Top Questions to Resolve in Review

_List the most important questions you have about your design, or things that you are still debating internally that you might like help working through._

1. Management consists of supervisor in each department
2. Limited contact information refers to just the email of either the employee or management
3. Can U5 and U2 use the same endpoint? Or is it better to separate the two?
4. Is a separate department table with all the managers of each department required?


## 3. Use Cases

_This is where we work backwards from the customer and define what our customers would like to do (and why). You may also include use cases for yourselves (as developers), or for the organization providing the product to customers._

U1. As an admin, I want to be able to view all employees contact info, so that I can contact them.

U2. As an admin, I want to be able to view employee contact info based on the department to contact them.

U3. As an admin, I want to be able to create a new employee and then add their contact info plus which department they are in.

U4. As an admin I want to be able to update an employee’s contact information, so that I change their information if needed.

U5. As an admin I want to be able to view a list of all the departments and the current employee counts for each.

U6. As an admin, I want to be able to get a specific employee based on their name or employeeID, so I can contact them specifically.

*U7. As an employee I want to be able to view limited employee contact info (name + email) in my department.


*EXTRA FEATURES


## 4. Project Scope

_Clarify which parts of the problem you intend to solve. It helps reviewers know what questions to ask to make sure you are solving for what you say and stops discussions from getting sidetracked by aspects you do not intend to handle in your design._


### 4.1. In Scope

_Which parts of the problem defined in Sections 1 and 2 will you solve with this design? This should include the base functionality of your product. What pieces are required for your product to work?_

* Adding, updating, and retrieving/viewing contact information
* Functionality restrictions based on the role of the user
* Retrieving contact information based on the department
* (EXTRA FEATURE) Ability to email search results

### 4.2. Out of Scope

_Based on your problem description in Sections 1 and 2, are there any aspects you are not planning to solve? Do potential expansions or related problems occur to you that you want to explicitly say you are not worrying about now? Feel free to put anything here that you think your team can't accomplish in the unit, but would love to do with more time._

* Authentication of admins and employees is beyond the scope of this project.
* Employee Specific Section will be implemented once the Admin side is completed.
* This API does not facilitate calling employees. It is simply a means of retrieving data in a meaningful manner and provides the ability to email employees.

# 5. Proposed Architecture Overview

_Describe broadly how you are proposing to solve for the requirements you described in Section 2. This may include class diagram(s) showing what components you are planning to build. You should argue why this architecture (organization of components) is reasonable. That is, why it represents a good data flow and a good separation of concerns. Where applicable, argue why this architecture satisfies the stated requirements._

This initial iteration will provide the minimum viable product (MVP) including adding, retrieving, and updating an employee contact information for the admin role.

We will use API Gateway and Lambda to create 5 endpoints (GetEmployee, Add/UpdateEmployee, GetContactsGlobal, GetContactsByDept, AdvancedSearch) that will handle the creation, updation, and retrieval of employee information to satisfy our requirements.

We will store employee information in a table in DynamoDB.

Amazon Elite Contact Service will also provide a web interface for users. A main page providing a variety of options to add, update and view employee information.


# 6. API

## 6.1. Public Models

_Define the data models your service will expose in its responses via your *`-Model`* package. These will be equivalent to the *`PlaylistModel`* and *`SongModel`* from the Unit 3 project._

```
// EmployeeModel

String employeeId;
String firstName;
String lastName;
String jobTitle;
String email;
String deptId;
ZonedDateTime hireDate
String phoneNumber;
ZonedDateTime dateOfBirth;

```
```
// DepartmentModel

String deptId;
String deptName;

```

## 6.2. _GetEmployee Endpoint_

* Accepts `GET` requests to `/employee/:id`
* Accepts an employeeID and returns the corresponding EmployeeModel.
    * If the given employee ID is not found, will throw a
      `EmployeeNotFoundException`

![Client sends get employee form to Website View Employee Page. Website
View Employee page sends a get request to GetEmployeeActivity.
GetEmployeeActivity loads employee from contacts
database and returns.](images/UpdateEmployeeSd.png)

## 6.3 _GetContactGlobal Endpoint_

* Accepts `GET` requests to `/employee`
* Returns all the employees in all the departments in the EmployeeModel format.
    * If there is no data found, will throw a
      `NoDataFoundException`

## 6.4. GetContactsByDepartment Endpoint

* Accepts `GET` requests to `/employee/:deptId`
* Returns all the employees in the requested departments in the EmployeeModel format.
    * If there is no department found, will throw a
      `InvalidDepartmentException`

## 6.5. _UpdateEmployee Endpoint_

* Accepts `PUT` requests to `/employee/:id`
* Accepts data to update a contact including an updated
  firstName, lastName, jobTitle, deptId , dateOfBirth , email, and      
  phoneNumber. Returns the updated contact.
* If the employeeID or name is not found, will throw a   
  `EmployeeNotFoundException`
* For security concerns, we will validate the provided employee name does not
  contain invalid characters: `" ' \`
* If the employee name contains invalid characters, will throw an
  `InvalidAttributeValueException` 

![Client sends submit update employee form to Website Update/Add page. Website
Update/Add page sends an update request to UpdateEmployeeActivity.
UpdateEmployeeActivity saves updates to the contacts
database.](images/UpdateEmployeeSd.png)

## 6.6. _AddEmployee Endpoint_

* Accepts `POST` requests to `/employee/`
* Accepts data to create a new employee which includes their
  firstName, lastName, jobTitle, deptId , dateOfBirth , email, and      
  phoneNumber. Returns the new employee.
* For security concerns, we will validate the provided employee name does not
  contain invalid characters: `" ' \`
* If the employee name contains invalid characters, will throw an
  `InvalidAttributeValueException`

## 6.7 _AdvancedSearch Endpoint_

* Accepts the following parameters departmentId (REQUIRED), firstName,
  lastName, and employeeID to perform a search on the database
* Returns a list of results based on the entered search criteria.
* A departmentID is required as this is the partition key.
* If there is no data found, will throw a
  `NoDataFoundException`
* If there is no department found, will throw a
  `InvalidDepartmentException`
* For security concerns, we will validate the provided employee name does not
  contain invalid characters: `" ' \`
* If the employee name contains invalid characters, will throw an
  `InvalidAttributeValueException`

## 6.8 GetDeptGlobal 
* Accepts `GET` requests to `/department`
* Returns all the departments in the DepartmentTable format.
  * If there is no data found, will throw a
    `NoDataFoundException`

# 7. Tables

_Define the DynamoDB tables you will need for the data your service will use. It may be helpful to first think of what objects your service will need, then translate that to a table structure, like with the *`Playlist` POJO* versus the `playlists` table in the Unit 3 project._

* EmployeeTable
  - employeeId // partition key, string
  - firstName // string
  - lastName // string
  - jobTitle // string
  - Email // string
  - deptId // string (GSI Partition Key)
  - hireDate // string
  - phoneNumber // string
  - dateOfBirth // string

* DepartmentTable
  * deptId // partition key, string
  * deptName // string
   

# 8. Pages

https://www.figma.com/file/5rmM0IQhuG0wefUsezsCud/Purple-Haze?node-id=0%3A1


