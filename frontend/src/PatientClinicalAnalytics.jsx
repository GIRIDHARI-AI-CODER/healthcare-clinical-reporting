import { useEffect, useState } from "react";

function PatientClinicalAnalytics() {
  const [patients, setPatients] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    fetch("http://localhost:8080/api/reports/patient-clinical-analytics")
      .then((res) => {
        if (!res.ok) {
          throw new Error("Failed to load patient clinical analytics");
        }

        return res.json();
      })
      .then((data) => {
        setPatients(data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Patient clinical analytics error:", err);
        setError("Unable to load clinical analytics.");
        setLoading(false);
      });
  }, []);

  if (loading) {
    return (
      <>
        <header className="topbar">
          <div>
            <h1>Patient Clinical Analytics</h1>
            <p>Clinical activity analysis from Oracle SQL reporting</p>
          </div>
        </header>

        <div className="panel">
          <p>Loading clinical analytics...</p>
        </div>
      </>
    );
  }

  if (error) {
    return (
      <>
        <header className="topbar">
          <div>
            <h1>Patient Clinical Analytics</h1>
            <p>Clinical activity analysis from Oracle SQL reporting</p>
          </div>
        </header>

        <div className="panel">
          <p className="empty-message">{error}</p>
        </div>
      </>
    );
  }

  return (
    <>
      <header className="topbar">
        <div>
          <h1>Patient Clinical Analytics</h1>
          <p>
            Patient encounters, diagnoses and laboratory result analysis
          </p>
        </div>
      </header>

      <section className="stats-grid">
        <div className="stat-card">
          <div className="stat-icon">♙</div>

          <div>
            <span>Total Patients</span>
            <strong>{patients.length}</strong>
          </div>
        </div>

        <div className="stat-card">
          <div className="stat-icon">◉</div>

          <div>
            <span>Patients With Encounters</span>
            <strong>
              {
                patients.filter(
                  (patient) => patient.totalEncounters > 0
                ).length
              }
            </strong>
          </div>
        </div>

        <div className="stat-card">
          <div className="stat-icon">▤</div>

          <div>
            <span>Total Diagnoses</span>
            <strong>
              {patients.reduce(
                (total, patient) =>
                  total + patient.totalDiagnoses,
                0
              )}
            </strong>
          </div>
        </div>

        <div className="stat-card">
          <div className="stat-icon">✓</div>

          <div>
            <span>Total Lab Results</span>
            <strong>
              {patients.reduce(
                (total, patient) =>
                  total + patient.totalLabResults,
                0
              )}
            </strong>
          </div>
        </div>
      </section>

      <section className="panel">
        <div className="panel-header">
          <div>
            <h2>Patient Clinical Activity</h2>
            <p>
              Aggregated clinical activity using Oracle SQL joins and
              grouping
            </p>
          </div>
        </div>

        <div className="table-card">
          <table>
            <thead>
              <tr>
                <th>Patient ID</th>
                <th>Patient Name</th>
                <th>Encounters</th>
                <th>Diagnoses</th>
                <th>Lab Results</th>
              </tr>
            </thead>

            <tbody>
              {patients.map((patient) => (
                <tr key={patient.patientId}>
                  <td>{patient.patientId}</td>

                  <td>
                    <strong>{patient.patientName}</strong>
                  </td>

                  <td>
                    <span className="status-badge">
                      {patient.totalEncounters}
                    </span>
                  </td>

                  <td>
                    <span className="status-badge">
                      {patient.totalDiagnoses}
                    </span>
                  </td>

                  <td>
                    <span className="status-badge">
                      {patient.totalLabResults}
                    </span>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>

          {patients.length === 0 && (
            <p className="empty-message">
              No patient clinical analytics available.
            </p>
          )}
        </div>
      </section>

      <footer className="dashboard-footer">
        Healthcare Clinical Reporting System • Oracle SQL + Spring Boot +
        React
      </footer>
    </>
  );
}

export default PatientClinicalAnalytics;