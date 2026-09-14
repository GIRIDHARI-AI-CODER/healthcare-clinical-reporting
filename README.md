\# Healthcare Clinical Reporting System



A full-stack healthcare clinical reporting system built using \*\*Java, Spring Boot, Oracle Database, SQL, PL/SQL, JDBC, and React\*\*.



The application manages healthcare data including patients, doctors, departments, appointments, clinical encounters, diagnoses, medications, laboratory tests, laboratory results, billing, and audit events.



It provides REST APIs and a React-based dashboard for operational, clinical, and analytical reporting.



\---



\## Project Overview



The \*\*Healthcare Clinical Reporting System\*\* is an enterprise-style healthcare application developed to demonstrate practical software engineering and database development skills.



The project focuses on:



\- Healthcare data management

\- Oracle SQL data retrieval and processing

\- Complex SQL joins and aggregation

\- PL/SQL stored procedure development

\- Java JDBC database connectivity

\- Spring Boot REST API development

\- Clinical reporting

\- Patient clinical analytics

\- Doctor clinical performance reporting

\- Department-level clinical statistics

\- Audit logging

\- Unit testing

\- React dashboard development

\- Git and GitHub version control



The application follows a layered architecture:



```text

React Frontend

&#x20;     |

&#x20;     v

Spring Boot REST API

&#x20;     |

&#x20;     v

Service Layer

&#x20;     |

&#x20;     v

Repository Layer

&#x20;     |

&#x20;     v

JDBC

&#x20;     |

&#x20;     v

Oracle Database

&#x20;     |

&#x20;     +---- SQL

&#x20;     |

&#x20;     +---- PL/SQL

```



\---



\# Technology Stack



\## Backend



\- Java 23

\- Spring Boot 3.5.5

\- JDBC

\- Maven

\- REST APIs

\- Java Collections

\- Exception Handling



\## Database



\- Oracle Database 26ai

\- Oracle SQL

\- PL/SQL

\- Stored Procedures

\- SYS\_REFCURSOR

\- PreparedStatement

\- CallableStatement

\- Relational Data Modeling

\- SQL Joins

\- Aggregation



\## Frontend



\- React

\- JavaScript

\- Vite

\- HTML

\- CSS

\- REST API Integration



\## Testing



\- JUnit 5

\- Mockito

\- Spring Boot Test

\- Maven Test Lifecycle



\## Development Tools



\- Git

\- GitHub

\- VS Code

\- cURL

\- SQL Developer



\---



\# Database Modules



The Oracle database contains the following healthcare modules:



```text

DEPARTMENT

DOCTOR

PATIENT

APPOINTMENT

ENCOUNTER

DIAGNOSIS

MEDICATION

LAB\_TEST

LAB\_RESULT

BILLING

AUDIT\_LOG

```



These tables represent relationships between healthcare providers, patients, appointments, clinical encounters, diagnoses, medications, laboratory data, billing information, and audit events.



\---



\# Key Features



\## 1. Patient Management



The patient management module provides:



\- Patient record retrieval

\- Patient search by ID

\- Patient demographic information

\- Patient clinical information

\- Patient-specific reporting

\- Patient clinical activity analytics



Patient information includes fields such as:



\- Patient ID

\- First name

\- Last name

\- Date of birth

\- Gender

\- Phone

\- Email

\- Blood group

\- Address

\- Status



\---



\# 2. Doctor Management



The doctor module provides:



\- Doctor record retrieval

\- Doctor specialization information

\- Department association

\- Doctor appointment information

\- Doctor clinical performance reporting



Doctor information includes:



\- Doctor ID

\- Department

\- Doctor name

\- Specialization

\- Phone

\- Email

\- Joining date

\- Status



\---



\# 3. Department Management



The department module provides:



\- Department information retrieval

\- Doctor-to-department association

\- Department-level reporting

\- Department clinical statistics



Departments can be analyzed based on:



\- Number of doctors

\- Number of patients

\- Total appointments

\- Completed appointments

\- Scheduled appointments

\- Total encounters



\---



\# 4. Appointment Management



The appointment module manages:



\- Patient appointments

\- Doctor appointments

\- Appointment time

\- Appointment reason

\- Appointment status

\- Appointment reporting



The dashboard provides appointment statistics including:



\- Total appointments

\- Scheduled appointments

\- Completed appointments



\---



\# 5. Clinical Encounter Management



Clinical encounters connect patients and doctors with healthcare activities.



The encounter module stores information such as:



\- Encounter ID

\- Patient ID

\- Doctor ID

\- Appointment ID

\- Encounter time

\- Chief complaint

\- Clinical notes

\- Encounter status



Clinical encounters are used as the foundation for diagnosis and laboratory reporting.



\---



\# 6. Diagnosis Management



The diagnosis module supports clinical diagnosis information associated with patient encounters.



Diagnosis data is used in:



\- Patient clinical reports

\- Patient clinical analytics

\- Diagnosis statistics

\- Clinical activity analysis



\---



\# 7. Medication Management



Medication information is associated with clinical encounters.



Clinical reports can include:



\- Medication

\- Dosage

\- Frequency



This allows the system to present medication information as part of a patient's clinical history.



\---



\# 8. Laboratory Management



The system manages laboratory tests and laboratory results.



\## Laboratory Test



Laboratory test information includes:



\- Test name

\- Test category

\- Normal range

\- Unit

\- Test cost

\- Status



\## Laboratory Result



Laboratory result information includes:



\- Result value

\- Result status

\- Result date

\- Remarks



Laboratory data is used in patient clinical reporting and clinical analytics.



\---



\# 9. Clinical Reporting



The system generates clinical reports by retrieving related information from multiple healthcare tables.



Clinical reports can include:



\- Patient information

\- Encounter details

\- Doctor information

\- Diagnosis

\- Medication

\- Dosage

\- Frequency

\- Laboratory information



The reporting functionality demonstrates practical SQL joins and relational data processing.



\---



\# 10. Patient Clinical Analytics



The system provides a dedicated \*\*Patient Clinical Analytics\*\* report.



For each patient, the system calculates:



\- Total encounters

\- Total diagnoses

\- Total laboratory results



The report uses Oracle SQL joins and aggregation across healthcare entities.



The logical relationship is:



```text

PATIENT

&#x20;  |

&#x20;  v

ENCOUNTER

&#x20;  |

&#x20;  +------> DIAGNOSIS

&#x20;  |

&#x20;  +------> LAB\_RESULT

```



The analytics data is exposed through a Spring Boot REST API and displayed in the React dashboard.



Example endpoint:



```text

GET /api/reports/patient-clinical-analytics

```



The current test dataset contains:



```text

Total Patients: 8

Patients With Encounters: 5

Total Diagnoses: 5

Total Lab Results: 5

```



\---



\# 11. Doctor Clinical Performance



The system provides doctor-level clinical performance reporting.



The report includes:



\- Doctor ID

\- Doctor name

\- Specialization

\- Total appointments

\- Completed appointments

\- Scheduled appointments

\- Total encounters

\- Total patients



The reporting logic is implemented using Oracle SQL/PLSQL and exposed through a Spring Boot REST API.



Example endpoint:



```text

GET /api/dashboard/doctor-clinical-performance

```



The React dashboard displays doctor clinical performance in a structured table.



\---



\# 12. Department Clinical Statistics



The system provides department-level clinical statistics.



The report includes:



\- Department name

\- Number of doctors

\- Number of patients

\- Total appointments

\- Completed appointments

\- Scheduled appointments

\- Total encounters



Example endpoint:



```text

GET /api/dashboard/department-clinical-statistics

```



This demonstrates:



\- SQL joins

\- GROUP BY

\- Aggregate functions

\- COUNT

\- COUNT(DISTINCT ...)

\- Multi-table reporting



\---



\# 13. PL/SQL Integration



The project integrates Java with Oracle PL/SQL stored procedures.



A stored procedure named:



```text

GET\_PATIENT\_CLINICAL\_SUMMARY

```



generates patient clinical information using an Oracle:



```text

SYS\_REFCURSOR

```



Java invokes the stored procedure using:



```text

CallableStatement

```



and processes the returned cursor through a:



```text

ResultSet

```



The integration flow is:



```text

Java

&#x20;  |

&#x20;  v

JDBC CallableStatement

&#x20;  |

&#x20;  v

Oracle PL/SQL Procedure

&#x20;  |

&#x20;  v

SYS\_REFCURSOR

&#x20;  |

&#x20;  v

Java ResultSet

&#x20;  |

&#x20;  v

Spring Boot REST API

&#x20;  |

&#x20;  v

React Frontend

```



This demonstrates practical Java-to-Oracle PL/SQL integration.



\---



\# 14. Audit Logging



The application implements database audit logging using the Oracle PL/SQL procedure:



```text

LOG\_AUDIT\_EVENT

```



Audit information includes:



\- Table name

\- Action type

\- Record ID

\- Changed by

\- Event details

\- Timestamp



Supported action types are:



```text

INSERT

UPDATE

DELETE

```



Java invokes the procedure using JDBC:



```text

CallableStatement

```



The audit flow is:



```text

Spring Boot

&#x20;   |

&#x20;   v

AuditLogService

&#x20;   |

&#x20;   v

AuditLogRepository

&#x20;   |

&#x20;   v

JDBC CallableStatement

&#x20;   |

&#x20;   v

LOG\_AUDIT\_EVENT

&#x20;   |

&#x20;   v

AUDIT\_LOG

```



This demonstrates:



\- Database auditing

\- PL/SQL procedure integration

\- Transaction handling

\- JDBC CallableStatement usage

\- Enterprise-style audit tracking



\---



\# 15. REST APIs



The backend exposes REST APIs for dashboard and clinical reporting.



\## Dashboard Summary



```text

GET /api/dashboard/summary

```



Example response:



```json

{

&#x20; "totalPatients": 8,

&#x20; "totalDoctors": 5,

&#x20; "totalAppointments": 8,

&#x20; "scheduledAppointments": 3,

&#x20; "completedAppointments": 5

}

```



\---



\## Patient Clinical Analytics



```text

GET /api/reports/patient-clinical-analytics

```



Returns patient-level:



\- Encounters

\- Diagnoses

\- Laboratory results



\---



\## Doctor Clinical Performance



```text

GET /api/dashboard/doctor-clinical-performance

```



Returns doctor-level clinical performance statistics.



\---



\## Department Clinical Statistics



```text

GET /api/dashboard/department-clinical-statistics

```



Returns department-level clinical statistics.



\---



\## Audit Logging



```text

POST /api/audit

```



Creates an audit event in the Oracle `AUDIT\_LOG` table.



\---



\# 16. React Dashboard



The React frontend provides a professional healthcare reporting dashboard.



The application includes the following sections:



```text

Dashboard

Patients

Doctors

Appointments

Clinical Reports

Lab Results

Patient Analytics

```



The dashboard consumes Spring Boot REST APIs and displays data retrieved from Oracle Database.



The dashboard includes:



\- Summary statistics

\- Appointment statistics

\- Diagnosis statistics

\- Doctor clinical performance

\- Patient clinical analytics

\- Patient details

\- Doctor details

\- Laboratory information

\- Clinical reports



\---



\# 17. Application Architecture



The backend follows a layered architecture.



```text

Controller

&#x20;   |

&#x20;   v

Service

&#x20;   |

&#x20;   v

Repository

&#x20;   |

&#x20;   v

JDBC

&#x20;   |

&#x20;   v

Oracle Database

```



\### Controller Layer



Responsible for:



\- HTTP requests

\- REST endpoints

\- Request handling

\- API responses



\### Service Layer



Responsible for:



\- Business logic

\- Validation

\- Service-level processing



\### Repository Layer



Responsible for:



\- SQL queries

\- Database access

\- PreparedStatement

\- CallableStatement

\- ResultSet processing



\### Database Layer



Responsible for:



\- Relational data

\- SQL reporting

\- PL/SQL procedures

\- Audit logging



\---



\# 18. SQL Concepts Demonstrated



The project demonstrates practical Oracle SQL concepts including:



\- SELECT

\- WHERE

\- JOIN

\- LEFT JOIN

\- GROUP BY

\- ORDER BY

\- COUNT

\- COUNT(DISTINCT ...)

\- Aggregate functions

\- String concatenation

\- Result processing

\- Multi-table reporting



Example reporting relationship:



```text

PATIENT

&#x20;  |

&#x20;  +---- APPOINTMENT

&#x20;  |

&#x20;  +---- ENCOUNTER

&#x20;            |

&#x20;            +---- DIAGNOSIS

&#x20;            |

&#x20;            +---- LAB\_RESULT

```



\---



\# 19. Testing



Automated tests are included using:



\- JUnit 5

\- Mockito

\- Spring Boot Test

\- Maven



Testing covers application service logic, repository functionality, and PL/SQL integration functionality.



The project was validated using:



```cmd

mvn clean test

```



The tests are designed to verify:



\- Service behavior

\- Repository interactions

\- Clinical reporting logic

\- PL/SQL procedure integration

\- Expected application results



\---



\# 20. Debugging and Root Cause Analysis



The project includes documented debugging and root cause analysis.



One issue involved an audit REST API returning an HTTP error during browser testing.



The endpoint was implemented using:



```java

@PostMapping

```



while the browser request was:



```text

GET

```



The issue was diagnosed as an HTTP method mismatch.



The resolution was validated using cURL with an explicit POST request.



The database audit record was then verified directly in Oracle Database.



Detailed documentation is available in:



```text

docs/DEBUGGING-RCA.md

```



This demonstrates:



\- REST API debugging

\- HTTP method analysis

\- Spring Boot exception handling

\- Root cause analysis

\- cURL testing

\- Oracle database verification

\- End-to-end troubleshooting



\---



\# 21. Project Structure



```text

healthcare-clinical-reporting/

│

├── src/

│   │

│   ├── main/

│   │   │

│   │   └── java/

│   │       │

│   │       └── com/

│   │           │

│   │           └── healthcare/

│   │               │

│   │               ├── config/

│   │               │   └── DatabaseConnection.java

│   │               │

│   │               ├── controller/

│   │               │   ├── AuditLogController.java

│   │               │   ├── PatientClinicalAnalyticsController.java

│   │               │   └── ...

│   │               │

│   │               ├── exception/

│   │               │   ├── GlobalExceptionHandler.java

│   │               │   └── PatientNotFoundException.java

│   │               │

│   │               ├── model/

│   │               │   ├── Appointment.java

│   │               │   ├── Department.java

│   │               │   ├── Doctor.java

│   │               │   ├── Patient.java

│   │               │   ├── PatientClinicalAnalytics.java

│   │               │   └── ...

│   │               │

│   │               ├── repository/

│   │               │   ├── AppointmentRepository.java

│   │               │   ├── AuditLogRepository.java

│   │               │   ├── ClinicalReportRepository.java

│   │               │   ├── ClinicalReportProcedureRepository.java

│   │               │   ├── DepartmentRepository.java

│   │               │   ├── DoctorRepository.java

│   │               │   ├── PatientRepository.java

│   │               │   ├── PatientClinicalAnalyticsRepository.java

│   │               │   └── ...

│   │               │

│   │               └── service/

│   │                   ├── AuditLogService.java

│   │                   ├── ClinicalReportService.java

│   │                   ├── DepartmentService.java

│   │                   ├── DoctorService.java

│   │                   ├── PatientClinicalAnalyticsService.java

│   │                   ├── PatientService.java

│   │                   └── ...

│   │

│   └── test/

│       │

│       └── java/

│           │

│           └── com/

│               │

│               └── healthcare/

│                   │

│                   ├── repository/

│                   │   ├── ClinicalReportProcedureRepositoryTest.java

│                   │   └── PatientRepositoryTest.java

│                   │

│                   └── service/

│                       └── PatientClinicalAnalyticsServiceTest.java

│

├── frontend/

│   │

│   ├── src/

│   │   ├── App.jsx

│   │   ├── App.css

│   │   ├── Patients.jsx

│   │   ├── Doctors.jsx

│   │   ├── Appointments.jsx

│   │   ├── ClinicalReports.jsx

│   │   ├── LabResults.jsx

│   │   └── PatientClinicalAnalytics.jsx

│   │

│   ├── package.json

│   └── ...

│

├── docs/

│   └── DEBUGGING-RCA.md

│

├── pom.xml

├── README.md

└── .gitignore

```



\---



\# 22. Running the Backend



\## Prerequisites



Install:



\- Java 23

\- Maven

\- Oracle Database 26ai

\- SQL Developer

\- Node.js

\- Git



\---



\## Oracle Database



The application uses the Oracle database service:



```text

Host: localhost

Port: 1521

Service: FREEPDB1

```



The application database user should be configured locally.



Database credentials should \*\*not\*\* be committed to GitHub.



\---



\## Start Spring Boot



From the project root:



```cmd

mvn spring-boot:run

```



The backend runs on:



```text

http://localhost:8080

```



\---



\# 23. Running the Frontend



Navigate to the frontend directory:



```cmd

cd frontend

```



Install dependencies:



```cmd

npm install

```



Start the React development server:



```cmd

npm run dev

```



The Vite development server will provide the local frontend URL in the terminal.



\---



\# 24. API Testing with cURL



Example dashboard request:



```cmd

curl http://localhost:8080/api/dashboard/summary

```



Example patient analytics request:



```cmd

curl http://localhost:8080/api/reports/patient-clinical-analytics

```



Example audit request:



```cmd

curl -X POST "http://localhost:8080/api/audit?tableName=PATIENT\&actionType=INSERT\&recordId=2\&changedBy=JAVA\_APP\&details=Patient%20audit%20test"

```



cURL was used to validate REST API behavior independently of the React frontend.



\---



\# 25. Project Validation



The application has been validated across the complete application stack:



```text

React Frontend

&#x20;     |

&#x20;     v

Spring Boot REST API

&#x20;     |

&#x20;     v

Java Service Layer

&#x20;     |

&#x20;     v

JDBC Repository Layer

&#x20;     |

&#x20;     v

Oracle Database

&#x20;     |

&#x20;     +---- SQL

&#x20;     |

&#x20;     +---- PL/SQL

```



Validated functionality includes:



\- Dashboard summary

\- Patient clinical analytics

\- Doctor clinical performance

\- Department clinical statistics

\- Clinical reporting

\- Audit logging

\- Oracle database connectivity

\- REST API communication

\- React frontend integration

\- Automated testing



\---



\# 26. Skills Demonstrated



\## Programming



\- Java

\- SQL

\- PL/SQL

\- JavaScript



\## Backend Development



\- Spring Boot

\- REST API development

\- JDBC

\- PreparedStatement

\- CallableStatement

\- ResultSet processing

\- Layered architecture

\- Exception handling

\- Input validation



\## Oracle Database



\- Oracle Database

\- SQL

\- PL/SQL

\- Stored Procedures

\- SYS\_REFCURSOR

\- SQL Joins

\- Aggregation

\- GROUP BY

\- Database auditing

\- Relational data modeling



\## Reporting and Analytics



\- Clinical reporting

\- Patient analytics

\- Doctor performance reporting

\- Department statistics

\- Appointment reporting

\- Diagnosis statistics

\- Laboratory result analysis



\## Testing



\- JUnit 5

\- Mockito

\- Maven testing

\- Repository testing

\- Service testing

\- PL/SQL integration testing



\## Frontend



\- React

\- Vite

\- JavaScript

\- CSS

\- REST API integration

\- Dashboard development

\- Data tables



\## Tools



\- Git

\- GitHub

\- VS Code

\- SQL Developer

\- cURL

\- Maven



\---



\# 27. Future Enhancements



Possible future improvements include:



\- Role-based authentication and authorization

\- Advanced interactive clinical dashboards

\- Data visualization and charts

\- PDF report generation

\- Excel report export

\- Pagination for large datasets

\- Centralized application logging

\- Docker-based deployment

\- CI/CD pipeline

\- Production database configuration

\- Additional healthcare workflows



\---



\# 28. Author



\*\*Giridhari Behera\*\*



B.Tech Computer Science \& Engineering



\---



\# 29. Project Purpose



This project was developed as a practical demonstration of:



```text

Java

\+

Spring Boot

\+

Oracle SQL

\+

PL/SQL

\+

JDBC

\+

REST APIs

\+

React

\+

JUnit

\+

Git/GitHub

```



The project specifically demonstrates the ability to build applications that retrieve, process, analyze, and report relational healthcare data using Java and Oracle technologies.



\---



\# 30. Conclusion



The \*\*Healthcare Clinical Reporting System\*\* demonstrates an end-to-end full-stack implementation combining enterprise Java development with Oracle database programming.



The project covers:



\- Healthcare data management

\- SQL-based reporting

\- PL/SQL stored procedures

\- JDBC integration

\- Spring Boot REST APIs

\- Clinical analytics

\- Audit logging

\- Automated testing

\- React dashboard development

\- Debugging and root cause analysis

\- Git/GitHub version control



It provides a practical demonstration of software development, database programming, data processing, reporting, testing, and full-stack application integration.

