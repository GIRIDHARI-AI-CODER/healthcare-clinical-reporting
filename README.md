\# Healthcare Clinical Reporting System



A Java and Oracle-based healthcare clinical reporting system designed to manage patient, doctor, department, appointment, encounter, diagnosis, medication, laboratory, billing, and audit data.



\## Project Overview



This project demonstrates backend development and database programming using Java, JDBC, Oracle SQL, PL/SQL, Maven, and JUnit 5.



The system focuses on retrieving and processing healthcare data and generating clinical reports using SQL joins and a PL/SQL stored procedure.



\## Technology Stack



\- Java 23

\- Oracle Database 26ai

\- JDBC

\- SQL

\- PL/SQL

\- Maven

\- JUnit 5

\- Git \& GitHub

\- VS Code



\## Database Modules



The database contains the following major tables:



\- DEPARTMENT

\- DOCTOR

\- PATIENT

\- APPOINTMENT

\- ENCOUNTER

\- DIAGNOSIS

\- MEDICATION

\- LAB\_TEST

\- LAB\_RESULT

\- BILLING

\- AUDIT\_LOG



\## Key Features



\### Patient Management



\- Retrieve patient records using JDBC.

\- Search patients by patient ID.

\- Process patient demographic information.



\### Doctor and Department Management



\- Retrieve doctor information.

\- Retrieve department information.

\- Associate doctors with departments.



\### Appointment Reporting



\- Generate appointment reports.

\- Display patient, doctor, appointment time, reason, and status.

\- Generate doctor-wise appointment summaries.



\### Clinical Reporting



Generate a patient clinical report containing:



\- Patient information

\- Encounter details

\- Doctor information

\- Diagnosis

\- Medication

\- Dosage

\- Frequency



\### PL/SQL Integration



A stored procedure named `GET\_PATIENT\_CLINICAL\_SUMMARY` generates clinical data through an Oracle `SYS\_REFCURSOR`.



Java invokes the procedure using JDBC `CallableStatement` and processes the returned `ResultSet`.



This demonstrates integration between:



`Java → JDBC → Oracle PL/SQL → SYS\_REFCURSOR → Java ResultSet`



\### Testing



JUnit 5 tests are included for database repository and PL/SQL integration functionality.



\## Project Structure



```text

src/

├── main/

│   └── java/

│       └── com/

│           └── healthcare/

│               ├── config/

│               │   └── DatabaseConnection.java

│               ├── model/

│               │   ├── Appointment.java

│               │   ├── Department.java

│               │   ├── Doctor.java

│               │   └── Patient.java

│               ├── repository/

│               │   ├── AppointmentReportRepository.java

│               │   ├── AppointmentRepository.java

│               │   ├── ClinicalReportProcedureRepository.java

│               │   ├── ClinicalReportRepository.java

│               │   ├── DepartmentRepository.java

│               │   ├── DoctorAppointmentReportRepository.java

│               │   ├── DoctorRepository.java

│               │   ├── PatientEncounterReportRepository.java

│               │   └── PatientRepository.java

│               └── service/

│                   ├── ClinicalReportService.java

│                   ├── DepartmentService.java

│                   ├── DoctorService.java

│                   └── PatientService.java

│

└── test/

&#x20;   └── java/

&#x20;       └── com/

&#x20;           └── healthcare/

&#x20;               └── repository/

&#x20;                   ├── ClinicalReportProcedureRepositoryTest.java

&#x20;                   └── PatientRepositoryTest.java

