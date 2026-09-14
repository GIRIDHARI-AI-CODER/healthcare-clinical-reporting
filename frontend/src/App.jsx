import { useEffect, useState } from "react";
import Patients from "./Patients";
import Doctors from "./Doctors";
import Appointments from "./Appointments";
import ClinicalReports from "./ClinicalReports";
import LabResults from "./LabResults";
import "./App.css";

function App() {
  const [activePage, setActivePage] = useState("dashboard");

  const [summary, setSummary] = useState({
    totalPatients: 0,
    totalDoctors: 0,
    totalAppointments: 0,
    scheduledAppointments: 0,
    completedAppointments: 0,
  });

  const [diagnoses, setDiagnoses] = useState([]);
  const [doctorStatistics, setDoctorStatistics] = useState([]);

  useEffect(() => {
    if (activePage !== "dashboard") {
      return;
    }

    Promise.all([
      fetch("http://localhost:8080/api/dashboard/summary").then((res) => {
        if (!res.ok) {
          throw new Error("Failed to load dashboard summary");
        }

        return res.json();
      }),

      fetch("http://localhost:8080/api/dashboard/diagnoses").then((res) => {
        if (!res.ok) {
          throw new Error("Failed to load diagnosis statistics");
        }

        return res.json();
      }),

      fetch(
        "http://localhost:8080/api/dashboard/doctor-clinical-performance"
      ).then((res) => {
        if (!res.ok) {
          throw new Error("Failed to load doctor clinical performance");
        }

        return res.json();
      }),
    ])
      .then(([summaryData, diagnosisData, doctorData]) => {
        setSummary(summaryData);
        setDiagnoses(diagnosisData);
        setDoctorStatistics(doctorData);
      })
      .catch((error) => {
        console.error("Dashboard loading error:", error);
      });
  }, [activePage]);

  return (
    <div className="app">

      <aside className="sidebar">

        <div className="logo">

          <div className="logo-icon">
            +
          </div>

          <div>
            <h2>HealthCare</h2>
            <span>Clinical System</span>
          </div>

        </div>

        <nav>

          <button
            className={activePage === "dashboard" ? "active" : ""}
            onClick={() => setActivePage("dashboard")}
          >
            <span>▦</span>
            Dashboard
          </button>

          <button
            className={activePage === "patients" ? "active" : ""}
            onClick={() => setActivePage("patients")}
          >
            <span>♙</span>
            Patients
          </button>

          <button
            className={activePage === "doctors" ? "active" : ""}
            onClick={() => setActivePage("doctors")}
          >
            <span>♙</span>
            Doctors
          </button>

          <button
            className={activePage === "appointments" ? "active" : ""}
            onClick={() => setActivePage("appointments")}
          >
            <span>▣</span>
            Appointments
          </button>

          <button
            className={activePage === "clinical-reports" ? "active" : ""}
            onClick={() => setActivePage("clinical-reports")}
          >
            <span>▤</span>
            Clinical Reports
          </button>

          <button
            className={activePage === "lab-results" ? "active" : ""}
            onClick={() => setActivePage("lab-results")}
          >
            <span>◉</span>
            Lab Results
          </button>

        </nav>

        <div className="sidebar-footer">
          <span>Healthcare Clinical Reporting</span>
          <small>Java + Oracle + React</small>
        </div>

      </aside>

      <main className="main-content">

        {activePage === "patients" ? (
          <Patients />

        ) : activePage === "doctors" ? (
          <Doctors />

        ) : activePage === "appointments" ? (
          <Appointments />

        ) : activePage === "clinical-reports" ? (
          <ClinicalReports />

        ) : activePage === "lab-results" ? (
          <LabResults />

        ) : (
          <Dashboard
            summary={summary}
            diagnoses={diagnoses}
            doctorStatistics={doctorStatistics}
            setActivePage={setActivePage}
          />
        )}

      </main>

    </div>
  );
}

function Dashboard({
  summary,
  diagnoses,
  doctorStatistics,
  setActivePage,
}) {
  return (
    <>
      <header className="topbar">

        <div>
          <h1>Clinical Dashboard</h1>
          <p>
            Healthcare patient and clinical reporting overview
          </p>
        </div>

        <button
          className="primary-button"
          onClick={() => setActivePage("patients")}
        >
          View Patients
        </button>

      </header>

      <section className="stats-grid">

        <div className="stat-card">

          <div className="stat-icon">
            ♙
          </div>

          <div>
            <span>Total Patients</span>
            <strong>{summary.totalPatients}</strong>
          </div>

        </div>

        <div className="stat-card">

          <div className="stat-icon">
            ♟
          </div>

          <div>
            <span>Total Doctors</span>
            <strong>{summary.totalDoctors}</strong>
          </div>

        </div>

        <div className="stat-card">

          <div className="stat-icon">
            ▣
          </div>

          <div>
            <span>Total Appointments</span>
            <strong>{summary.totalAppointments}</strong>
          </div>

        </div>

        <div className="stat-card">

          <div className="stat-icon">
            ✓
          </div>

          <div>
            <span>Completed</span>
            <strong>{summary.completedAppointments}</strong>
          </div>

        </div>

      </section>

      <section className="dashboard-grid">

        <div className="panel">

          <div className="panel-header">

            <div>
              <h2>Appointment Overview</h2>
              <p>Current appointment status</p>
            </div>

          </div>

          <div className="appointment-summary">

            <div>
              <span>Scheduled</span>
              <strong>{summary.scheduledAppointments}</strong>
            </div>

            <div>
              <span>Completed</span>
              <strong>{summary.completedAppointments}</strong>
            </div>

            <div>
              <span>Total</span>
              <strong>{summary.totalAppointments}</strong>
            </div>

          </div>

        </div>

        <div className="panel">

          <div className="panel-header">

            <div>
              <h2>Diagnosis Statistics</h2>
              <p>Clinical diagnosis distribution</p>
            </div>

          </div>

          <div className="diagnosis-list">

            {diagnoses.map((item, index) => (
              <div
                className="diagnosis-row"
                key={index}
              >
                <span>
                  {item.diagnosisName}
                </span>

                <strong>
                  {item.patientCount}
                </strong>
              </div>
            ))}

          </div>

        </div>

      </section>

      <section
        className="panel"
        style={{ marginTop: "20px" }}
      >

        <div className="panel-header">

          <div>
            <h2>Doctor Clinical Performance</h2>
            <p>
              Doctor appointment workload from Oracle PL/SQL reporting
            </p>
          </div>

        </div>

        <div className="table-card">

          <table>

            <thead>

              <tr>
                <th>Doctor ID</th>
                <th>Doctor</th>
                <th>Specialization</th>
                <th>Total Appointments</th>
              </tr>

            </thead>

            <tbody>

              {doctorStatistics.map((doctor) => (
                <tr key={doctor.doctorId}>

                  <td>
                    {doctor.doctorId}
                  </td>

                  <td>
                    <strong>
                      {doctor.doctorName}
                    </strong>
                  </td>

                  <td>
                    {doctor.specialization}
                  </td>

                  <td>
                    <span className="status-badge">
                      {doctor.totalAppointments}
                    </span>
                  </td>

                </tr>
              ))}

            </tbody>

          </table>

          {doctorStatistics.length === 0 && (
            <p className="empty-message">
              No doctor statistics available.
            </p>
          )}

        </div>

      </section>

      <footer className="dashboard-footer">
        Healthcare Clinical Reporting System • Spring Boot + Oracle Database +
        React
      </footer>

    </>
  );
}

export default App;